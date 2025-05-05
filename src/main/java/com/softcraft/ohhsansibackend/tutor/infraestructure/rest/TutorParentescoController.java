package com.softcraft.ohhsansibackend.tutor.infraestructure.rest;

import com.softcraft.ohhsansibackend.tutor.application.usecases.TutorParentescoService;
import com.softcraft.ohhsansibackend.tutor.domain.models.TutorParentesco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@RestController
@RequestMapping("/parentesco")
public class TutorParentescoController {
    private final TutorParentescoService tutorParentescoService;

    @Autowired
    public TutorParentescoController(TutorParentescoService tutorParentescoService){
        this.tutorParentescoService = tutorParentescoService;
    }

    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> save(@RequestBody TutorParentesco tutorParentesco) {
        return ResponseEntity.ok(tutorParentescoService.save(tutorParentesco));
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> update(@RequestBody TutorParentesco tutorParentesco) {
        return ResponseEntity.ok(tutorParentescoService.update(tutorParentesco));
    }

    @DeleteMapping("/delete/{idTutorParentesco}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable int idTutorParentesco) {
        return ResponseEntity.ok(tutorParentescoService.delete(idTutorParentesco));
    }

    @GetMapping("/{idTutorParentesco}")
    public ResponseEntity<Map<String, Object>> findByIdTutorParentesco(@PathVariable int idTutorParentesco) {
        return ResponseEntity.ok(tutorParentescoService.findByIdTutorParentesco(idTutorParentesco));
    }

    @GetMapping("/findAllParentescos")
    public ResponseEntity<Map<String, Object>> findAllParentescos() {
        return ResponseEntity.ok(tutorParentescoService.findAllParentescos());
    }
}
