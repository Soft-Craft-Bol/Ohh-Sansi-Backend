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

    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> save(@RequestBody CatalogoOlimpiada catalogoOlimpiada) {
        Map<String, Object> response = catalogoOlimpiadaService.save(catalogoOlimpiada);

        if ("success".equals(response.get("status"))) {
            return ResponseEntity.ok(
                    Map.of(
                            "status", "success",
                            "message", response.get("message"),
                            "data", response.get("data")
                    )
            );
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Map.of(
                            "status", "error",
                            "message", response.get("message")
                    )
            );
        }
    }


    @GetMapping
    public List<CatalogoOlimpiadaDTO> getCatalogoOlimpiadas() {
        return catalogoOlimpiadaService.findAll();
    }


}
