package com.softcraft.ohhsansibackend.fechasolimpiada.application.usecases;

import com.softcraft.ohhsansibackend.exception.ResourceNotFoundException;
import com.softcraft.ohhsansibackend.fechasolimpiada.application.ports.FechaOlimpiadaAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class PlazoInscripcionService {
    private final FechaOlimpiadaAdapter fechaOlimpiadaAdapter;

    @Autowired
    public PlazoInscripcionService(FechaOlimpiadaAdapter fechaOlimpiadaAdapter) {
        this.fechaOlimpiadaAdapter = fechaOlimpiadaAdapter;
    }

    public Map<String, Object> upsertPlazoInscripcion(PlazoInscripcion plazoInscripcion) {
        Map<String, Object> response = new HashMap<>();
        try {
            fechaOlimpiadaAdapter.upsertPlazoInscripcion(plazoInscripcion);
            response.put("message", "Plazo de inscripción registrado exitosamente");
        } catch (Exception e) {
            response.put("message", "Error al registrar el plazo de inscripción: " + e.getMessage());
        }
        return response;
    }

    public Map<String, Object> deletePlazoInscripcion(int id) {
        Map<String, Object> response = new HashMap<>();
        boolean deleted = fechaOlimpiadaAdapter.deletePlazoInscripcion(id);
        if (deleted) {
            response.put("message","Area eliminada exitosamente");
        }else {
            throw new ResourceNotFoundException("Area no encontrada");
        }
        return response;
    }

    public Map<String, Object> getPlazosInscripcion() {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("message", "Lista de plazos de inscripción obtenida exitosamente");
            response.put("data", fechaOlimpiadaAdapter.getPlazosInscripcion());
        } catch (Exception e) {
            response.put("message", "Error al obtener la lista de plazos de inscripción");
        }
        return response;
    }

    public Map<String, Object> getPlazoInscripcionById(int id) {
        Map<String, Object> response = new HashMap<>();
        try {
            PlazoInscripcion plazoInscripcion = fechaOlimpiadaAdapter.getPlazoInscripcionById(id);
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
            PlazoInscripcion plazoInscripcion = fechaOlimpiadaAdapter.getPlazoInscripcionActivo();
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
            PlazoInscripcion plazoInscripcion = fechaOlimpiadaAdapter.getPlazoInscripcionByDate(date);
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

    public Map<String, Object> insertPrecioPeriodo(PlazoInscripcion plazoInscripcion) {
        Map<String, Object> response = new HashMap<>();
        try {
            PlazoInscripcion result = fechaOlimpiadaAdapter.insertPrecioPeriodo(plazoInscripcion);
            response.put("message", "Precio del periodo registrado exitosamente");
            response.put("data", result);
        } catch (Exception e) {
            response.put("message", "Error al registrar el precio del periodo: " + e.getMessage());
        }
        return response;
    }
}
