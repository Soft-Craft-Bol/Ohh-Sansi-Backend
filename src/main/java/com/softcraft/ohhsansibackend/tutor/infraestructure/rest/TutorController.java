package com.softcraft.ohhsansibackend.tutor.infraestructure.rest;

import com.softcraft.ohhsansibackend.tutor.application.usecases.TutorService;
import com.softcraft.ohhsansibackend.tutor.domain.models.Tutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/tutor")
public class TutorController {
    private final TutorService tutorService;
    @Autowired
    public TutorController(TutorService tutorService) {
        this.tutorService = tutorService;
    }
    @PostMapping
    public ResponseEntity<Map<String,Object>> save(@RequestBody Tutor tutor) {
        return ResponseEntity.ok(tutorService.save(tutor));
    }
    @GetMapping("/{idTutor}")
    public ResponseEntity<Map<String,Object>> findByIdTutor(int idTutor) {
        return ResponseEntity.ok(tutorService.findByIdTutor(idTutor));
    }
    @GetMapping
    public ResponseEntity<Map<String,Object>> findAllTutor() {
        return ResponseEntity.ok(tutorService.findAllTutor());
    }

}
