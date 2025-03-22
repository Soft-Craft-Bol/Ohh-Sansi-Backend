package com.softcraft.ohhsansibackend.inscripcion.application.ports;

import com.softcraft.ohhsansibackend.inscripcion.domain.models.InscripcionArea;
import com.softcraft.ohhsansibackend.inscripcion.domain.services.InscripcionAreaDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InscripcionAreaAdapter {
    private final InscripcionAreaDomainService inscripcionAreaDomainService;

    @Autowired
    public InscripcionAreaAdapter(InscripcionAreaDomainService inscripcionAreaDomainService) {
        this.inscripcionAreaDomainService = inscripcionAreaDomainService;
    }

    public List<InscripcionArea> getInscripcionAreas() {
        return inscripcionAreaDomainService.listInscripcionArea();
    }

}
