package com.softcraft.ohhsansibackend.domain.services;


import com.softcraft.ohhsansibackend.domain.models.Area;
import com.softcraft.ohhsansibackend.domain.repository.abstraction.IAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AreaDomainService {
    private final IAreaRepository IAreaRepository;


    @Autowired
    public AreaDomainService(IAreaRepository IAreaRepository) {
        this.IAreaRepository = IAreaRepository;
    }

    public void createArea(Area area) {
        IAreaRepository.save(area);
    }

    public void updateArea(Area area) {
        IAreaRepository.update(area);
    }

    public void deleteArea(Long id) {
        IAreaRepository.delete(id);
    }

    public Optional<Area> findAreaById(Long id) {
        return IAreaRepository.findById(id);
    }

    public List<Area> getAreas() {
        return IAreaRepository.findAll();
    }


}
