package com.softcraft.ohhsansibackend.fechasolimpiada.domain.repository.abstraction;

import com.softcraft.ohhsansibackend.fechasolimpiada.domain.models.FechaOlimpiada;
import com.softcraft.ohhsansibackend.fechasolimpiada.infraestructure.dto.OlimpiadaEventosDTO;

import java.time.LocalDate;
import java.util.List;

public interface IFechaOlimpiadaRepository {
    FechaOlimpiada upsertFechaOlimpiada(FechaOlimpiada fechaOlimpiada);

    boolean deleteFechaOlimpiada(int idFechaOlimpiada);

    List<FechaOlimpiada> getFechaOlimpiada();

    FechaOlimpiada getFechaOlimpiada(int idFechaOlimpiada);

    FechaOlimpiada getFechaOlimpiadaPublic();

    List<OlimpiadaEventosDTO> findAllOlimpiadasEventos();
}
