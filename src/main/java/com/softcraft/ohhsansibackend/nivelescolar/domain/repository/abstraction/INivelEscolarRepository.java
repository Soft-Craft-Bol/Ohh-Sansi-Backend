package com.softcraft.ohhsansibackend.nivelescolar.domain.repository.abstraction;

import com.softcraft.ohhsansibackend.nivelescolar.domain.models.NivelEscolar;

import java.util.List;

public interface INivelEscolarRepository {
    NivelEscolar save(NivelEscolar nivelEscolar);

    boolean update(NivelEscolar nivelEscolar);

    boolean delete(int idNivel);

    NivelEscolar findById(int idNivel);

    List<NivelEscolar> findAll();
}
