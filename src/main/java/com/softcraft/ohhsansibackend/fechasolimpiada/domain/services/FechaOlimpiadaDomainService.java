package com.softcraft.ohhsansibackend.fechasolimpiada.domain.services;

import com.softcraft.ohhsansibackend.fechasolimpiada.domain.repository.abstraction.IFechaOlimpiadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class PlazoInscripcionDomainService {
    private final IFechaOlimpiadaRepository fechaOlimpiadaRepository;

    @Autowired
    public PlazoInscripcionDomainService(IFechaOlimpiadaRepository fechaOlimpiadaRepository) {
        this.fechaOlimpiadaRepository = fechaOlimpiadaRepository;
    }

    public PlazoInscripcion upsertPlazoInscripcion(PlazoInscripcion plazoInscripcion) {
        return plazoInscripcionRepository.upsertPlazoInscripcion(plazoInscripcion);
    }

    public boolean deletePlazoInscripcion(int id) {
        return plazoInscripcionRepository.deletePlazoInscripcion(id);
    }

    public List<PlazoInscripcion> getPlazosInscripcion() {
        return plazoInscripcionRepository.getPlazosInscripcion();
    }

    public PlazoInscripcion getPlazoInscripcion(int id) {
        return plazoInscripcionRepository.getPlazoInscripcion(id);
    }

    public PlazoInscripcion getPlazoInscripcionActivo() {
        return plazoInscripcionRepository.getPlazoInscripcionActivo();
    }

    public PlazoInscripcion getPlazoInscripcionByDate(LocalDate date) {
        return plazoInscripcionRepository.getPlazoInscripcionByDate(date);
    }

    public PlazoInscripcion insertPrecioPeriodo(PlazoInscripcion plazoInscripcion) {
        return plazoInscripcionRepository.insertPrecioPeriodo(plazoInscripcion);
    }
}
