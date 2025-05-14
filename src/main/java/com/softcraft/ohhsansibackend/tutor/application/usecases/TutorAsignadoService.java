package com.softcraft.ohhsansibackend.tutor.application.usecases;

import com.softcraft.ohhsansibackend.tutor.domain.models.TutorAsigned;
import com.softcraft.ohhsansibackend.tutor.domain.repository.abstraction.ITutorAsignado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TutorAsignadoService {

    private final ITutorAsignado tutorAsignado;

    @Autowired
    public TutorAsignadoService(ITutorAsignado tutorAsignado) {
        this.tutorAsignado = tutorAsignado;
    }

    public ResponseEntity<Map<String, Object>> getTutoresLegales(String ci) {
        try {
            List<TutorAsigned> tutores = tutorAsignado.findAllTutors(ci);

            if (tutores.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "No se encontraron tutores legales para el CI proporcionado."));
            }

            return ResponseEntity.status(HttpStatus.OK).body(Map.of("tutoresLegales", tutores));

        } catch (Exception e) {
            e.printStackTrace(); // Considera usar un logger adecuado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Error al obtener los tutores legales", "details", e.getMessage()));
        }
    }
}