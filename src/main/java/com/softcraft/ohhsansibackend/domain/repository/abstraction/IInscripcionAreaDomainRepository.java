package com.softcraft.ohhsansibackend.domain.repository.abstraction;

import com.softcraft.ohhsansibackend.domain.models.InscripcionArea;
import java.util.List;

public interface IInscripcionAreaDomainRepository {
    InscripcionArea insertInscripcionArea(int idInscripcion, int idArea);
    List<InscripcionArea> findAllInscripcionAreas();
    List<InscripcionArea> findByInscripcionId(int idInscripcion);
    List<InscripcionArea> findByAreaId(int idArea);
}
