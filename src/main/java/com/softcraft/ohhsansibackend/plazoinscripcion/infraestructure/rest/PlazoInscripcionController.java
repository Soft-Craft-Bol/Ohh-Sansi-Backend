package com.softcraft.ohhsansibackend.plazoinscripcion.infraestructure.rest;

import com.softcraft.ohhsansibackend.plazoinscripcion.application.usecases.PlazoInscripcionService;
import com.softcraft.ohhsansibackend.plazoinscripcion.domain.models.PlazoInscripcion;
import com.softcraft.ohhsansibackend.plazoinscripcion.infraestructure.dto.FechaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/plazo-inscripcion")
public class PlazoInscripcionController {
    private final PlazoInscripcionService plazoInscripcionService;

    @Autowired
    public PlazoInscripcionController(PlazoInscripcionService plazoInscripcionService) {
        this.plazoInscripcionService = plazoInscripcionService;
    }

    @PostMapping("register")
    public ResponseEntity<Map<String, Object>> crearOActualizar(@RequestBody PlazoInscripcion plazoInscripcion) {
        Map<String, Object> response = plazoInscripcionService.upsertPlazoInscripcion(plazoInscripcion);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminar(@PathVariable int id) {
        Map<String, Object> response = plazoInscripcionService.deletePlazoInscripcion(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> buscarTodos() {
        Map<String, Object> response = plazoInscripcionService.getPlazosInscripcion();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> buscarPorId(@PathVariable int id) {
        Map<String, Object> response = plazoInscripcionService.getPlazoInscripcionById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/activo")
    public ResponseEntity<Map<String, Object>> buscarActivo() {
        Map<String, Object> response = plazoInscripcionService.getPlazoInscripcionActivo();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}/precio")
    public ResponseEntity<Map<String, Object>> actualizarPrecio(@PathVariable int id, @RequestBody PlazoInscripcion plazoInscripcion) {
        plazoInscripcion.setIdPeriodoInscripcion(id);
        Map<String, Object> response = plazoInscripcionService.insertPrecioPeriodo(plazoInscripcion);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
