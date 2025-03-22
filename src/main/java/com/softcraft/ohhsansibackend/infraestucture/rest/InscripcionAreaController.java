package com.softcraft.ohhsansibackend.inscripcion.infraestructure.rest;

import com.softcraft.ohhsansibackend.inscripcion.application.usecases.InscripcionAreaService;
import com.softcraft.ohhsansibackend.inscripcion.domain.models.InscripcionArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/inscripcion/area")
public class InscripcionAreaController {
    private final InscripcionAreaService inscripcionAreaService;

    @Autowired
    public InscripcionAreaController(InscripcionAreaService inscripcionAreaService) {
        this.inscripcionAreaService = inscripcionAreaService;
    }

    @GetMapping
    public ResponseEntity<List<InscripcionArea>> getInscripcionAreas() {
        List<InscripcionArea> inscripcionAreas = inscripcionAreaService.getInscripcionAreas();
        return ResponseEntity.ok(inscripcionAreas);
    }
}
