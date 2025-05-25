package com.softcraft.ohhsansibackend.tutor.infraestructure.rest;

import com.softcraft.ohhsansibackend.tutor.application.usecases.TutorAsignadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/tutores") //para legales y profesores
public class TutorAsignadoController {

    private final TutorAsignadoService tutorAsignadoService;

    @Autowired
    public TutorAsignadoController(TutorAsignadoService tutorAsignadoService) {
        this.tutorAsignadoService = tutorAsignadoService;
    }

    @GetMapping("/getTutoresLegales/{ci}")
    public ResponseEntity<Map<String, Object>> getTutoresLegalesByCi(@PathVariable String ci) {
        return tutorAsignadoService.getTutoresLegales(ci);
    }
}