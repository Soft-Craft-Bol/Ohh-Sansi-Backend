package com.softcraft.ohhsansibackend.area.domain.services;

import com.softcraft.ohhsansibackend.area.domain.models.Area;
import com.softcraft.ohhsansibackend.area.domain.repository.abstraction.IAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaDomainService {
    private final IAreaRepository IAreaRepository;

    @Autowired
    public AreaDomainService(IAreaRepository IAreaRepository) {
        this.IAreaRepository = IAreaRepository;
    }

    public Area createArea(Area area) {
        return IAreaRepository.save(area);
    }

    public List<Area> getAreas() {
        return IAreaRepository.findAll();
    }

    public boolean updateArea(Area area) {
        return IAreaRepository.update(area);
    }

    public boolean deleteArea(int id) {
        return IAreaRepository.delete(id);
    }

    public Area findAreaById(int id) {
        return IAreaRepository.findById(id);
    }

}
