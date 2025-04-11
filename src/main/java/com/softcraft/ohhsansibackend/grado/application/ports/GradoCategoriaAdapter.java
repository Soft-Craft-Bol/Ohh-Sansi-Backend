package com.softcraft.ohhsansibackend.grado.application.ports;

import com.softcraft.ohhsansibackend.grado.domain.models.NivelEscolarCategorias;
import com.softcraft.ohhsansibackend.grado.domain.services.NivelEscolarCategoriasDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NivelEscolarCategoriasAdapter {
    private final NivelEscolarCategoriasDomainService nivelEscolarCategoriasDomainService;

    @Autowired
    public NivelEscolarCategoriasAdapter(NivelEscolarCategoriasDomainService nivelEscolarCategoriasDomainService) {
        this.nivelEscolarCategoriasDomainService = nivelEscolarCategoriasDomainService;
    }

    public NivelEscolarCategorias saveNivelEscolarCategorias(NivelEscolarCategorias nivelEscolarCategorias) {
        return nivelEscolarCategoriasDomainService.createNivelEscolarCategorias(nivelEscolarCategorias);
    }

}
