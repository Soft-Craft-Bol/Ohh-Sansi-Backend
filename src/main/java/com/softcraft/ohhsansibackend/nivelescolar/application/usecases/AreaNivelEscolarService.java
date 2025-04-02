package com.softcraft.ohhsansibackend.nivelescolar.application.usecases;


import com.softcraft.ohhsansibackend.area.domain.models.AreaNivelEscolar;
import com.softcraft.ohhsansibackend.nivelescolar.domain.repository.implementation.AreaNivelEscolarDomainRepository;
import com.softcraft.ohhsansibackend.nivelescolar.dto.AreaCategoriaNivelDTO;
import com.softcraft.ohhsansibackend.nivelescolar.dto.AreaNivelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<String, Object> getAreasAndCategoriasByNivel(int idNivel) {
        List<AreaNivelDTO> areas = areaNivelEscolarDomainRepository.findAreasByNivel(idNivel);
        List<AreaCategoriaNivelDTO> areasCategorias = areaNivelEscolarDomainRepository.findAreasCategoriasByNivel(idNivel);

        Map<String, Object> result = new HashMap<>();
        result.put("areas", areas);
        result.put("areasCategorias", areasCategorias);

        return result;
    }



}