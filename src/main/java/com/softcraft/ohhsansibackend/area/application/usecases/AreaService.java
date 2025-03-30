package com.softcraft.ohhsansibackend.area.application.usecases;

import com.softcraft.ohhsansibackend.exception.DuplicateResourceException;
import com.softcraft.ohhsansibackend.exception.ResourceNotFoundException;
import com.softcraft.ohhsansibackend.area.application.ports.AreaAdapter;
import com.softcraft.ohhsansibackend.area.domain.models.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import java.util.Map;

@Service
public class AreaService {

    private final AreaAdapter areaAdapter;

    @Autowired
    public AreaService(AreaAdapter areaAdapter) {
        this.areaAdapter = areaAdapter;
    }

    public Map<String, Object> saveArea(Area area) {
        Map<String, Object> response = new HashMap<>();
        try {
            areaAdapter.saveArea(area);
            response.put("message", "Área registrada exitosamente");
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateResourceException("Área ya registrada");
        }
        return response;
    }


    public Map<String, Object> updateArea(Area area) {
        Map<String, Object> response = new HashMap<>();
        boolean updated = areaAdapter.updateArea(area);

        if (updated) {
            response.put("message", "Área actualizada exitosamente");
        } else {
            throw new ResourceNotFoundException("Area no encontrada");
        }
        return response;
    }

    public Map<String, Object> deleteArea(int id) {
        Map<String, Object> response = new HashMap<>();
        boolean deleted = areaAdapter.deleteArea(id);
        if (deleted) {
            response.put("message","Area eliminada exitosamente");
        }else {
            throw new ResourceNotFoundException("Area no encontrada");
        }
        return response;
    }

    public Map<String, Object> findAreaById(int id) {
        Map<String, Object> response = new HashMap<>();
        Area area = areaAdapter.findAreaById(id);
        if (area == null) {
            throw new ResourceNotFoundException("Area no encontrada");
        }
        response.put("area", area);
        return response;
    }

    public Map<String, Object> getAreas() {
        try {
            Map<String, Object> response = new HashMap<>();
            response.put("areas", areaAdapter.getAreas());
            return response;
        } catch (Exception e) {
        throw new RuntimeException("Error al obtener la lista de areas");
        }
    }

}

