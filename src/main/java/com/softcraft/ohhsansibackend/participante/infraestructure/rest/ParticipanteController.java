package com.softcraft.ohhsansibackend.participante.infraestructure.rest;

import com.softcraft.ohhsansibackend.participante.application.usecases.ParticipanteService;
import com.softcraft.ohhsansibackend.participante.domain.models.Participante;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/participante")
public class ParticipanteController {
    private final ParticipanteService participanteService;

    @Autowired
    public ParticipanteController(ParticipanteService participanteService) {
        this.participanteService = participanteService;
    }

    @PostMapping("/register-participant")
    public ResponseEntity<Map<String, Object>> registerParticipant(@Valid @RequestBody Participante participante) {
        Map<String, Object> response = participanteService.save(participante);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Long id) {
        Map<String, Object> response = participanteService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> findAll() {
        Map<String, Object> response = participanteService.findAll();
        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Map<String, Object>> findByEmail(@PathVariable String email) {
        Map<String, Object> response = participanteService.findByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/carnet/{carnetIdentidad}")
    public ResponseEntity<Map<String, Object>> findByCarnetIdentidad(@PathVariable int carnetIdentidad) {
        Map<String, Object> response = participanteService.findByCarnetIdentidad(carnetIdentidad);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}