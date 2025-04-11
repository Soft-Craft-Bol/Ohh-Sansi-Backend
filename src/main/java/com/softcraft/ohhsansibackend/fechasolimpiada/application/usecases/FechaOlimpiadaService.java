package com.softcraft.ohhsansibackend.fechasolimpiada.application.usecases;

import com.softcraft.ohhsansibackend.exception.ResourceNotFoundException;
import com.softcraft.ohhsansibackend.fechasolimpiada.application.ports.FechaOlimpiadaAdapter;
import com.softcraft.ohhsansibackend.fechasolimpiada.domain.models.FechaOlimpiada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class FechaOlimpiadaService {
    private final FechaOlimpiadaAdapter fechaOlimpiadaAdapter;

    @Autowired
    public FechaOlimpiadaService(FechaOlimpiadaAdapter fechaOlimpiadaAdapter) {
        this.fechaOlimpiadaAdapter = fechaOlimpiadaAdapter;
    }

    public Map<String, Object> upsertFechaOlimpiada(FechaOlimpiada fechaOlimpiada) {
        Map<String, Object> response = new HashMap<>();
        try {
            fechaOlimpiadaAdapter.upsertFechaOlimpiada(fechaOlimpiada);
            response.put("message", "Fecha registrada exitosamente");
        } catch (Exception e) {
            response.put("message", "Error al registrar la fecha: " + e.getMessage());
        }
        return response;
    }

    public Map<String, Object> deleteFechaOlimpiada(int id) {
        Map<String, Object> response = new HashMap<>();
        boolean deleted = fechaOlimpiadaAdapter.deleteFechaOlimpiada(id);
        if (deleted) {
            response.put("message","Fecha eliminada exitosamente");
        }else {
            throw new ResourceNotFoundException("Fecha no encontrada");
        }
        return response;
    }

    public Map<String, Object> getFechaOlimpiada() {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("message", "Lista de fechas de olimpiadas obtenida exitosamente");
            response.put("data", fechaOlimpiadaAdapter.getFechaOlimpiada());
        } catch (Exception e) {
            response.put("message", "Error al obtener la lista de fechas de olimpiadas");
        }
        return response;
    }

    public Map<String, Object> getFechaOlimpiadaById(int id) {
        Map<String, Object> response = new HashMap<>();
        try {
            FechaOlimpiada fechaOlimpiada = fechaOlimpiadaAdapter.getFechaOlimpiadaById(id);
            response.put("message", "Fecha de olimpiada encontrada exitosamente");
            response.put("data", fechaOlimpiada);
        } catch (Exception e) {
            response.put("message", "Error al buscar la fecha de olimpiada");
        }
        return response;
    }

    public Map<String, Object> getFechaOlimpiadaPublic() {
        Map<String, Object> response = new HashMap<>();
        try {
            FechaOlimpiada fechaOlimpiada = fechaOlimpiadaAdapter.getFechaOlimpiadaPublic();
            response.put("message", "Fecha de olimpiada publica encontrada exitosamente");
            response.put("data", fechaOlimpiada);
        } catch (Exception e) {
            response.put("message", "Error al buscar la fecha de olimpiada publica");
        }
        return response;
    }
}
