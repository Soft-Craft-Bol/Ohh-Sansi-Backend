package com.softcraft.ohhsansibackend.domain.repository.abstraction;

import com.softcraft.ohhsansibackend.domain.models.Area;

import java.util.List;
import java.util.Optional;

public interface AreaRepository {
    void save(Area area);
    void update(Area area);
    void delete(Long idArea);
    Optional<Area> findById(Long idArea);
    List<Area> findAll();
}
