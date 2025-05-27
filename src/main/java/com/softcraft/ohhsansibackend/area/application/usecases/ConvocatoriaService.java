package com.softcraft.ohhsansibackend.area.application.usecases;

import com.softcraft.ohhsansibackend.area.domain.models.Convocatoria;
import com.softcraft.ohhsansibackend.area.domain.models.PdfOlimpiada;
import com.softcraft.ohhsansibackend.area.domain.repository.implementation.ConvocatoriaDomainRepository;
import com.softcraft.ohhsansibackend.area.domain.repository.implementation.PdfOlimpiadaDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.Map;

@Service
public class ConvocatoriaService {
    private final ConvocatoriaDomainRepository convocatoriaDomainRepository;
    private final AreaService areaService;
    private final PdfOlimpiadaDomainRepository pdfOlimpiadaDomainRepository;

    @Autowired
    public ConvocatoriaService(ConvocatoriaDomainRepository convocatoriaDomainRepository,
                               AreaService areaService,
                               PdfOlimpiadaDomainRepository pdfOlimpiadaDomainRepository
    ) {
        this.convocatoriaDomainRepository = convocatoriaDomainRepository;
        this.areaService = areaService;
        this.pdfOlimpiadaDomainRepository = pdfOlimpiadaDomainRepository;
    }

    public Map<String, Object> saveConvocatoria(
            int idArea,
            int idOlimpiada,
            MultipartFile pdfFile
    ) {
        Map<String, Object> response = new java.util.HashMap<>();
        try {
            String pdfBase64 = Base64.getEncoder().encodeToString(pdfFile.getBytes());
            PdfOlimpiada pdfOlimpiada = pdfOlimpiadaDomainRepository.save(pdfBase64);
            Convocatoria convocatoria = new Convocatoria(idArea, idOlimpiada, pdfOlimpiada.getIdPdfConvocatoria());
            convocatoriaDomainRepository.save(convocatoria);
            response.put("status", "success");
            response.put("message", "Convocatoria guardada correctamente");
            response.put("convocatoria", convocatoria);
            response.put("pdfOlimpiada", pdfOlimpiada);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la convocatoria: " + e.getMessage(), e);
        }
        return response;
    }

}
