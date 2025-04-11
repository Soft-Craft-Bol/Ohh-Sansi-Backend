package com.softcraft.ohhsansibackend.fechasolimpiada.application.usecases;

import com.softcraft.ohhsansibackend.fechasolimpiada.application.ports.OlimpiadaAdapter;
import com.softcraft.ohhsansibackend.fechasolimpiada.domain.models.FechaOlimpiada;
import com.softcraft.ohhsansibackend.fechasolimpiada.domain.models.Olimpiada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OlimpiadaService {
    private final OlimpiadaAdapter OlimpiadaAdapter;

    @Autowired
    public OlimpiadaService(OlimpiadaAdapter OlimpiadaAdapter) {
        this.OlimpiadaAdapter = OlimpiadaAdapter;
    }

    public Map<String, Object> saveOlimpiada(Olimpiada olimpiada) {
        Map<String, Object> response = new HashMap<>();
        try {
            OlimpiadaAdapter.saveOlimpiada(olimpiada);
            response.put("message", "Olimpiada registrada exitosamente");
        } catch (Exception e) {
            response.put("message", "Error al registrar la olimpiada: " + e.getMessage());
        }
        return response;
    }

    public Map<String, Object> deleteOlimpiada(int id) {
        Map<String, Object> response = new HashMap<>();
        try {
            boolean deleted = OlimpiadaAdapter.deleteOlimpiada(id);
            if (deleted) {
                response.put("message", "Olimpiada eliminada exitosamente");
            } else {
                response.put("message", "Olimpiada no encontrada");
            }
        } catch (Exception e) {
            response.put("message", "Error al eliminar la olimpiada: " + e.getMessage());
        }
        return response;
    }
    public Map<String, Object> getOlimpiada() {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("message", "Lista de olimpiadas obtenida exitosamente");
            response.put("data", OlimpiadaAdapter.getAllOlimpiadas());
        } catch (Exception e) {
            response.put("message", "Error al obtener la lista de olimpiadas");
        }
        return response;
    }

    public Map<String, Object> getOlimpiadaById(int id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Olimpiada olimpiada = OlimpiadaAdapter.getOlimpiadaById(id);
            response.put("message", "Olimpiada encontrada exitosamente");
            response.put("data", olimpiada);
        } catch (Exception e) {
            response.put("message", "Error al obtener la olimpiada: " + e.getMessage());
        }
        return response;
    }



    public Map<String, Object> getOlimpiadaPublic() {
        Map<String, Object> response = new HashMap<>();
        try {
            Olimpiada olimpiada = OlimpiadaAdapter.getOlimpiadaPublic();
            response.put("message", "Olimpiada publica encontrada exitosamente");
            response.put("data", olimpiada);
        } catch (Exception e) {
            response.put("message", "Error al obtener la olimpiada publica: " + e.getMessage());
        }
        return response;
    }

}
