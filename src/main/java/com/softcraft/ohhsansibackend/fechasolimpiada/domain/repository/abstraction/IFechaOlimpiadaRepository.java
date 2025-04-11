package com.softcraft.ohhsansibackend.fechasolimpiada.domain.repository.abstraction;

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
