package com.softcraft.ohhsansibackend.periodosolimpiada.domain.repository.abstraction;

import com.softcraft.ohhsansibackend.periodosolimpiada.domain.models.Olimpiada;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IOlimpiadaRepository {
    Olimpiada saveOlimpiada(Olimpiada olimpiada);

    Olimpiada updateOlimpiada(Olimpiada olimpiada);

    List<Olimpiada> getOlimpiadas();

    Optional<Olimpiada> findById(Integer idOlimpiada);
}
