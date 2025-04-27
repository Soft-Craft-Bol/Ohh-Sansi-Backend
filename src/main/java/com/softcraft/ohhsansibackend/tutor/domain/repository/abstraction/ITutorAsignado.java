package com.softcraft.ohhsansibackend.tutor.domain.repository.abstraction;

import com.softcraft.ohhsansibackend.tutor.domain.models.TutorAsigned;

import java.util.List;

public interface ITutorAsignado {
    List<TutorAsigned> findAllTutors(String ci);
}
