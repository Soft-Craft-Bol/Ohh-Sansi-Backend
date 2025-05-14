package com.softcraft.ohhsansibackend.periodosolimpiada.domain.services;

import com.softcraft.ohhsansibackend.periodosolimpiada.domain.models.PeriodoOlimpiada;
import com.softcraft.ohhsansibackend.periodosolimpiada.domain.repository.abstraction.IPeriodoOlimpiadaRepository;
import com.softcraft.ohhsansibackend.periodosolimpiada.infraestructure.dto.OlimpiadaEventosDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeriodoOlimpiadaDomainService {
    private final IPeriodoOlimpiadaRepository periodoOlimpiadaRepository;

    @Autowired
    public PeriodoOlimpiadaDomainService(IPeriodoOlimpiadaRepository periodoOlimpiadaRepository) {
        this.periodoOlimpiadaRepository = periodoOlimpiadaRepository;
    }

    public PeriodoOlimpiada insertPeriodoOlimpiada(PeriodoOlimpiada periodoOlimpiada) {
        return periodoOlimpiadaRepository.insertPeriodoOlimpiada(periodoOlimpiada);
    }

    public List<OlimpiadaEventosDTO> getOlimpiadasconEventos() {
        return periodoOlimpiadaRepository.findAllOlimpiadasEventos();
    }
}
