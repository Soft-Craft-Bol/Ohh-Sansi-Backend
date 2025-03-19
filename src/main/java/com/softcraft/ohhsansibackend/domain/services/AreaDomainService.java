package com.softcraft.ohhsansibackend.domain.services;


import com.softcraft.ohhsansibackend.domain.models.Area;
import com.softcraft.ohhsansibackend.domain.repository.AreaDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AreaDomainService {
    private final AreaDomainRepository areaDomainRepository;


    @Autowired
    public AreaDomainService(AreaDomainRepository areaDomainRepository) {
        this.areaDomainRepository = areaDomainRepository;
    }

    public void createArea(Area area) {
        areaDomainRepository.save(area);
    }


}
