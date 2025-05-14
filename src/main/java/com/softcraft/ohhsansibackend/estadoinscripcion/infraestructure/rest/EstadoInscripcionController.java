package com.softcraft.ohhsansibackend.estadoinscripcion.infraestructure.rest;

import com.softcraft.ohhsansibackend.estadoinscripcion.application.EstadoInscripcionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/estado-inscripcion")
public class EstadoInscripcionController {
    private final EstadoInscripcionService estadoInscripcionService;
    public EstadoInscripcionController(EstadoInscripcionService estadoInscripcionService) {
        this.estadoInscripcionService = estadoInscripcionService;
    }

    @GetMapping("/{ciParticipante}")
    public ResponseEntity<Map<String,Object>> getEstadoInscripcion(@PathVariable int ciParticipante) {
        Map<String, Object> response = estadoInscripcionService.verificarExistenciaDeTutores(ciParticipante);
        return ResponseEntity.ok(response);
    }
}
