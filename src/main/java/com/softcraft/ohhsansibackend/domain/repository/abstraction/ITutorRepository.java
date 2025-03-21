package com.softcraft.ohhsansibackend.domain.repository.abstraction;

import com.softcraft.ohhsansibackend.domain.models.Tutor;

import java.util.List;

public interface ITutorRepository {
    Tutor save(Tutor tutor);
    Tutor findByIdTutor(int idTutor);
    List<Tutor> findAllTutor();
}
