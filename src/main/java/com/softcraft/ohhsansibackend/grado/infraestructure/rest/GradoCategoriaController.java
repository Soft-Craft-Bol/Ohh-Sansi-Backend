package com.softcraft.ohhsansibackend.grado.infraestructure.rest;

import com.softcraft.ohhsansibackend.grado.application.GradoCategoriaAreaService;
import com.softcraft.ohhsansibackend.grado.application.usecases.GradoCategoriaService;
import com.softcraft.ohhsansibackend.grado.domain.models.GradoCategoria;
import com.softcraft.ohhsansibackend.grado.infraestructure.request.GradoCategoriaDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grado-categoria")
public class GradoCategoriaController {

    private final GradoCategoriaAreaService gradoCategoriaAreaService;
    private final GradoCategoriaService gradoCategoriaService;

    @Autowired
    public GradoCategoriaController(GradoCategoriaAreaService gradoCategoriaAreaService, GradoCategoriaService gradoCategoriaService) {
        this.gradoCategoriaAreaService = gradoCategoriaAreaService;
        this.gradoCategoriaService = gradoCategoriaService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> addGradoCategoriaArea(@Valid @RequestBody GradoCategoriaDTO dto) {
        gradoCategoriaAreaService.processCategoriasGrados(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<GradoCategoriaDTO>> getGradoCategoriaArea() {
        List<GradoCategoriaDTO> gradoCategorias = gradoCategoriaService.getGradoCategorias();
        return ResponseEntity.ok(gradoCategorias);
    }

}