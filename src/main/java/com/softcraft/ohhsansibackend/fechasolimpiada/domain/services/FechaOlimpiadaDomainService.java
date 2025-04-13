package com.softcraft.ohhsansibackend.fechasolimpiada.domain.services;

import com.softcraft.ohhsansibackend.fechasolimpiada.domain.models.FechaOlimpiada;
import com.softcraft.ohhsansibackend.fechasolimpiada.domain.repository.abstraction.IFechaOlimpiadaRepository;
import com.softcraft.ohhsansibackend.fechasolimpiada.infraestructure.dto.OlimpiadaEventosDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class FechaOlimpiadaDomainService {
    private final IFechaOlimpiadaRepository fechaOlimpiadaRepository;

    @Autowired
    public FechaOlimpiadaDomainService(IFechaOlimpiadaRepository fechaOlimpiadaRepository) {
        this.fechaOlimpiadaRepository = fechaOlimpiadaRepository;
    }

    public FechaOlimpiada upsertFechaOlimpiada(FechaOlimpiada fechaOlimpiada) {
        return fechaOlimpiadaRepository.upsertFechaOlimpiada(fechaOlimpiada);
    }

    public boolean deleteFechaOlimpiada(int id) {
        return fechaOlimpiadaRepository.deleteFechaOlimpiada(id);
    }

    public List<FechaOlimpiada> getFechaOlimpiada() {
        return fechaOlimpiadaRepository.getFechaOlimpiada();
    }

    public FechaOlimpiada getFechaOlimpiadaById(int id) {
        return fechaOlimpiadaRepository.getFechaOlimpiada(id);
    }

    public FechaOlimpiada getFechaOlimpiadaPublic() {
        return fechaOlimpiadaRepository.getFechaOlimpiadaPublic();
    }

    public List<OlimpiadaEventosDTO> getOlimpiadasconEventos() {
        return fechaOlimpiadaRepository.findAllOlimpiadasEventos();
    }
}
