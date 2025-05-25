package com.softcraft.ohhsansibackend.catalogoolimpiadas.infraestructure;

import com.softcraft.ohhsansibackend.catalogoolimpiadas.application.CatalogoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/catalogo")
public class CatalogoController {
    private final CatalogoService catalogoService;

    public CatalogoController(CatalogoService catalogoService) {
        this.catalogoService = catalogoService;
    }
    @GetMapping("/ci-participante/{ciParticipante}")
    public ResponseEntity<List<Map<String, Object>>> getAreasByGrado(@PathVariable int ciParticipante) {
        List<Map<String, Object>> areas = catalogoService.getAreasByGradoParticipante(ciParticipante);
        return ResponseEntity.ok(areas);
    }
}
