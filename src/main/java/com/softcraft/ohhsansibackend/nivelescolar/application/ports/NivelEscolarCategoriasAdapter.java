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

    public boolean updateNivelEscolarCategorias(NivelEscolarCategorias nivelEscolarCategorias) {
        return nivelEscolarCategoriasDomainService.updateNivelEscolarCategorias(nivelEscolarCategorias);
    }

    public boolean deleteNivelEscolarCategorias(int id) {
        return nivelEscolarCategoriasDomainService.deleteNivelEscolarCategorias(id);
    }

    public NivelEscolarCategorias findNivelEscolarCategoriasById(int id) {
        return nivelEscolarCategoriasDomainService.findNivelEscolarCategoriasById(id);
    }

    public List<NivelEscolarCategorias> getNivelEscolarCategoriass() {
        return nivelEscolarCategoriasDomainService.getNivelEscolarCategoriass();
    }

}
