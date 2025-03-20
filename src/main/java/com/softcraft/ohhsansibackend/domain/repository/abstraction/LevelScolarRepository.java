package com.softcraft.ohhsansibackend.domain.repository.abstraction;

import com.softcraft.ohhsansibackend.domain.models.LevelScolar;

import java.util.List;
import java.util.Optional;

public interface LevelScolarRepository {
    void save(LevelScolar levelScolar);

    void update(LevelScolar levelScolar);

    void delete(Long idLevel);

    Optional<LevelScolar> findById(Long idLevel);

    List<LevelScolar> findAll();
}
