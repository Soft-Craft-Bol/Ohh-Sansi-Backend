package com.softcraft.ohhsansibackend.fechasolimpiada.infraestructure.rest;

import com.softcraft.ohhsansibackend.fechasolimpiada.application.usecases.OlimpiadaService;
import com.softcraft.ohhsansibackend.fechasolimpiada.domain.models.Olimpiada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/olimpiada")
public class OlimpiadaController {
    private final OlimpiadaService olimpiadaService;

    @Autowired
    public OlimpiadaController(OlimpiadaService olimpiadaService) {
        this.olimpiadaService = olimpiadaService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> createOlimpiada(@RequestBody Olimpiada olimpiada) {
        Map<String, Object> response = olimpiadaService.saveOlimpiada(olimpiada);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteOlimpiada(@PathVariable int id) {
        Map<String, Object> response = olimpiadaService.deleteOlimpiada(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getOlimpiadas() {
        Map<String, Object> response = olimpiadaService.getOlimpiada();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getOlimpiadaById(@PathVariable int id) {
        Map<String, Object> response = olimpiadaService.getOlimpiadaById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/public")
    public ResponseEntity<Map<String, Object>> getOlimpiadaPublic() {
        Map<String, Object> response = olimpiadaService.getOlimpiadaPublic();
        return ResponseEntity.ok(response);
    }
}
