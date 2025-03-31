package com.softcraft.ohhsansibackend.plazoinscripcion.domain.services;

import com.softcraft.ohhsansibackend.plazoinscripcion.domain.models.PlazoInscripcion;
import com.softcraft.ohhsansibackend.plazoinscripcion.domain.repository.abstraction.IPlazoInscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class PlazoInscripcionDomainService {
    private final IPlazoInscripcionRepository plazoInscripcionRepository;

    @Autowired
    public PlazoInscripcionDomainService(IPlazoInscripcionRepository plazoInscripcionRepository) {
        this.plazoInscripcionRepository = plazoInscripcionRepository;
    }

    public PlazoInscripcion upsertPlazoInscripcion(PlazoInscripcion plazoInscripcion) {
        return plazoInscripcionRepository.upsertPlazoInscripcion(plazoInscripcion);
    }

    public PlazoInscripcion createPlazoInscripcion(PlazoInscripcion plazoInscripcion) {
        return plazoInscripcionRepository.insertPlazoInscripcion( plazoInscripcion);
    }

    public PlazoInscripcion updatePlazoInscripcion(PlazoInscripcion plazoInscripcion) {
        return plazoInscripcionRepository.updatePlazoInscripcion(plazoInscripcion);
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
}
