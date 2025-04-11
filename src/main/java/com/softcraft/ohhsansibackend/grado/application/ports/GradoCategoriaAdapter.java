package com.softcraft.ohhsansibackend.grado.application.ports;

import com.softcraft.ohhsansibackend.grado.domain.models.GradoCategoria;
import com.softcraft.ohhsansibackend.grado.domain.services.GradoCategoriaDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GradoCategoriaAdapter {
    private final GradoCategoriaDomainService gradoCategoriaDomainService;

    @Autowired
    public GradoCategoriaAdapter(GradoCategoriaDomainService gradoCategoriaDomainService) {
        this.gradoCategoriaDomainService = gradoCategoriaDomainService;
    }

    public GradoCategoria saveGradoCategoria(GradoCategoria gradoCategoria) {
        return gradoCategoriaDomainService.createGradoCategoria(gradoCategoria);
    }

}
