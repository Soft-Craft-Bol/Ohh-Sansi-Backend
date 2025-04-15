package com.softcraft.ohhsansibackend.catalogoolimpiadas.application.usecases;

import com.softcraft.ohhsansibackend.catalogoolimpiadas.application.adapter.CatalogoOlimpiadaAdapter;
import com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.DTO.CatalogoOlimpiadaDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogoOlimpiadaService {

    private final CatalogoOlimpiadaAdapter catalogoOlimpiadaAdapter;

    public CatalogoOlimpiadaService(CatalogoOlimpiadaAdapter catalogoOlimpiadaAdapter) {
        this.catalogoOlimpiadaAdapter = catalogoOlimpiadaAdapter;
    }

    public List<CatalogoOlimpiadaDTO> findAll() {
        return catalogoOlimpiadaAdapter.findAll();
    }
}
