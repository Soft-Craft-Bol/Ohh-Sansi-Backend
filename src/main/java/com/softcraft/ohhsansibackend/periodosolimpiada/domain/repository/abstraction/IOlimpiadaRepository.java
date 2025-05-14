package com.softcraft.ohhsansibackend.periodosolimpiada.domain.repository.abstraction;

import com.softcraft.ohhsansibackend.periodosolimpiada.domain.models.Olimpiada;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IOlimpiadaRepository {
    Olimpiada saveOlimpiada(Olimpiada olimpiada);

    boolean deleteOlimpiada(int idOlimpiada);

    List<Olimpiada> getOlimpiadas();

    boolean updatePrecioOlimpiada(int idOlimpiada, BigDecimal precioOlimpiada);

    Optional<Olimpiada> findById(Integer idOlimpiada);
}
