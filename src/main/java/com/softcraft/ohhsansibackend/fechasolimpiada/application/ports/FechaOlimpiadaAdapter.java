package com.softcraft.ohhsansibackend.fechasolimpiada.application.ports;

import com.softcraft.ohhsansibackend.fechasolimpiada.domain.services.FechaOlimpiadaDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public class PlazoInscripcionAdapter {
    private final FechaOlimpiadaDomainService fechaOlimpiadaDomainService;

    @Autowired
    public PlazoInscripcionAdapter(FechaOlimpiadaDomainService fechaOlimpiadaDomainService) {
        this.fechaOlimpiadaDomainService = fechaOlimpiadaDomainService;
    }

    public PlazoInscripcion upsertPlazoInscripcion(PlazoInscripcion plazoInscripcion) {
        return fechaOlimpiadaDomainService.upsertPlazoInscripcion(plazoInscripcion);
    }

    public boolean deletePlazoInscripcion(int id) {
        return fechaOlimpiadaDomainService.deletePlazoInscripcion(id);
    }

    public List<PlazoInscripcion> getPlazosInscripcion() {
        return fechaOlimpiadaDomainService.getPlazosInscripcion();
    }

    public PlazoInscripcion getPlazoInscripcionById(int idPlazoInscripcion) {
        return fechaOlimpiadaDomainService.getPlazoInscripcion(idPlazoInscripcion);
    }

    public PlazoInscripcion getPlazoInscripcionActivo() {
        return fechaOlimpiadaDomainService.getPlazoInscripcionActivo();
    }

    public PlazoInscripcion getPlazoInscripcionByDate(LocalDate date) {
        return fechaOlimpiadaDomainService.getPlazoInscripcionByDate(date);
    }

    public PlazoInscripcion insertPrecioPeriodo(PlazoInscripcion plazoInscripcion) {
        return fechaOlimpiadaDomainService.insertPrecioPeriodo(plazoInscripcion);
    }
}
