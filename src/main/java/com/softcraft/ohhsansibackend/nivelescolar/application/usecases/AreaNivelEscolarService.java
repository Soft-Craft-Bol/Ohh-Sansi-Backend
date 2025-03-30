package com.softcraft.ohhsansibackend.nivelescolar.application.usecases;


import com.softcraft.ohhsansibackend.area.domain.models.AreaNivelEscolar;
import com.softcraft.ohhsansibackend.nivelescolar.domain.repository.implementation.AreaNivelEscolarDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AreaNivelEscolarService {

    private final AreaNivelEscolarDomainRepository areaNivelEscolarDomainRepository;

    @Autowired
    public AreaNivelEscolarService(AreaNivelEscolarDomainRepository areaNivelEscolarDomainRepository) {
        this.areaNivelEscolarDomainRepository = areaNivelEscolarDomainRepository;
    }

    public AreaNivelEscolar saveAreaNivelEscolar(AreaNivelEscolar areaNivelEscolar) {
        return areaNivelEscolarDomainRepository.save(areaNivelEscolar);
    }
}