package com.softcraft.ohhsansibackend.periodosolimpiada.infraestructure.rest;

import com.softcraft.ohhsansibackend.periodosolimpiada.application.usecases.OlimpiadaService;
import com.softcraft.ohhsansibackend.periodosolimpiada.domain.models.Olimpiada;
import org.springframework.beans.factory.annotation.Autowired;
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
        return olimpiadaService.saveOlimpiada(olimpiada);
    }

    @PutMapping("/update-olimpiada")
    public ResponseEntity<Map<String, Object>> updateOlimpiada(@RequestBody Olimpiada olimpiada) {
        return olimpiadaService.updateOlimpiada(olimpiada);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getOlimpiadas() {
        Map<String, Object> response = olimpiadaService.getOlimpiada();
        return ResponseEntity.ok(response);
    }
}
