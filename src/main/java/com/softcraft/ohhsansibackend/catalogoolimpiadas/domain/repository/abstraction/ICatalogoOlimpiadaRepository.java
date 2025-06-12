package com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.repository.abstraction;

import com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.DTO.CatalogoOlimpiadaDTO;
import com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.model.CatalogoOlimpiada;

import java.util.List;

public interface ICatalogoOlimpiadaRepository {
    CatalogoOlimpiada save(CatalogoOlimpiada catalogoOlimpiada);

    List<CatalogoOlimpiadaDTO> findAll();

    List<CatalogoOlimpiadaDTO> getCatalogoOlimpiadaById(Integer idCatalogoOlimpiada);

}
