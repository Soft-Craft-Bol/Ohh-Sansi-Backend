package com.softcraft.ohhsansibackend.nivelescolar.infraestructure.rest;

import com.softcraft.ohhsansibackend.nivelescolar.application.NivelEscolarCategoriaAreaService;
import com.softcraft.ohhsansibackend.nivelescolar.infraestructure.request.NivelEscolarCategoriaAreaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nivelescolar-categoria-area")
public class NivelEscolarCategoriaAreaController {

    private final NivelEscolarCategoriaAreaService nivelEscolarCategoriaAreaService;

    @Autowired
    public NivelEscolarCategoriaAreaController(NivelEscolarCategoriaAreaService nivelEscolarCategoriaAreaService) {
        this.nivelEscolarCategoriaAreaService = nivelEscolarCategoriaAreaService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> addNivelEscolarCategoriaArea(@RequestBody NivelEscolarCategoriaAreaDTO dto) {
        nivelEscolarCategoriaAreaService.proccessNivelEscolarCategoriaArea(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}