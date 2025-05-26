package com.softcraft.ohhsansibackend.catalogoolimpiadas.application.adapter;

import com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.DTO.CatalogoOlimpiadaDTO;
import com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.model.CatalogoOlimpiada;
import com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.services.CatalogoOlimpiadaDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CatalogoOlimpiadaAdapter {
    private final CatalogoOlimpiadaDomainService catalogoOlimpiadaDomainService;

    @Autowired
    public CatalogoOlimpiadaAdapter(CatalogoOlimpiadaDomainService catalogoOlimpiadaDomainService) {
        this.catalogoOlimpiadaDomainService = catalogoOlimpiadaDomainService;
    }

    public CatalogoOlimpiada save(CatalogoOlimpiada catalogoOlimpiada) {
        return catalogoOlimpiadaDomainService.save(catalogoOlimpiada);
    }

    public List<CatalogoOlimpiadaDTO> findAll() {
        return catalogoOlimpiadaDomainService.findAll();
    }

    public List<CatalogoOlimpiadaDTO> getCatalogoOlimpiadaById(Integer idCatalogoOlimpiada) {
        return catalogoOlimpiadaDomainService.getCatalogoOlimpiadaById(idCatalogoOlimpiada);
    }
}
