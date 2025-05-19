package com.softcraft.ohhsansibackend.inscripcion.infraestructure.rest;

import com.softcraft.ohhsansibackend.exception.ResourceNotFoundException;
import com.softcraft.ohhsansibackend.inscripcion.application.usecases.InscripcionService;
import com.softcraft.ohhsansibackend.inscripcion.domain.models.Inscripcion;
import com.softcraft.ohhsansibackend.inscripcion.domain.services.InscripcionMasivaDomainService;
import com.softcraft.ohhsansibackend.inscripcion.infraestructure.dto.FechaRangeRequest;
import jakarta.validation.Valid;
import org.hibernate.type.descriptor.java.ObjectJavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/inscripcion")
public class InscripcionController {
    private final InscripcionService inscripcionService;
    private final InscripcionMasivaDomainService inscripcionMasiva;

    @Autowired
    public InscripcionController(InscripcionService inscripcionService, InscripcionMasivaDomainService inscripcionMasiva) {
        this.inscripcionService = inscripcionService;
        this.inscripcionMasiva = inscripcionMasiva;
    }

    @PostMapping("/register-inscripcion")
    public ResponseEntity<Inscripcion> createInscripcion() {
        Inscripcion response = inscripcionService.saveInscripcion();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getInscripcion(@PathVariable int id) {
        Inscripcion inscripcion = inscripcionService.findInscripcionById(id);
        return ResponseEntity.ok(Map.of("data", inscripcion));
    }

    @GetMapping
    public ResponseEntity<List<Inscripcion>>listInscripcion() {
        return ResponseEntity.ok(inscripcionService.getInscripciones());
    }

    @GetMapping("/details/{codigoUnico}")
    public ResponseEntity<Map<String, Object>> getInscripcionDetails(@PathVariable String codigoUnico) {
        Map<String, Object> details = inscripcionService.getInscripcionDetails(codigoUnico);
        return ResponseEntity.ok(details);
    }

    @GetMapping("/masivo/details/{codUnique}")
    public ResponseEntity<Map<String, Object>> getInscripcionMasivaDetails(@PathVariable String codUnique){
        return inscripcionMasiva.getDetailsInscription(codUnique);
    }

}
