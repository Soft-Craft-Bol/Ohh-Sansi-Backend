package com.softcraft.ohhsansibackend.periodosolimpiada.application.ports;

import com.softcraft.ohhsansibackend.periodosolimpiada.domain.models.PeriodoOlimpiada;
import com.softcraft.ohhsansibackend.periodosolimpiada.domain.services.PeriodoOlimpiadaDomainService;
import com.softcraft.ohhsansibackend.periodosolimpiada.infraestructure.dto.OlimpiadaEventosDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PeriodoOlimpiadaAdapter {
    private final PeriodoOlimpiadaDomainService periodoOlimpiadaDomainService;

    @Autowired
    public PeriodoOlimpiadaAdapter(PeriodoOlimpiadaDomainService periodoOlimpiadaDomainService) {
        this.periodoOlimpiadaDomainService = periodoOlimpiadaDomainService;
    }

    public PeriodoOlimpiada insertPeriodoOlimpiada(PeriodoOlimpiada periodoOlimpiada) {
        return periodoOlimpiadaDomainService.insertPeriodoOlimpiada(periodoOlimpiada);
    }

    public List<OlimpiadaEventosDTO> getOlimpiadasconEventos() {
        return periodoOlimpiadaDomainService.getOlimpiadasconEventos();
    }
}
