package com.softcraft.ohhsansibackend.domain.repository.abstraction;

import com.softcraft.ohhsansibackend.domain.models.NivelEscolar;

import java.util.List;
import java.util.Optional;

public interface INivelEscolarRepository {
    NivelEscolar save(NivelEscolar nivelEscolar);

    boolean update(NivelEscolar nivelEscolar);

    boolean delete(int idNivel);

    NivelEscolar findById(int idNivel);

    List<NivelEscolar> findAll();
}
