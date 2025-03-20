package com.softcraft.ohhsansibackend.application.ports;

import com.softcraft.ohhsansibackend.domain.models.Area;
import com.softcraft.ohhsansibackend.domain.services.AreaDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AreaAdapter {
    private final AreaDomainService areaDomainService;

    @Autowired
    public AreaAdapter(AreaDomainService areaDomainService) {
        this.areaDomainService = areaDomainService;
    }

    public void saveArea(Area area) {
        areaDomainService.createArea(area);
    }

    public void updateArea(Area area) {
        areaDomainService.updateArea(area);
    }

    public void deleteArea(Long id) {
        areaDomainService.deleteArea(id);
    }

    public Optional<Area> findAreaById(Long id) {
        return areaDomainService.findAreaById(id);
    }

    public List<Area> getAreas() {
        return areaDomainService.getAreas();
    }

}
