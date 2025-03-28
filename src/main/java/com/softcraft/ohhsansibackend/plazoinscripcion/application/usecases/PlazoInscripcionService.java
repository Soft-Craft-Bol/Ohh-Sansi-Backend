package com.softcraft.ohhsansibackend.plazoinscripcion.application.usecases;

import com.softcraft.ohhsansibackend.exception.ResourceNotFoundException;
import com.softcraft.ohhsansibackend.plazoinscripcion.application.ports.PlazoInscripcionAdapter;
import com.softcraft.ohhsansibackend.plazoinscripcion.domain.models.PlazoInscripcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class PlazoInscripcionService {
    private final PlazoInscripcionAdapter plazoInscripcionAdapter;

    @Autowired
    public PlazoInscripcionService(PlazoInscripcionAdapter plazoInscripcionAdapter) {
        this.plazoInscripcionAdapter = plazoInscripcionAdapter;
    }

    public Map<String, Object> upsertPlazoInscripcion(PlazoInscripcion plazoInscripcion) {
        Map<String, Object> response = new HashMap<>();
        try {
            plazoInscripcionAdapter.upsertPlazoInscripcion(plazoInscripcion);
            response.put("message", "Plazo de inscripción registrado exitosamente");
        } catch (Exception e) {
            response.put("message", "Error al registrar el plazo de inscripción: " + e.getMessage());
        }
        return response;
    }

    public Map<String, Object> savePlazoInscripcion(PlazoInscripcion plazoInscripcion) {
        Map<String, Object> response = new HashMap<>();
        try{
            plazoInscripcionAdapter.savePlazoInscripcion(plazoInscripcion);
            response.put("message", "Plazo de inscripción registrado exitosamente");
        } catch (Exception e) {
            response.put("message", "Error al registrar el plazo de inscripción: " + e.getMessage());
        }
        return response;
    }

    public Map<String, Object> updatePlazoInscripcion(PlazoInscripcion plazoInscripcion) {
        Map<String, Object> response = new HashMap<>();
        try {
            plazoInscripcionAdapter.updatePlazoInscripcion(plazoInscripcion);
            response.put("message", "Plazo de inscripción actualizado exitosamente");
        } catch (Exception e) {
            response.put("message", "Error al actualizar el plazo de inscripción");
        }
        return response;
    }

    public Map<String, Object> deletePlazoInscripcion(int id) {
        Map<String, Object> response = new HashMap<>();
        boolean deleted = plazoInscripcionAdapter.deletePlazoInscripcion(id);
        if (deleted) {
            response.put("message","Area eliminada exitosamente");
        }else {
            throw new ResourceNotFoundException("Area no encontrada");
        }
        return response;
    }

    public Map<String, Object> getPlazoInscripcionById(int id) {
        Map<String, Object> response = new HashMap<>();
        try {
            PlazoInscripcion plazoInscripcion = plazoInscripcionAdapter.getPlazoInscripcionById(id);
            response.put("message", "Plazo de inscripción encontrado exitosamente");
            response.put("data", plazoInscripcion);
        } catch (Exception e) {
            response.put("message", "Error al buscar el plazo de inscripción");
        }
        return response;
    }

    public Map<String, Object> getPlazoInscripcionActivo() {
        Map<String, Object> response = new HashMap<>();
        try {
            PlazoInscripcion plazoInscripcion = plazoInscripcionAdapter.getPlazoInscripcionActivo();
            response.put("message", "Plazo de inscripción activo encontrado exitosamente");
            response.put("data", plazoInscripcion);
        } catch (Exception e) {
            response.put("message", "Error al buscar el plazo de inscripción activo");
        }
        return response;
    }

    public Map<String, Object> getPlazoInscripcionByDate(LocalDate date) {
        Map<String, Object> response = new HashMap<>();
        try {
            PlazoInscripcion plazoInscripcion = plazoInscripcionAdapter.getPlazoInscripcionByDate(date);
            response.put("message", "Plazo de inscripción encontrado exitosamente");
            response.put("data", plazoInscripcion);
        } catch (EmptyResultDataAccessException e) {
            response.put("message", "No se encontró un plazo de inscripción para la fecha especificada");
        } catch (Exception e) {
            response.put("message", "Error interno al buscar el plazo de inscripción");
            response.put("error", e.getMessage());
        }
        return response;
    }
}
