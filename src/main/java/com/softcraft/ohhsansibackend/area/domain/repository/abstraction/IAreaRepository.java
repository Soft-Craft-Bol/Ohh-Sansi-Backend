package com.softcraft.ohhsansibackend.area.domain.repository.abstraction;

import com.softcraft.ohhsansibackend.area.domain.models.Area;
import com.softcraft.ohhsansibackend.domain.IDatabaseRepository;


public interface IAreaRepository extends IDatabaseRepository<Area, Integer> {
    boolean update(Area area);
    boolean delete(int idArea);

    Area findById(int idArea);
}
