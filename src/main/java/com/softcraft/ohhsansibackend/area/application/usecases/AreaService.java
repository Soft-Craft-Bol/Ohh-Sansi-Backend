package com.softcraft.ohhsansibackend.area.application.usecases;

import com.softcraft.ohhsansibackend.exception.ResourceNotFoundException;
import com.softcraft.ohhsansibackend.area.application.ports.AreaAdapter;
import com.softcraft.ohhsansibackend.area.domain.models.Area;
import org.springframework.beans.factory.annotation.Autowired;
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
        try {
            areaAdapter.saveArea(area);
        } catch (DuplicateKeyException e) {
            throw new DuplicateResourceException("Area ya registrada");
        }
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Area registrada exitosamente");
        return response;
    }

    public Map<String, Object> updateArea(Area area) {
        Map<String, Object> response = new HashMap<>();
        try {
            areaAdapter.updateArea(area);
            response.put("message", "Area actualizada exitosamente");
        } catch (Exception e) {
            throw new ResourceNotFoundException("Area no encontrada");
        }
        return response;
    }

    public Map<String, Object> deleteArea(int id) {
        Map<String, Object> response = new HashMap<>();
        try {
            areaAdapter.deleteArea(id);
            response.put("message", "Area eliminada exitosamente");
        } catch (Exception e) {
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

