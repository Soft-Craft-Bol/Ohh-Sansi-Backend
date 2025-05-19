package com.softcraft.ohhsansibackend.inscripcion.domain.repository.abstraction;

import com.softcraft.ohhsansibackend.inscripcion.domain.models.InscripcionMasivaDetail;

public interface IInscripcionMasivaDetail {
    InscripcionMasivaDetail getAllDetails(String codUnico);
}
