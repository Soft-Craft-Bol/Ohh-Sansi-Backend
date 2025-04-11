package com.softcraft.ohhsansibackend.grado.infraestructure.rest;

import com.softcraft.ohhsansibackend.grado.application.GradoCategoriaAreaService;
import com.softcraft.ohhsansibackend.grado.infraestructure.request.GradoCategoriaAreaDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/nivelescolar-categoria-area")
public class NivelEscolarCategoriaAreaController {

    private final GradoCategoriaAreaService gradoCategoriaAreaService;
    
    @Autowired
    public NivelEscolarCategoriaAreaController(GradoCategoriaAreaService gradoCategoriaAreaService) {
        this.gradoCategoriaAreaService = gradoCategoriaAreaService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> addNivelEscolarCategoriaArea(@Valid @RequestBody GradoCategoriaAreaDTO dto) {
        gradoCategoriaAreaService.proccessNivelEscolarCategoriaArea(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}