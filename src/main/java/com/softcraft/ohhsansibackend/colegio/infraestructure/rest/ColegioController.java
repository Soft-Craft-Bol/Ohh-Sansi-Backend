package com.softcraft.ohhsansibackend.colegio.infraestructure.rest;

import com.softcraft.ohhsansibackend.colegio.application.usecases.ColegioService;
import com.softcraft.ohhsansibackend.colegio.domain.models.Colegio;
import com.softcraft.ohhsansibackend.departamento.domain.models.Departamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/colegios")
public class ColegioController {
    private final ColegioService colegioService;

    @Autowired
    public ColegioController(ColegioService colegioService) {
        this.colegioService = colegioService;
    }

    @PostMapping("/register-colegio")
    public ResponseEntity<Map<String, Object>> addDepartamentos(@RequestBody List<Colegio> colegios) {
        Map<String, Object> response = colegioService.saveColegios(colegios);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllColegios() {
        Map<String, Object> response = colegioService.getColegios();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/municipio/{idMunicipio}")
    public ResponseEntity<Map<String, Object>> getColegiosByMunicipio(@PathVariable int idMunicipio) {
        Map<String, Object> response = colegioService.getColegiosByMunicipio(idMunicipio);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
