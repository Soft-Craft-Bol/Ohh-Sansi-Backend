package com.softcraft.ohhsansibackend.application.usecases;

import com.softcraft.ohhsansibackend.domain.models.Area;
import com.softcraft.ohhsansibackend.domain.repository.AreaDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AreaService {

    private final AreaDomainRepository areaDomainRepository;

    @Autowired
    public AreaService(AreaDomainRepository areaDomainRepository) {
        this.areaDomainRepository = areaDomainRepository;
    }

    public Map<String, Object> saveArea(Area area) {
        areaDomainRepository.save(area);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Área creada con éxito");
        response.put("area", area);
        return response;
    }

    public List<Area> getAllAreas() {
        return areaDomainRepository.findAll();
    }

    public Optional<Area> getAreaById(Long id) {
        return areaDomainRepository.findById(id);
    }

    public Map<String, Object> updateArea(Long id, Area area) {
        Optional<Area> areaOptional = areaDomainRepository.findById(id);

        if (areaOptional.isEmpty()) {
            return Map.of("message", "No se encontró el área con ID " + id);
        }
        area.setIdArea(id);
        areaDomainRepository.update(area);

        return Map.of(
                "message", "Área actualizada con éxito",
                "area", area
        );
    }

    public boolean deleteArea(Long id) {
        Optional<Area> area = areaDomainRepository.findById(id);
        if (area.isPresent()) {
            areaDomainRepository.delete(id);
            return true;
        }
        return false;
    }
}

