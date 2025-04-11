package com.softcraft.ohhsansibackend.plazoinscripcion.application.ports;

import com.softcraft.ohhsansibackend.plazoinscripcion.domain.models.PlazoInscripcion;
import com.softcraft.ohhsansibackend.plazoinscripcion.domain.services.PlazoInscripcionDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public class PlazoInscripcionAdapter {
    private final PlazoInscripcionDomainService plazoInscripcionDomainService;

    @Autowired
    public PlazoInscripcionAdapter(PlazoInscripcionDomainService plazoInscripcionDomainService) {
        this.plazoInscripcionDomainService = plazoInscripcionDomainService;
    }

    public PlazoInscripcion upsertPlazoInscripcion(PlazoInscripcion plazoInscripcion) {
        return plazoInscripcionDomainService.upsertPlazoInscripcion(plazoInscripcion);
    }

    public boolean deletePlazoInscripcion(int id) {
        return plazoInscripcionDomainService.deletePlazoInscripcion(id);
    }

    public List<PlazoInscripcion> getPlazosInscripcion() {
        return plazoInscripcionDomainService.getPlazosInscripcion();
    }

    public PlazoInscripcion getPlazoInscripcionById(int idPlazoInscripcion) {
        return plazoInscripcionDomainService.getPlazoInscripcion(idPlazoInscripcion);
    }

    public PlazoInscripcion getPlazoInscripcionActivo() {
        return plazoInscripcionDomainService.getPlazoInscripcionActivo();
    }

    public PlazoInscripcion getPlazoInscripcionByDate(LocalDate date) {
        return plazoInscripcionDomainService.getPlazoInscripcionByDate(date);
    }

    public PlazoInscripcion insertPrecioPeriodo(PlazoInscripcion plazoInscripcion) {
        return plazoInscripcionDomainService.insertPrecioPeriodo(plazoInscripcion);
    }
}
