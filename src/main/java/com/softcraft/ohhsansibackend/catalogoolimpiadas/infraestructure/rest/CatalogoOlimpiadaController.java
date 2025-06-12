package com.softcraft.ohhsansibackend.catalogoolimpiadas.infraestructure.rest;

import com.softcraft.ohhsansibackend.catalogoolimpiadas.application.usecases.CatalogoOlimpiadaService;
import com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.DTO.CatalogoOlimpiadaDTO;
import com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.model.CatalogoOlimpiada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/catalogo-olimpiada")
public class CatalogoOlimpiadaController {
    private final CatalogoOlimpiadaService catalogoOlimpiadaService;

    @Autowired
    public CatalogoOlimpiadaController(CatalogoOlimpiadaService catalogoOlimpiadaService) {
        this.catalogoOlimpiadaService = catalogoOlimpiadaService;
    }

    @PutMapping("/save")
    public ResponseEntity<Map<String, Object>> save(@RequestBody CatalogoOlimpiada catalogoOlimpiada) {
        Map<String, Object> response = catalogoOlimpiadaService.save(catalogoOlimpiada);

        HttpStatus status = "success".equals(response.get("status")) ?
                HttpStatus.OK : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(status).body(response);
    }


    @GetMapping
    public List<CatalogoOlimpiadaDTO> getCatalogoOlimpiadas() {
        return catalogoOlimpiadaService.findAll();
    }


}
