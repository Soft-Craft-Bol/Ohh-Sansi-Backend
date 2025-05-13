package com.softcraft.ohhsansibackend.registromasivo.application.usecases;

import com.softcraft.ohhsansibackend.participante.domain.models.Participante;
import com.softcraft.ohhsansibackend.participante.application.usecases.ParticipanteService;
import com.monitorjbl.xlsx.StreamingReader;
import com.softcraft.ohhsansibackend.tutor.application.usecases.TutorService;
import com.softcraft.ohhsansibackend.tutor.domain.models.Tutor;
import org.apache.poi.ss.usermodel.*;
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

    public InscripcionMasivaService(ParticipanteService participanteService, TutorService tutorService) {
        this.participanteService = participanteService;
        this.tutorService = tutorService;
    }

    public List<Map<String, Object>> processInscripcionMasiva(InputStream fileInputStream) throws IOException {
        List<Map<String, Object>> resultados = new ArrayList<>();
        int totalRegistros = 0;
        int registrosExitosos = 0;
        int registrosOmitidos = 0;

        try (Workbook workbook = StreamingReader.builder()
                .rowCacheSize(100)
                .bufferSize(4096)
                .open(fileInputStream)) {

            Sheet sheet = workbook.getSheetAt(1); // Índice 1 para la segunda hoja
            System.out.println("Procesando hoja oculta: " + sheet.getSheetName());

            boolean firstRow = true;

            for (Row row : sheet) {
                if (firstRow) {
                    firstRow = false;
                    continue;
                }

                totalRegistros++;
                Map<String, Object> resultado = new LinkedHashMap<>();
                resultado.put("fila", row.getRowNum() + 1);

                try {
                    // Validar campos obligatorios del participante
                    if (!esFilaValida(row)) {
                        resultado.put("success", false);
                        resultado.put("error", "Fila omitida - Campos obligatorios vacíos");
                        resultado.put("omitido", true);
                        registrosOmitidos++;
                        resultados.add(resultado);
                        continue;
                    }

                    // 1. Registrar participante primero
                    Participante participante = mapRowToParticipanteHoja2(row);
                    Map<String, Object> saveResult = participanteService.save(participante);
                    resultado.putAll(saveResult);

                    // 2. Verificar si necesita tutor y procesar tutores
                    if (participante.isTutorRequerido()) {
                        List<Tutor> tutores = new ArrayList<>();

                        // Procesar tutor legal (si existe en la fila)
                        if (!isEmptyCell(row.getCell(13))) { // Verificar si hay datos de tutor
                            Tutor tutorLegal = mapRowToTutorLegal(row);
                            if (tutorLegal != null) {
                                tutores.add(tutorLegal);

                                // Registrar el tutor
                                Map<String, Object> tutorResult = tutorService.save(
                                        tutores,
                                        participante.getCarnetIdentidadParticipante(),
                                        (int) getNumericCellValue(row.getCell(20)) // parentescoId
                                );

                                resultado.put("tutorResult", tutorResult);
                            }
                        }
                    }

                    resultado.put("success", true);
                    registrosExitosos++;

                } catch (Exception e) {
                    resultado.put("success", false);
                    resultado.put("error", e.getMessage());
                    e.printStackTrace(); // Para depuración
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

        if (isEmptyCell(row.getCell(14)) || // email_tutor
                isEmptyCell(row.getCell(15)) || // nombres_tutor
                isEmptyCell(row.getCell(16)) || // apellidos_tutor
                isEmptyCell(row.getCell(18))) { // carnet_identidad_tutor
            return null;
        }

        Tutor tutor = new Tutor();
        tutor.setIdTipoTutor((int) getNumericCellValue(row.getCell(13)));
        tutor.setEmailTutor(getStringCellValue(row.getCell(14)));
        tutor.setNombresTutor(getStringCellValue(row.getCell(15)));
        tutor.setApellidosTutor(getStringCellValue(row.getCell(16)));
        tutor.setTelefono((int) getNumericCellValue(row.getCell(17)));
        tutor.setCarnetIdentidadTutor((int) getNumericCellValue(row.getCell(18)));
        tutor.setComplementoCiTutor(getStringCellValue(row.getCell(19)));


        return tutor;
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

        switch (cell.getCellType()) {
            case BLANK:
                return true;
            case STRING:
                return cell.getStringCellValue().trim().isEmpty();
            case NUMERIC:
                // Considerar 0 como vacío si es necesario
                return cell.getNumericCellValue() == 0;
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
            System.out.println("Tipo de celda: " + cell.getCellType());

            // Caso 1: Celda es una fórmula
            if (cell.getCellType() == CellType.FORMULA) {
                System.out.println("Celda es fórmula. Evaluando...");

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
        if (cell == null) return "";

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                return String.valueOf((int)cell.getNumericCellValue());
            case FORMULA:
                switch (cell.getCachedFormulaResultType()) {
                    case STRING:
                        return cell.getStringCellValue().trim();
                    case NUMERIC:
                        return String.valueOf((int)cell.getNumericCellValue());
                    default:
                        return "";
                }
            default:
                return "";
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
        return cell.getCellType() == CellType.BOOLEAN ? cell.getBooleanCellValue() : false;
    }
}