package com.softcraft.ohhsansibackend.grado.infraestructure.rest;

import com.softcraft.ohhsansibackend.grado.application.usecases.GradoService;
import com.softcraft.ohhsansibackend.grado.domain.models.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/nivelescolar")
public class GradoController {
    private final GradoService gradoService;

    @Autowired
    public GradoController(GradoService gradoService) {
        this.gradoService = gradoService;
    }

    @PostMapping("/register-nivel")
    public ResponseEntity<Map<String, Object>> addGrado(@RequestBody Grade grade) {
        Map<String, Object> response = gradoService.saveGrade(grade);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateGrado(@PathVariable int id, @RequestBody Grade grade) {
        grade.setIdGrado(id);
        Map<String, Object> response = gradoService.updateGrade(grade);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteNivelEscolar(@PathVariable int id) {
        Map<String, Object> response = gradoService.deleteGrade(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getNivelEscolarById(@PathVariable int id) {
        Grade grade = gradoService.findGradeById(id);
        if(grade != null) {
            return ResponseEntity.ok(Map.of( "data", grade));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("success", false, "message", "Nivel Escolar not found"));
        }
    }
}
