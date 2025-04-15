package com.softcraft.ohhsansibackend.catalogoolimpiadas.infraestructure.rest;

import com.softcraft.ohhsansibackend.catalogoolimpiadas.application.usecases.CatalogoOlimpiadaService;
import com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.DTO.CatalogoOlimpiadaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catalogo-olimpiada")
public class CatalogoOlimpiadaController {
    private final CatalogoOlimpiadaService catalogoOlimpiadaService;

    @Autowired
    public CatalogoOlimpiadaController(CatalogoOlimpiadaService catalogoOlimpiadaService) {
        this.catalogoOlimpiadaService = catalogoOlimpiadaService;
    }

    @GetMapping
    public List<CatalogoOlimpiadaDTO> getCatalogoOlimpiadas() {
        return catalogoOlimpiadaService.findAll();
    }
}
