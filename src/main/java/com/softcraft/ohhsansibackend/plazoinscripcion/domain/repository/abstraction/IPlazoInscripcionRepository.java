package com.softcraft.ohhsansibackend.plazoinscripcion.domain.repository.abstraction;

import com.softcraft.ohhsansibackend.plazoinscripcion.domain.models.PlazoInscripcion;
import java.time.LocalDate;

public interface IPlazoInscripcionRepository {
    PlazoInscripcion upsertPlazoInscripcion(PlazoInscripcion plazoInscripcion);

    PlazoInscripcion insertPlazoInscripcion(PlazoInscripcion plazoInscripcion);

    PlazoInscripcion updatePlazoInscripcion(PlazoInscripcion plazoInscripcion);

    boolean deletePlazoInscripcion(int idPlazoInscripcion);

    PlazoInscripcion getPlazoInscripcion(int idPlazoInscripcion);

    PlazoInscripcion getPlazoInscripcionActivo();

    PlazoInscripcion getPlazoInscripcionByDate(LocalDate date);
}
