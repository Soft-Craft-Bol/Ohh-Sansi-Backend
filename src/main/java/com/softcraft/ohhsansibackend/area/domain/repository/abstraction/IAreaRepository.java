package com.softcraft.ohhsansibackend.area.domain.repository.abstraction;

import com.softcraft.ohhsansibackend.area.domain.models.Area;

import java.math.BigDecimal;
import java.util.List;


public interface IAreaRepository  {

    Area save(Area area);
    boolean update(Area area);
    boolean delete(int idArea);
    Area findById(int idArea);
    List<Area> findAll();

    boolean updatePrecio(int idArea, BigDecimal precioArea);
}
