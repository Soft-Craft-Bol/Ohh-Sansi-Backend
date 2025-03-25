package com.softcraft.ohhsansibackend.inscripcion.application.usecases;

import com.softcraft.ohhsansibackend.inscripcion.application.ports.InscripcionAreaAdapter;
import com.softcraft.ohhsansibackend.inscripcion.domain.models.InscripcionArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InscripcionAreaService {

    private final InscripcionAreaAdapter inscripcionAreaAdapter;

    @Autowired
    public InscripcionAreaService(InscripcionAreaAdapter inscripcionAreaAdapter) {
        this.inscripcionAreaAdapter = inscripcionAreaAdapter;
    }

    public InscripcionArea createInscripcionArea(int idInscripcion, int idArea) {
        return inscripcionAreaAdapter.saveInscripcionArea(idInscripcion, idArea);
    }
}