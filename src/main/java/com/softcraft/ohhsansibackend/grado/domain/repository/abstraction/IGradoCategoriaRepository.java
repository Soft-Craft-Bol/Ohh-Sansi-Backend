package com.softcraft.ohhsansibackend.grado.domain.repository.abstraction;

import com.softcraft.ohhsansibackend.grado.domain.models.NivelEscolarCategorias;

import java.util.List;

public interface INivelEscolarCategoriasRepository {
    NivelEscolarCategorias save(NivelEscolarCategorias nivelEscolarCategorias);

    boolean update(NivelEscolarCategorias nivelEscolarCategorias);

    boolean delete(int idNivelEscolarCategorias);

    NivelEscolarCategorias findById(int idNivelEscolarCategorias);

    List<NivelEscolarCategorias> findAll();
}