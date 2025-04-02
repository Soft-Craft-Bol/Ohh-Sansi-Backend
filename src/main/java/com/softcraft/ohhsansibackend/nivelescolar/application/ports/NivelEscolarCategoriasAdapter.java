package com.softcraft.ohhsansibackend.nivelescolar.application.ports;

import com.softcraft.ohhsansibackend.nivelescolar.domain.models.NivelEscolarCategorias;
import com.softcraft.ohhsansibackend.nivelescolar.domain.services.NivelEscolarCategoriasDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
