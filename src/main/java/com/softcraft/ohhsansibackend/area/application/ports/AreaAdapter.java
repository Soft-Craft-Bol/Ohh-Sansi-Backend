package com.softcraft.ohhsansibackend.area.application.ports;

import com.softcraft.ohhsansibackend.area.domain.models.Area;
import com.softcraft.ohhsansibackend.area.domain.services.AreaDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class AreaAdapter {
    private final AreaDomainService areaDomainService;

    @Autowired
    public AreaAdapter(AreaDomainService areaDomainService) {
        this.areaDomainService = areaDomainService;
    }

    public Area saveArea(Area area) {
        return areaDomainService.createArea(area);
    }

    public boolean updateArea(Area area) {
        return areaDomainService.updateArea(area);
    }

    public boolean deleteArea(int id) {
        return areaDomainService.deleteArea(id);
    }

    public Area findAreaById(int id) {
        return areaDomainService.findAreaById(id);
    }

    public List<Area> getAreas() {
        return areaDomainService.getAreas();
    }

    public boolean updatePrecioArea(int idArea, BigDecimal precioArea) {
        return areaDomainService.updatePrecioArea(idArea, precioArea);
    }
}
