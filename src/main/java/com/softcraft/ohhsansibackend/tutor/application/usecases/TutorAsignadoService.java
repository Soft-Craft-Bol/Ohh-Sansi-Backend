package com.softcraft.ohhsansibackend.tutor.application.usecases;

import com.softcraft.ohhsansibackend.tutor.domain.models.TutorAsigned;
import com.softcraft.ohhsansibackend.tutor.domain.repository.abstraction.ITutorAsignado;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Map<String, Object> getTutoresLegales(String ci) {
        try {
            List<TutorAsigned> tutores = tutorAsignado.findAllTutors(ci);
            if (tutores == null || tutores.isEmpty()) {
                throw new RuntimeException("No se encontraron tutores legales para el CI proporcionado.");
            }
            return Map.of("tutoresLegales", tutores);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener los tutores legales", e);
        }
    }
}