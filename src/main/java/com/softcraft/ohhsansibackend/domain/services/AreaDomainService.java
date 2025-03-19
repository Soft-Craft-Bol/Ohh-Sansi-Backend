package com.softcraft.ohhsansibackend.domain.services;


import com.softcraft.ohhsansibackend.domain.models.Area;
import com.softcraft.ohhsansibackend.domain.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AreaDomainService {
    private final AreaRepository areaRepository;


    @Autowired
    public AreaDomainService(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    public void createArea(Area area) {
        areaRepository.save(area);
    }

    public void updateArea(Area area) {
        areaRepository.update(area);
    }

    public void deleteArea(Long id) {
        areaRepository.delete(id);
    }

    public Optional<Area> findAreaById(Long id) {
        return areaRepository.findById(id);
    }

    public List<Area> getAreas() {
        return areaRepository.findAll();
    }


}
