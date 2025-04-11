package com.softcraft.ohhsansibackend.fechasolimpiada.domain.repository.abstraction;

import com.softcraft.ohhsansibackend.fechasolimpiada.domain.models.Olimpiada;

import java.util.List;

public interface IOlimpiadaRepository {
    Olimpiada saveOlimpiada(Olimpiada olimpiada);

    boolean deleteOlimpiada(int idOlimpiada);

    Olimpiada getOlimpiada(int idOlimpiada);

    Olimpiada getOlimpiadaPublic();

    List<Olimpiada> getOlimpiadas();
}
