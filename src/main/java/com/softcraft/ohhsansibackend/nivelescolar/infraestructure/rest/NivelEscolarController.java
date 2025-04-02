package com.softcraft.ohhsansibackend.nivelescolar.infraestructure.rest;

import com.softcraft.ohhsansibackend.nivelescolar.application.usecases.NivelEscolarService;
import com.softcraft.ohhsansibackend.nivelescolar.domain.models.NivelEscolar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/nivelescolar")
public class NivelEscolarController {
    private final NivelEscolarService nivelEscolarService;

    @Autowired
    public NivelEscolarController(NivelEscolarService nivelEscolarService) {
        this.nivelEscolarService = nivelEscolarService;
    }

    @PostMapping("/register-nivel")
    public ResponseEntity<Map<String, Object>> addNivelEscolar(@RequestBody NivelEscolar nivelEscolar) {
        Map<String, Object> response = nivelEscolarService.saveNivelEscolar(nivelEscolar);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateNivelEscolar(@PathVariable int id, @RequestBody NivelEscolar nivelEscolar) {
        nivelEscolar.setIdNivel(id);
        Map<String, Object> response = nivelEscolarService.updateNivelEscolar(nivelEscolar);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteNivelEscolar(@PathVariable int id) {
        Map<String, Object> response =nivelEscolarService.deleteNivelEscolar(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getNivelEscolarById(@PathVariable int id) {
        NivelEscolar nivelEscolar = nivelEscolarService.findNivelEscolarById(id);
        if(nivelEscolar != null) {
            return ResponseEntity.ok(Map.of( "data", nivelEscolar));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("success", false, "message", "Nivel Escolar not found"));
        }
    }
    @GetMapping
    public ResponseEntity<List<NivelEscolar>> getNivelEscolar() {
        return ResponseEntity.ok(nivelEscolarService.getNivelEscolars());
    }

}
