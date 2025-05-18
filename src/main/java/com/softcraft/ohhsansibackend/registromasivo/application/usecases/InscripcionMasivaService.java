package com.softcraft.ohhsansibackend.registromasivo.application.usecases;

import com.softcraft.ohhsansibackend.exception.ResourceNotFoundException;
import com.softcraft.ohhsansibackend.participante.application.ports.ParticipanteAdapter;
import com.softcraft.ohhsansibackend.participante.domain.models.Participante;
import com.softcraft.ohhsansibackend.participante.application.usecases.ParticipanteService;
import com.monitorjbl.xlsx.StreamingReader;
import com.softcraft.ohhsansibackend.participante.domain.repository.implementation.ParticipanteDomainRepository;
import com.softcraft.ohhsansibackend.tutor.application.usecases.TutorService;
import com.softcraft.ohhsansibackend.tutor.domain.models.Tutor;
import com.softcraft.ohhsansibackend.utils.UniqueCodeGenerator;
import org.apache.poi.ss.usermodel.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class InscripcionMasivaService {

    private final ParticipanteService participanteService;
    private final TutorService tutorService;
    private final JdbcTemplate jdbcTemplate;
    private int counterFaileds = 0;

    public InscripcionMasivaService(ParticipanteService participanteService, TutorService tutorService, JdbcTemplate jdbcTemplate) {
        this.participanteService = participanteService;
        this.tutorService = tutorService;
        this.jdbcTemplate = jdbcTemplate;
    }

    public Participante createExclParticipante() {
        Participante participante = new Participante();
        // Datos por defecto para el participante Excel
        participante.setIdDepartamento(2541);
        participante.setIdMunicipio(11427);
        participante.setIdColegio(11383);
        participante.setIdGrado(15);
        participante.setParticipanteHash("XcelLinscRiption");
        participante.setNombreParticipante("Excel");
        participante.setApellidoPaterno("Inscription");
        participante.setApellidoMaterno("");
        participante.setFechaNacimiento(java.sql.Date.valueOf("2009-10-14"));

        Random random = new Random();
        int carnet = 22000000 + random.nextInt(2000000);;
        ParticipanteDomainRepository participanteAdapter = new ParticipanteDomainRepository(jdbcTemplate);

        boolean existe;
        try {
            participanteAdapter.findByCarnetIdentidad(carnet);
            existe = true;
        } catch (ResourceNotFoundException ex) {
            existe = false;
        }

        if (existe) {
            int nuevoCarnet;
            int intentos = 0;
            do {
                nuevoCarnet = 22000000 + random.nextInt(2000000); // genera entre 22000000 y 23999999
                intentos++;
                if (intentos > 100) {
                    throw new RuntimeException("No se pudo generar un CI único después de múltiples intentos.");
                }
                try {
                    participanteAdapter.findByCarnetIdentidad(nuevoCarnet);
                } catch (ResourceNotFoundException ex) {
                    carnet = nuevoCarnet;
                    break; // CI único encontrado
                }
            } while (true);
        }

        participante.setCarnetIdentidadParticipante(carnet);
        System.out.println(carnet);
        participante.setEmailParticipante("amercer732@gmail.com");
        participante.setTutorRequerido(false);

        return participante;
    }


    public List<Map<String, Object>> processInscripcionMasiva(InputStream fileInputStream) throws IOException {
        Participante participanteExcel = createExclParticipante();
        List<Map<String, Object>> resultados = new ArrayList<>();
        int totalRegistros = 0;
        int registrosExitosos = 0;
        int registrosOmitidos = 0;
        int omitidosSeguidos = 0;
        participanteService.save(participanteExcel);

        try (Workbook workbook = StreamingReader.builder()
                .rowCacheSize(100)
                .bufferSize(4096)
                .open(fileInputStream)) {

            Sheet sheet = workbook.getSheetAt(1);
            System.out.println("Procesando hoja: " + sheet.getSheetName());
            Sheet hoja4 = workbook.getSheetAt(4);
            System.out.println("Procesando hoja: " + hoja4.getSheetName());


            Iterator<Row> rowIteratorHoja4 = hoja4.iterator();
            if (rowIteratorHoja4.hasNext()) {
                rowIteratorHoja4.next();
            }
            Iterator<Row> rowIterator = sheet.iterator();
            if (rowIterator.hasNext()) {
                rowIterator.next(); // Saltar la fila de encabezados
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Row rowArea = rowIteratorHoja4.next();

                totalRegistros++;
                Map<String, Object> resultado = new LinkedHashMap<>();
                resultado.put("fila", row.getRowNum() + 1);

                try {
                    // Validar campos obligatorios del participante
                    if (!esFilaValida(row)) {
                        registrosOmitidos++;
                        omitidosSeguidos++;
                        if(omitidosSeguidos >= 2){
                            System.out.println("Se llego al final de la tabla, detendiendo ejecución");//Si ya son 2 seguidos, final de la tabla
                            break;
                        }
                        resultado.put("success", false);
                        resultado.put("error", "Fila omitida - Campos obligatorios vacíos");
                        resultado.put("omitido", true);
                        resultados.add(resultado);
                        continue;
                    }else{
                        omitidosSeguidos = 0;
                    }

                    // 1. Registrar participante
                    Participante participante = mapRowToParticipanteHoja2(row);
                    Map<String, Object> saveResult = participanteService.save(participante);
                    resultado.putAll(saveResult);

                    // Asociar participanteExcel con el nuevo participante
                    String insertSql = "INSERT INTO excel_association (id_excel, ci_participante, id_inscripcion_excel) VALUES (?, ?, ?)";
                    jdbcTemplate.update(insertSql, participanteExcel.getCarnetIdentidadParticipante(), participante.getCarnetIdentidadParticipante(), participanteExcel.getIdInscripcion());

                    // También agregar al resultado del body
                    resultado.put("ci_participante_excel", participanteExcel.getCarnetIdentidadParticipante());
                    resultado.put("id_inscripcion", participanteExcel.getIdInscripcion());



                    // 2. Procesar tutor si es requerido
                    if (participante.isTutorRequerido()) {
                        try {
                            Tutor tutorLegal = mapRowToTutorLegal(row);
                            Tutor profesor1 = mapRowToProfesor(rowArea,0);
                            Tutor profesor2 = mapRowToProfesor(rowArea,7);
                            if (tutorLegal != null) {
                                List<Tutor> tutores = new ArrayList<>();
                                tutores.add(tutorLegal);

                                // Validar parentesco
                                int parentescoId = (int)getSafeIntValue(row.getCell(20));
                                int area1 = (int)getSafeIntValue(rowArea.getCell(2));
                                int area2 = (int)getSafeIntValue(rowArea.getCell(9));
                                if (parentescoId == 0) {
                                    throw new IllegalArgumentException("Parentesco del tutor es obligatorio");
                                }

                                // Registrar tutor
                                Map<String, Object> tutorResult = tutorService.save(
                                        tutores,
                                        participante.getCarnetIdentidadParticipante(),
                                        parentescoId
                                );
                                resultado.put("tutorResult", tutorResult);


                                // Registrar profesor 1 si es válido
                                if (area1 > 0 && profesor1 != null) {
                                    Map<String, Object> profe1Result = tutorService.registrarTutorAcademico(
                                            profesor1,
                                            participante.getCarnetIdentidadParticipante(),
                                            area1
                                    );
                                    resultado.put("Profesor1 result", profe1Result);
                                } else {
                                    resultado.put("Profesor1 error", "No se registró: área o datos de profesor inválidos");
                                }

                                // Registrar profesor 2 si es válido
                                if (area2 > 0 && profesor2 != null) {
                                    Map<String, Object> profe2Result = tutorService.registrarTutorAcademico(
                                            profesor2,
                                            participante.getCarnetIdentidadParticipante(),
                                            area2
                                    );
                                    resultado.put("Profesor2 result", profe2Result);
                                } else {
                                    resultado.put("Profesor2 error", "No se registró: área o datos de profesor inválidos");
                                }

                            }
                        } catch (Exception e) {
                            resultado.put("tutorError", "Error al procesar tutor: " + e.getMessage());
                            // Continuar aunque falle el tutor, pero marcarlo en el resultado
                        }
                    }

                    resultado.put("success", true);
                    registrosExitosos++;

                } catch (Exception e) {
                    resultado.put("success", false);
                    resultado.put("error", e.getMessage());
                    e.printStackTrace();
                }

                resultados.add(resultado);
            }
        }

        // Resumen final
        Map<String, Object> resumen = new HashMap<>();
        resumen.put("totalRegistros", totalRegistros);
        resumen.put("registrosExitosos", registrosExitosos);
        resumen.put("registrosFallidos", totalRegistros - registrosExitosos - registrosOmitidos);
        resumen.put("registrosOmitidos", registrosOmitidos);
        resultados.add(resumen);

        return resultados;
    }


    private Tutor mapRowToTutorLegal(Row row) {
        try {
            // Validar campos obligatorios del tutor
            if (isEmptyCell(row.getCell(13)) || // id_tipo_tutor
                    isEmptyCell(row.getCell(14)) || // email_tutor
                    isEmptyCell(row.getCell(15)) || // nombres_tutor
                    isEmptyCell(row.getCell(16)) || // apellidos_tutor
                    isEmptyCell(row.getCell(18))) { // carnet_identidad_tutor
                return null;
            }

            Tutor tutor = new Tutor();
            tutor.setIdTipoTutor(getSafeIntValue(row.getCell(13)));
            tutor.setEmailTutor(getSafeStringValue(row.getCell(14)));
            tutor.setNombresTutor(getSafeStringValue(row.getCell(15)));
            tutor.setApellidosTutor(getSafeStringValue(row.getCell(16)));
            tutor.setTelefono(getSafeIntValue(row.getCell(17)));
            tutor.setCarnetIdentidadTutor(getSafeIntValue(row.getCell(18)));
            tutor.setComplementoCiTutor(getSafeStringValue(row.getCell(19)));


            return tutor;
        } catch (Exception e) {
            System.err.println("Error al mapear tutor: " + e.getMessage());
            return null;
        }
    }

    private Tutor mapRowToProfesor(Row row, int index) {
        try {
            // Validar campos obligatorios del tutor
            if (isEmptyCell(row.getCell(1 )) || // id_tipo_tutor
                    isEmptyCell(row.getCell(index+2)) || // id_area
                    isEmptyCell(row.getCell(index+3)) || // email_tutor
                    isEmptyCell(row.getCell(index+4)) || // nombres_tutor
                    isEmptyCell(row.getCell(index+5)) || // apellidos_tutor
                    isEmptyCell(row.getCell(index+7))) { // carnet_identidad_tutor
                return null;
            }

            Tutor tutor = new Tutor();
            tutor.setIdTipoTutor(getSafeIntValue(row.getCell(index+1)));
            tutor.setEmailTutor(getSafeStringValue(row.getCell(index+3)));
            tutor.setNombresTutor(getSafeStringValue(row.getCell(index+4)));
            tutor.setApellidosTutor(getSafeStringValue(row.getCell(index+5)));
            tutor.setTelefono(getSafeIntValue(row.getCell(index+6)));
            tutor.setCarnetIdentidadTutor(getSafeIntValue(row.getCell(index+7)));
            tutor.setComplementoCiTutor(getSafeStringValue(row.getCell(index+8)));


            return tutor;
        } catch (Exception e) {
            System.err.println("Error al mapear tutor: " + e.getMessage());
            return null;
        }
    }

    private String getSafeStringValue(Cell cell) {
        if (cell == null) return null;  // Cambiado de "" a null

        try {
            String value;
            switch (cell.getCellType()) {
                case STRING:
                    value = cell.getStringCellValue().trim();
                    return value.isEmpty() ? null : value;
                case NUMERIC:
                    return String.valueOf((int) cell.getNumericCellValue());
                case FORMULA:
                    switch (cell.getCachedFormulaResultType()) {
                        case STRING:
                            value = cell.getStringCellValue().trim();
                            return value.isEmpty() ? null : value;
                        case NUMERIC:
                            return String.valueOf((int) cell.getNumericCellValue());
                        default:
                            return null;  // Cambiado de "" a null
                    }
                default:
                    return null;  // Cambiado de "" a null
            }
        } catch (Exception e) {
            System.err.println("Error al leer valor de celda como string: " + e.getMessage());
            return null;  // Cambiado de "" a null
        }
    }

    private int getSafeIntValue(Cell cell) {
        if (cell == null) return 0;

        try {
            switch (cell.getCellType()) {
                case NUMERIC:
                    return (int) cell.getNumericCellValue();
                case FORMULA:
                    if (cell.getCachedFormulaResultType() == CellType.NUMERIC) {
                        return (int) cell.getNumericCellValue();
                    }
                    return 0;
                case STRING:
                    try {
                        return Integer.parseInt(cell.getStringCellValue().trim());
                    } catch (NumberFormatException e) {
                        return 0;
                    }
                default:
                    return 0;
            }
        } catch (Exception e) {
            System.err.println("Error al leer valor de celda como entero: " + e.getMessage());
            return 0;
        }
    }

    private boolean esFilaValida(Row row) {
        // Verificar campos obligatorios
        if (isEmptyCell(row.getCell(0)) || // idDepartamento
                isEmptyCell(row.getCell(1)) || // idMunicipio
                isEmptyCell(row.getCell(2)) || // idColegio
                isEmptyCell(row.getCell(3)) || // idGrado
                isEmptyCell(row.getCell(5)) || // nombreParticipante
                isEmptyCell(row.getCell(6)) || // apellidoPaterno
                isEmptyCell(row.getCell(8))) { // fechaNacimiento
            return false;
        }
        return true;
    }


    private boolean isEmptyCell(Cell cell) {
        if (cell == null) {
            return true;
        }

        CellType cellType = cell.getCellType();

        // Si es una fórmula, evaluamos su resultado
        if (cellType == CellType.FORMULA) {
            cellType = cell.getCachedFormulaResultType();
        }

        switch (cellType) {
            case BLANK:
                return true;

            case STRING:
                String strValue = cell.getStringCellValue().trim();
                return strValue.isEmpty() || strValue.equals("0");

            case NUMERIC:
                double numValue = cell.getNumericCellValue();
                // Validamos 0 y valores de fecha tipo 1899-12-31
                if (DateUtil.isCellDateFormatted(cell)) {
                    Date date = cell.getDateCellValue();
                    return date.getTime() == DateUtil.getJavaDate(0).getTime();
                }
                return numValue == 0;

            case BOOLEAN:
                return false; // booleanos no deberían ser vacíos normalmente

            default:
                return false;
        }
    }


    private Participante mapRowToParticipanteHoja2(Row row) {
        Participante participante = new Participante();

        participante.setIdDepartamento((int) getNumericCellValue(row.getCell(0)));
        participante.setIdMunicipio((int) getNumericCellValue(row.getCell(1)));
        participante.setIdColegio((int) getNumericCellValue(row.getCell(2)));
        participante.setIdGrado((int) getNumericCellValue(row.getCell(3)));
        participante.setParticipanteHash(getStringCellValue(row.getCell(4)));
        participante.setNombreParticipante(getStringCellValue(row.getCell(5)));
        participante.setApellidoPaterno(getStringCellValue(row.getCell(6)));
        participante.setApellidoMaterno(getStringCellValue(row.getCell(7)));
        participante.setFechaNacimiento(getDateCellValue(row.getCell(8)));
        participante.setCarnetIdentidadParticipante((int) getNumericCellValue(row.getCell(9)));
        participante.setComplementoCiParticipante(getStringCellValue(row.getCell(10)));
        participante.setEmailParticipante(getStringCellValue(row.getCell(11)));
        participante.setTutorRequerido(getBooleanCellValue(row.getCell(12)));

        return participante;
    }

    private Date getDateCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }

        try {

            // Caso 1: Celda es una fórmula
            if (cell.getCellType() == CellType.FORMULA) {
                System.out.println("Celda es fórmula. Evaluando...");
                System.out.println("su formula"+ cell.getCellFormula());

                // Obtener el valor calculado de la fórmula
                switch (cell.getCachedFormulaResultType()) {
                    case NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            return cell.getDateCellValue();
                        } else {
                            throw new IllegalArgumentException("Fórmula numérica no es una fecha válida");
                        }
                    case STRING:
                        String dateStr = cell.getStringCellValue().trim();
                        return parseDateString(dateStr);
                    default:
                        throw new IllegalArgumentException("Tipo de resultado de fórmula no soportado");
                }
            }
            // Caso 2: Celda numérica (fecha de Excel)
            else if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
                return cell.getDateCellValue();
            }
            // Caso 3: Celda de texto (fecha como cadena)
            else if (cell.getCellType() == CellType.STRING) {
                return parseDateString(cell.getStringCellValue());
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Error procesando fecha: " + e.getMessage());
        }

        throw new IllegalArgumentException("Tipo de celda no soportado para fecha: " + cell.getCellType());
    }

    private Date parseDateString(String dateStr) throws ParseException {
        dateStr = dateStr.trim().replaceAll("\\s+", "");

        String[] formats = {
                "d/M/yyyy", "dd/MM/yyyy",
                "d-M-yyyy", "dd-MM-yyyy",
                "d.M.yyyy", "dd.MM.yyyy",
                "yyyy-MM-dd"
        };

        for (String format : formats) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                sdf.setLenient(false);
                return sdf.parse(dateStr);
            } catch (ParseException e) {
                // Continuar con el siguiente formato
            }
        }

        throw new ParseException("Formato de fecha no reconocido: " + dateStr, 0);
    }

    private String getStringCellValue(Cell cell) {
        if (cell == null) return null;  // Cambiado de "" a null

        switch (cell.getCellType()) {
            case STRING:
                String value = cell.getStringCellValue().trim();
                return value.isEmpty() ? null : value;
            case NUMERIC:
                return String.valueOf((int)cell.getNumericCellValue());
            case FORMULA:
                switch (cell.getCachedFormulaResultType()) {
                    case STRING:
                        value = cell.getStringCellValue().trim();
                        return value.isEmpty() ? null : value;
                    case NUMERIC:
                        return String.valueOf((int)cell.getNumericCellValue());
                    default:
                        return null;  // Cambiado de "" a null
                }
            default:
                return null;  // Cambiado de "" a null
        }
    }

    private double getNumericCellValue(Cell cell) {
        if (cell == null) return 0;

        switch (cell.getCellType()) {
            case NUMERIC:
                return cell.getNumericCellValue();
            case FORMULA:
                if (cell.getCachedFormulaResultType() == CellType.NUMERIC) {
                    return cell.getNumericCellValue();
                }
                return 0;
            default:
                return 0;
        }
    }

    private boolean getBooleanCellValue(Cell cell) {
        if (cell == null) return false;
        return cell.getCellType() == CellType.BOOLEAN ? cell.getBooleanCellValue() : true;
    }
}