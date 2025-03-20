package com.softcraft.ohhsansibackend.infraestucture.rest;

import com.softcraft.ohhsansibackend.application.usecases.ParticipanteService;
import java.util.Map;

import com.softcraft.ohhsansibackend.domain.models.Participante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/{id}")
    public ResponseEntity<Map<String,Object>> findById(@PathVariable Long id){
        Map<String,Object> response = participanteService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<Map<String,Object>> findAll(){
        Map<String,Object> response = participanteService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<Map<String,Object>> findByEmail(@PathVariable String email){
        Map<String,Object> response = participanteService.findByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
