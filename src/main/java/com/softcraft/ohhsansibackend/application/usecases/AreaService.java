package com.softcraft.ohhsansibackend.application.usecases;

import com.softcraft.ohhsansibackend.application.exception.ResourceNotFoundException;
import com.softcraft.ohhsansibackend.application.ports.AreaAdapter;
import com.softcraft.ohhsansibackend.domain.models.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
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
        }catch (DuplicateKeyException e){
            throw new DuplicateKeyException(e.getMessage());
        }
        return Map.of("success", true, "message", "Area creado exitosamente");
    }

    public Map<String, Object> updateArea(Area area) {
        if (areaAdapter.findAreaById(area.getIdArea()) == null) {
            throw new ResourceNotFoundException("Área con ID " + area.getIdArea() + " no encontrada");
        }
        areaAdapter.updateArea(area);
        return Map.of("success", true, "message", "Área actualizada exitosamente");
    }

    public Map<String, Object> deleteArea(int id) {
        if (areaAdapter.findAreaById(id) == null) {
            throw new ResourceNotFoundException("Área con ID " + id + " no encontrada");
        }
        areaAdapter.deleteArea(id);
        return Map.of("success", true, "message", "Área eliminada exitosamente");
    }

    public Area findAreaById(int id) {
        Area area = areaAdapter.findAreaById(id);
        if(area == null) {
            throw new ResourceNotFoundException("Area con ID" + id + " no encontrado");
        }
        return area;
    }

    public List<Area> getAreas() {
        return areaAdapter.getAreas();
    }

}

