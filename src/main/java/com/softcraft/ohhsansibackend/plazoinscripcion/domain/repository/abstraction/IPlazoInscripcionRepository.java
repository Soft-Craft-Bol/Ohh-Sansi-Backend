package com.softcraft.ohhsansibackend.plazoinscripcion.domain.repository.abstraction;

import com.softcraft.ohhsansibackend.plazoinscripcion.domain.models.PlazoInscripcion;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface IPlazoInscripcionRepository {
    PlazoInscripcion upsertPlazoInscripcion(PlazoInscripcion plazoInscripcion);



    boolean deletePlazoInscripcion(int idPeriodoInscripcion);

    List<PlazoInscripcion> getPlazosInscripcion();

    PlazoInscripcion getPlazoInscripcion(int idPeriodoInscripcion);

    PlazoInscripcion getPlazoInscripcionActivo();

    PlazoInscripcion getPlazoInscripcionByDate(LocalDate date);

    PlazoInscripcion insertPrecioPeriodo(PlazoInscripcion plazoInscripcion);
}
