package com.softcraft.ohhsansibackend.nivelescolar.infraestructure.rest;

import com.softcraft.ohhsansibackend.nivelescolar.application.usecases.NivelEscolarCategoriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nivelescolar/categorias")
public class NivelEscolarCategoriasController {
    private final NivelEscolarCategoriasService nivelEscolarCategoriasService;

    @Autowired
    public NivelEscolarCategoriasController(NivelEscolarCategoriasService nivelEscolarCategoriasService) {
        this.nivelEscolarCategoriasService = nivelEscolarCategoriasService;
    }

    @GetMapping("/all")
    public NivelEscolarCategoriasService getNivelEscolarCategoriasService() {
        return nivelEscolarCategoriasService;
    }
}
