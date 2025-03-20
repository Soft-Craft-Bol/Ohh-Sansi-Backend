package com.softcraft.ohhsansibackend.application.usecases;

import com.softcraft.ohhsansibackend.domain.models.Area;
import com.softcraft.ohhsansibackend.domain.services.AreaDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AreaService {

    private final AreaDomainService areaDomainService;

    @Autowired
    public AreaService(AreaDomainService areaDomainService) {
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

