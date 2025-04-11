package com.softcraft.ohhsansibackend.nivelescolar.infraestructure.rest;

import com.softcraft.ohhsansibackend.nivelescolar.application.NivelEscolarCategoriaAreaService;
import com.softcraft.ohhsansibackend.nivelescolar.application.usecases.AreaNivelEscolarService;
import com.softcraft.ohhsansibackend.nivelescolar.application.usecases.NivelEscolarCategoriaService;
import com.softcraft.ohhsansibackend.nivelescolar.infraestructure.request.NivelEscolarCategoriaAreaDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/nivelescolar-categoria-area")
public class NivelEscolarCategoriaAreaController {

    private final NivelEscolarCategoriaAreaService nivelEscolarCategoriaAreaService;
    private final AreaNivelEscolarService areaNivelEscolarService;


    @Autowired
    public NivelEscolarCategoriaAreaController(NivelEscolarCategoriaAreaService nivelEscolarCategoriaAreaService, AreaNivelEscolarService areaNivelEscolarService) {
        this.nivelEscolarCategoriaAreaService = nivelEscolarCategoriaAreaService;
        this.areaNivelEscolarService = areaNivelEscolarService;

    }

    @PostMapping("/register")
    public ResponseEntity<Void> addNivelEscolarCategoriaArea(@Valid @RequestBody NivelEscolarCategoriaAreaDTO dto) {
        nivelEscolarCategoriaAreaService.proccessNivelEscolarCategoriaArea(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/areas-categorias/{idNivel}")
    public ResponseEntity<Map<String, Object>> getAreasAndCategoriasByNivel(@PathVariable int idNivel) {
        Map<String, Object> result = areaNivelEscolarService.getAreasAndCategoriasByNivel(idNivel);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/areas-grados")
    public ResponseEntity<Map<String, Object>> getAreasGrados() {
        Map<String, Object> result = areaNivelEscolarService.getAreasGrados();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/areas-categorias")
    public ResponseEntity<Map<String, Object>> getAreasAndCategorias() {
        Map<String, Object> result = areaNivelEscolarService.getAreasAndCategorias();
        return ResponseEntity.ok(result);
    }
}