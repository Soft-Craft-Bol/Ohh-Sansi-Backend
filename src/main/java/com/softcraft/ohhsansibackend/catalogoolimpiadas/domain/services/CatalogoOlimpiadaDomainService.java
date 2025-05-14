package com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.services;

import com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.DTO.CatalogoOlimpiadaDTO;
import com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.model.CatalogoOlimpiada;
import com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.repository.abstraction.ICatalogoOlimpiadaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogoOlimpiadaDomainService {
    private final ICatalogoOlimpiadaRepository catalogoOlimpiadaRepository;

    public CatalogoOlimpiadaDomainService(ICatalogoOlimpiadaRepository catalogoOlimpiadaRepository) {
        this.catalogoOlimpiadaRepository = catalogoOlimpiadaRepository;
    }

    public CatalogoOlimpiada save(CatalogoOlimpiada catalogoOlimpiada) {
        return catalogoOlimpiadaRepository.save(catalogoOlimpiada);
    }

    public List<CatalogoOlimpiadaDTO> findAll() {
        return catalogoOlimpiadaRepository.findAll();
    }
}
