package com.softcraft.ohhsansibackend.periodosolimpiada.domain.repository.abstraction;

import com.softcraft.ohhsansibackend.periodosolimpiada.domain.models.PeriodoOlimpiada;
import com.softcraft.ohhsansibackend.periodosolimpiada.infraestructure.dto.OlimpiadaEventosDTO;

import java.util.List;

public interface IPeriodoOlimpiadaRepository {
    PeriodoOlimpiada insertPeriodoOlimpiada(PeriodoOlimpiada periodoOlimpiada);

    List<OlimpiadaEventosDTO> findAllOlimpiadasEventos();

    PeriodoOlimpiada actualizarPeriodo(PeriodoOlimpiada periodoOlimpiada);
}
