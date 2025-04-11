package com.softcraft.ohhsansibackend.fechasolimpiada.infraestructure.rest;

import com.softcraft.ohhsansibackend.fechasolimpiada.application.usecases.FechaOlimpiadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/plazo-inscripcion")
public class PlazoInscripcionController {
    private final FechaOlimpiadaService fechaOlimpiadaService;

    @Autowired
    public PlazoInscripcionController(FechaOlimpiadaService fechaOlimpiadaService) {
        this.fechaOlimpiadaService = fechaOlimpiadaService;
    }

    @PostMapping("register")
    public ResponseEntity<Map<String, Object>> crearOActualizar(@RequestBody PlazoInscripcion plazoInscripcion) {
        Map<String, Object> response = fechaOlimpiadaService.upsertPlazoInscripcion(plazoInscripcion);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminar(@PathVariable int id) {
        Map<String, Object> response = fechaOlimpiadaService.deletePlazoInscripcion(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> buscarTodos() {
        Map<String, Object> response = fechaOlimpiadaService.getPlazosInscripcion();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> buscarPorId(@PathVariable int id) {
        Map<String, Object> response = fechaOlimpiadaService.getPlazoInscripcionById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/activo")
    public ResponseEntity<Map<String, Object>> buscarActivo() {
        Map<String, Object> response = fechaOlimpiadaService.getPlazoInscripcionActivo();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}/precio")
    public ResponseEntity<Map<String, Object>> actualizarPrecio(@PathVariable int id, @RequestBody PlazoInscripcion plazoInscripcion) {
        plazoInscripcion.setIdPeriodoInscripcion(id);
        Map<String, Object> response = fechaOlimpiadaService.insertPrecioPeriodo(plazoInscripcion);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
