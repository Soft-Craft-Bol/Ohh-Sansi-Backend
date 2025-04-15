package com.softcraft.ohhsansibackend.tutor.infraestructure.rest;

import com.softcraft.ohhsansibackend.tutor.application.usecases.TutorAreaParticipanteService;
import com.softcraft.ohhsansibackend.tutor.domain.models.TutorAreaParticipante;
import com.softcraft.ohhsansibackend.tutor.infraestructure.request.TutorAreaParticipanteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tutor-area-participante")
public class TutorAreaParticipanteController {
    private final TutorAreaParticipanteService tutorAreaParticipanteService;

    @Autowired
    public TutorAreaParticipanteController(TutorAreaParticipanteService tutorAreaParticipanteService) {
        this.tutorAreaParticipanteService = tutorAreaParticipanteService;
    }

    @GetMapping("/{ciParticipante}")
    public ResponseEntity<Map<String, Object>> getTutorAreaParticipante(@PathVariable int ciParticipante) {
        Map<String, Object> response = tutorAreaParticipanteService.getTutorAreaParticipanteInfo(ciParticipante);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> saveTutorAreaParticipante(@RequestBody List<TutorAreaParticipanteDTO> tutorAreaParticipantes) {
        Map<String, Object> response = tutorAreaParticipanteService.saveTutorAreaParticipante(tutorAreaParticipantes);
        return ResponseEntity.ok(response);
    }

}
