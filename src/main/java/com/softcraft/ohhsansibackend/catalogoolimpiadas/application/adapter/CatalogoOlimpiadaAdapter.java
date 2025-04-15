package com.softcraft.ohhsansibackend.catalogoolimpiadas.application.adapter;

import com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.DTO.CatalogoOlimpiadaDTO;
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

    public List<CatalogoOlimpiadaDTO> findAll() {
        return catalogoOlimpiadaDomainService.findAll();
    }
}
