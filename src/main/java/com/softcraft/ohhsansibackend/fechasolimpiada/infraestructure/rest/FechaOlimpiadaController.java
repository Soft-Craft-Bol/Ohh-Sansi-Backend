package com.softcraft.ohhsansibackend.fechasolimpiada.infraestructure.rest;

import com.softcraft.ohhsansibackend.fechasolimpiada.application.usecases.FechaOlimpiadaService;
import com.softcraft.ohhsansibackend.fechasolimpiada.domain.models.FechaOlimpiada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/fechaolimpiada")
public class FechaOlimpiadaController {
    private final FechaOlimpiadaService fechaOlimpiadaService;

    @Autowired
    public FechaOlimpiadaController(FechaOlimpiadaService fechaOlimpiadaService) {
        this.fechaOlimpiadaService = fechaOlimpiadaService;
    }

    @PostMapping("register")
    public ResponseEntity<Map<String, Object>> crearOActualizar(@RequestBody FechaOlimpiada fechaOlimpiada) {
        Map<String, Object> response = fechaOlimpiadaService.upsertFechaOlimpiada(fechaOlimpiada);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminar(@PathVariable int id) {
        Map<String, Object> response = fechaOlimpiadaService.deleteFechaOlimpiada(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> buscarTodos() {
        Map<String, Object> response = fechaOlimpiadaService.getFechaOlimpiada();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> buscarPorId(@PathVariable int id) {
        Map<String, Object> response = fechaOlimpiadaService.getFechaOlimpiadaById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/public")
    public ResponseEntity<Map<String, Object>> buscarActivo() {
        Map<String, Object> response = fechaOlimpiadaService.getFechaOlimpiadaPublic();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
