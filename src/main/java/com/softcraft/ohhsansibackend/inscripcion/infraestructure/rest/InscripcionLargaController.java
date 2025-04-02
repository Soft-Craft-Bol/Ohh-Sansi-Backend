package com.softcraft.ohhsansibackend.inscripcion.infraestructure.rest;

import com.softcraft.ohhsansibackend.inscripcion.application.usecases.InscripcionLargaService;
import com.softcraft.ohhsansibackend.inscripcion.infraestructure.request.InscripcionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/inscripcion/v1")
public class InscripcionLargaController {

    private final InscripcionLargaService inscripcionLargaService;

    @Autowired
    public InscripcionLargaController(InscripcionLargaService inscripcionLargaService) {
        this.inscripcionLargaService = inscripcionLargaService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerInscripcion(@RequestBody InscripcionDTO inscripcionDTO) {
        Map<String, Object> response = inscripcionLargaService.registerInscripcion(inscripcionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
