package com.softcraft.ohhsansibackend.inscripcion.domain.services;

import com.softcraft.ohhsansibackend.inscripcion.domain.models.InscripcionMasivaDetail;
import com.softcraft.ohhsansibackend.inscripcion.domain.repository.abstraction.IInscripcionMasivaDetail;
import com.softcraft.ohhsansibackend.inscripcion.domain.repository.implementation.InscripcionDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class InscripcionMasivaDomainService {
    private final IInscripcionMasivaDetail iInscripcionMasivaDetail;
    private final InscripcionDomainRepository inscripcionDomainRepository;


    @Autowired
    public InscripcionMasivaDomainService(IInscripcionMasivaDetail iInscripcionMasivaDetail,InscripcionDomainRepository inscripcionDomainRepository){
        this.iInscripcionMasivaDetail = iInscripcionMasivaDetail;
        this.inscripcionDomainRepository = inscripcionDomainRepository;
    }

    public ResponseEntity<Map<String, Object>> getDetailsInscription(String codUnique) {
        try {
            InscripcionMasivaDetail inscription = iInscripcionMasivaDetail.getAllDetails(codUnique);

            if (inscription == null) {
                return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "No se encontraron detalles o no es inscripcion masiva"));
            }

            return ResponseEntity.status(HttpStatus.OK).body(Map.of("Responsable", inscription,
                    "olimpiadas", inscripcionDomainRepository.findOlimapiada()));

        } catch (Exception e) {
            e.printStackTrace(); // Considera usar un logger adecuado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Error al obtener los detalles de inscripcion", "details", e.getMessage()));
        }
    }
}
