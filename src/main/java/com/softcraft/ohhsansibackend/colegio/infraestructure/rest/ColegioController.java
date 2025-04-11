package com.softcraft.ohhsansibackend.colegio.infraestructure.rest;

import com.softcraft.ohhsansibackend.colegio.application.usecases.ColegioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/colegios")
public class ColegioController {
    private final ColegioService colegioService;

    @Autowired
    public ColegioController(ColegioService colegioService) {
        this.colegioService = colegioService;
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
