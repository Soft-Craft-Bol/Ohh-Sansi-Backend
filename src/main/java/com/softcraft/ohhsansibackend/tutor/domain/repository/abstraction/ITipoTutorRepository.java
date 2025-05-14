package com.softcraft.ohhsansibackend.tutor.domain.repository.abstraction;
import com.softcraft.ohhsansibackend.tutor.domain.models.TipoTutor;

import java.util.List;

public interface ITipoTutorRepository {
    TipoTutor save(TipoTutor tipoTutor);
    TipoTutor update(TipoTutor tipoTutor);
    void delete(int idTipoTutor);
    TipoTutor findByIdTipoTutor(int idTipoTutor);
    List<TipoTutor> findAllTipoTutor();
}