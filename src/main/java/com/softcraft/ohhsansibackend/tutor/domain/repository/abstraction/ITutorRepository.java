package com.softcraft.ohhsansibackend.tutor.domain.repository.abstraction;

import com.softcraft.ohhsansibackend.tutor.domain.models.Tutor;

import java.util.List;

public interface ITutorRepository {
    Tutor save(Tutor tutor);
    Tutor findByIdTutor(int idTutor);
    List<Tutor> findAllTutor();
}