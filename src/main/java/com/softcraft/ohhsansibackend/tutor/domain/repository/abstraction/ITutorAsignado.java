package com.softcraft.ohhsansibackend.tutor.domain.repository.abstraction;

import com.softcraft.ohhsansibackend.tutor.domain.models.Tutor;

import java.util.List;

public interface ITutorAsignado {
    List<Tutor> findAllTutors(String ci);
}
