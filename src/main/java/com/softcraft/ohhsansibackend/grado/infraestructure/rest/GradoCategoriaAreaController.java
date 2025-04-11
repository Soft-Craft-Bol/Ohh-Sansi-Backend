package com.softcraft.ohhsansibackend.grado.infraestructure.rest;

import com.softcraft.ohhsansibackend.grado.application.GradoCategoriaAreaService;
import com.softcraft.ohhsansibackend.grado.infraestructure.request.GradoCategoriaAreaDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/grado-categoria-area")
public class GradoCategoriaAreaController {

    private final GradoCategoriaAreaService gradoCategoriaAreaService;

    @Autowired
    public GradoCategoriaAreaController(GradoCategoriaAreaService gradoCategoriaAreaService) {
        this.gradoCategoriaAreaService = gradoCategoriaAreaService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> addGradoCategoriaArea(@Valid @RequestBody GradoCategoriaAreaDTO dto) {
        gradoCategoriaAreaService.proccessGradoCategoriaArea(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}