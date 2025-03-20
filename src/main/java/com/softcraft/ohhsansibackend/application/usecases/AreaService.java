package com.softcraft.ohhsansibackend.application.usecases;

import com.softcraft.ohhsansibackend.application.ports.AreaAdapter;
import com.softcraft.ohhsansibackend.domain.models.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AreaService {

    private final AreaAdapter areaAdapter;

    @Autowired
    public AreaService(AreaAdapter areaAdapter) {
        this.areaAdapter = areaAdapter;
    }

    public void saveArea(Area area) {
        areaAdapter.saveArea(area);
    }

    public void updateArea(Area area) {
        areaAdapter.updateArea(area);
    }

    public void deleteArea(Long id) {
        areaAdapter.deleteArea(id);
    }

    public Optional<Area> findAreaById(Long id) {
        return areaAdapter.findAreaById(id);
    }

    public List<Area> getAreas() { return areaAdapter.getAreas(); }

}

