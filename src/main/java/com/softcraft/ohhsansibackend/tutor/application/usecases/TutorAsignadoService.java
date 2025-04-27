package com.softcraft.ohhsansibackend.tutor.application.usecases;

import com.softcraft.ohhsansibackend.tutor.domain.models.Tutor;
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
        List<Tutor> tutores = tutorAsignado.findAllTutors(ci);
        return Map.of("tutoresLegales", tutores);
    }
}