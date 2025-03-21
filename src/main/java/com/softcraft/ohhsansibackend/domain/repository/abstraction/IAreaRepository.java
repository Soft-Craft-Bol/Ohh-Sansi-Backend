package com.softcraft.ohhsansibackend.domain.repository.abstraction;

import com.softcraft.ohhsansibackend.domain.models.Area;

public interface IAreaRepository extends IDatabaseRepository<Area, Integer> {
    boolean update(Area area);
    boolean delete(int idArea);

    Area findById(int idArea);
}
