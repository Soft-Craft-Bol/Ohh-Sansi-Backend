package com.softcraft.ohhsansibackend.inscripcion.application.usecases;

import com.softcraft.ohhsansibackend.inscripcion.application.ports.InscripcionAreaAdapter;
import com.softcraft.ohhsansibackend.inscripcion.domain.models.InscripcionArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscripcionAreaService {
    private final InscripcionAreaAdapter inscripcionAreaAdapter;

    @Autowired
    public InscripcionAreaService(InscripcionAreaAdapter inscripcionAreaAdapter) {
        this.inscripcionAreaAdapter = inscripcionAreaAdapter;
    }

    public List<InscripcionArea> getInscripcionAreas() {
        return inscripcionAreaAdapter.getInscripcionAreas();
    }
}
