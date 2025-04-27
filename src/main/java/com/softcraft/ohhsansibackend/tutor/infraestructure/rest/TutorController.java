package com.softcraft.ohhsansibackend.tutor.infraestructure.rest;

import com.softcraft.ohhsansibackend.tutor.application.usecases.TutorService;
import com.softcraft.ohhsansibackend.tutor.domain.models.Tutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tutor")
public class TutorController {
    private final TutorService tutorService;
    @Autowired
    public TutorController(TutorService tutorService) {
        this.tutorService = tutorService;
    }

    @PostMapping("/{carnetParticipante}")
    public ResponseEntity<Map<String, Object>> save(
            @PathVariable int carnetParticipante,
            @RequestBody List<Tutor> tutors) {
        return ResponseEntity.ok(tutorService.save(tutors, carnetParticipante));
    }

    @GetMapping("/{idTutor}")
    public ResponseEntity<Map<String,Object>> findByIdTutor(
            @PathVariable Integer idTutor
    ) {
        return ResponseEntity.ok(tutorService.findByIdTutor(idTutor));
    }

    @GetMapping
    public ResponseEntity<Map<String,Object>> findAllTutor() {
        return ResponseEntity.ok(tutorService.findAllTutor());
    }

}
