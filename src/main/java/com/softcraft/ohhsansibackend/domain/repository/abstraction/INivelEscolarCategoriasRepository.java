package com.softcraft.ohhsansibackend.nivelescolar.domain.repository.abstraction;

import com.softcraft.ohhsansibackend.nivelescolar.domain.models.NivelEscolarCategorias;

import java.util.List;

public interface INivelEscolarCategoriasRepository {
    NivelEscolarCategorias save(NivelEscolarCategorias nivelEscolarCategorias);

    boolean update(NivelEscolarCategorias nivelEscolarCategorias);

    boolean delete(int idNivelEscolarCategorias);

    NivelEscolarCategorias findById(int idNivelEscolarCategorias);

    List<NivelEscolarCategorias> findAll();
}
