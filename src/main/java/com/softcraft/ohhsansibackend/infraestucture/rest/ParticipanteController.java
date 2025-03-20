package com.softcraft.ohhsansibackend.infraestucture.rest;

import com.softcraft.ohhsansibackend.application.usecases.ParticipanteService;
import java.util.Map;

import com.softcraft.ohhsansibackend.domain.models.Participante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/participante")
public class ParticipanteController {
    private final ParticipanteService participanteService;
    @Autowired
    public ParticipanteController(ParticipanteService participanteService) {
        this.participanteService = participanteService;
    }
    @PostMapping("/register-participant")
    public ResponseEntity<Map<String,Object>> registerParticipant(@RequestBody Participante participante) {
        Map<String,Object> response = participanteService.save(participante);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
