package com.softcraft.ohhsansibackend.infraestucture.rest;

import com.softcraft.ohhsansibackend.application.usecases.InscripcionService;
import com.softcraft.ohhsansibackend.domain.models.Inscripcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/inscripcion")
public class InscripcionController {
    private final InscripcionService inscripcionService;

    @Autowired
    public InscripcionController(InscripcionService inscripcionService) {
        this.inscripcionService = inscripcionService;
    }

    @PostMapping("/register-inscripcion")
    public ResponseEntity<Map<String, Object>> createInscripcion(@RequestBody Inscripcion inscripcion) {
        Map<String, Object> response = inscripcionService.saveInscripcion(inscripcion);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getInscripcion(@PathVariable int id) {
        Inscripcion inscripcion = inscripcionService.findInscripcionById(id);
        if(inscripcion != null) {
            return ResponseEntity.ok(Map.of( "data", inscripcion));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("success", false, "message", "Inscripcion not found"));
        }
    }

    @GetMapping
    public ResponseEntity<List<Inscripcion>>listInscripcion() {
        return ResponseEntity.ok(inscripcionService.getInscripciones());
    }

    @GetMapping("/by-date-time")
    public ResponseEntity<List<Inscripcion>> findByDateAndTime(@RequestParam String date, @RequestParam String time) {
        return ResponseEntity.ok(inscripcionService.findByDateAndTime(date, time));
    }

    @GetMapping("/by-range-date")
    public ResponseEntity<List<Inscripcion>> findByRangeDate(@RequestParam String date) {
        return ResponseEntity.ok(inscripcionService.findByRangeDate(date));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateInscripcion(@PathVariable int id, @RequestBody Inscripcion inscripcion) {
        inscripcion.setIdInscripcion(id);
        Map<String, Object> response = inscripcionService.updateInscripcion(inscripcion);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteInscription(@PathVariable int id) {
        Map<String, Object> response = inscripcionService.deleteInscripcion(id);
        return ResponseEntity.ok(response);
    }

}
