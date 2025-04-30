package com.softcraft.ohhsansibackend.participante.domain.services;

import com.softcraft.ohhsansibackend.inscripcion.application.usecases.InscripcionService;
import com.softcraft.ohhsansibackend.inscripcion.domain.models.Inscripcion;
import com.softcraft.ohhsansibackend.participante.domain.models.Participante;
import com.softcraft.ohhsansibackend.participante.domain.repository.implementation.ParticipanteDomainRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BulkParticipanteService {
    private final ParticipanteDomainRepository participanteDomainRepository;
    private final InscripcionService inscripcionService;

    @Autowired
    public BulkParticipanteService(ParticipanteDomainRepository participanteDomainRepository, InscripcionService inscripcionService) {
        this.participanteDomainRepository = participanteDomainRepository;
        this.inscripcionService = inscripcionService;
    }
    public Map<String, Object> importFromExcel(MultipartFile file) {
        List<Participante> participantes = new ArrayList<>();

        try (Workbook wb = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = wb.getSheetAt(0);
            boolean first = true;

            for (Row row : sheet) {
                if (first) { first = false; continue; } // salto de cabecera

                long ci = (long) row.getCell(0).getNumericCellValue();
                Inscripcion ins = inscripcionService.findByCarnetIdentidad(ci);
                if (ins == null) {
                    // podrías acumular errores en un listado y devolverlos también
                    continue;
                }

                Participante p = new Participante();
                p.setIdInscripcion(ins.getIdInscripcion());
                p.setIdColegio(ins.getIdColegio());
                p.setIdGrado(ins.getIdGrado());
                p.setTutorRequerido(ins.isTutorRequerido());
                p.setIdDepartamento(ins.getIdDepartamento());
                p.setIdMunicipio(ins.getIdMunicipio());

                p.setParticipanteHash(UUID.randomUUID().toString());
                p.setNombreParticipante(row.getCell(1).getStringCellValue());
                p.setApellidoPaterno(row.getCell(2).getStringCellValue());
                p.setApellidoMaterno(row.getCell(3).getStringCellValue());
                p.setFechaNacimiento(row.getCell(4).getDateCellValue());
                p.setCarnetIdentidadParticipante(ci);
                p.setComplementoCiParticipante(row.getCell(5).getStringCellValue());
                p.setEmailParticipante(row.getCell(6).getStringCellValue());

                participantes.add(p);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error leyendo el archivo Excel", e);
        }

        int[] resultados = participanteRepo.saveAll(participantes);
        int total = 0;
        for (int r : resultados) total += r;

        Map<String, Object> resp = new HashMap<>();
        resp.put("totalImportados", total);
        resp.put("detalles", resultados);
        return resp;
    }
}
