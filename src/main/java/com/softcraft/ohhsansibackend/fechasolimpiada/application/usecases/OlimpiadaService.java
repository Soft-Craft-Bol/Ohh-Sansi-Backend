package com.softcraft.ohhsansibackend.fechasolimpiada.application.usecases;

import com.softcraft.ohhsansibackend.fechasolimpiada.application.ports.OlimpiadaAdapter;
import com.softcraft.ohhsansibackend.fechasolimpiada.domain.models.FechaOlimpiada;
import com.softcraft.ohhsansibackend.fechasolimpiada.domain.models.Olimpiada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class OlimpiadaService {
    private final OlimpiadaAdapter OlimpiadaAdapter;
    private final OlimpiadaAdapter olimpiadaAdapter;

    @Autowired
    public OlimpiadaService(OlimpiadaAdapter OlimpiadaAdapter, OlimpiadaAdapter olimpiadaAdapter) {
        this.OlimpiadaAdapter = OlimpiadaAdapter;
        this.olimpiadaAdapter = olimpiadaAdapter;
    }

    public Map<String, Object> saveOlimpiada(Olimpiada olimpiada) {
        Map<String, Object> response = new HashMap<>();
        try {
            Olimpiada saved = OlimpiadaAdapter.saveOlimpiada(olimpiada);
            response.put("success", true);
            response.put("message", "Olimpiada registrada exitosamente");
            response.put("olimpiada", saved);
        }  catch (DataAccessException dae) {
            String errorMessage = dae.getRootCause() != null ? dae.getRootCause().getMessage() : dae.getMessage();
            String userFriendlyMessage;
            String errorMessageLower = errorMessage.toLowerCase();

            if (errorMessageLower.contains("no se pueden activar")) {
                userFriendlyMessage = "No puedes activar una olimpiada de un año anterior al actual.";
            } else if (errorMessageLower.contains("ya existe")) {
                userFriendlyMessage = "Este período olímpico ya ha sido registrado anteriormente.";
            } else {
                userFriendlyMessage = "Error interno al registrar la olimpiada.";
            }

            response.put("success", false);
            response.put("message", userFriendlyMessage);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error interno al registrar la olimpiada.");
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

    public Map<String, Object> updatePrecioOlimpiada(int idOlimpiada, BigDecimal precioOlimpiada) {
        Map<String, Object> response = new HashMap<>();
        try {
            boolean updated = olimpiadaAdapter.updatePrecioOlimpiada(idOlimpiada, precioOlimpiada);

            if (updated) {
                response.put("message", "Precio de la olimpiada actualizado exitosamente");
                response.put("success", true);
            } else {
                response.put("message", "No se encontró la olimpiada para actualizar");
                response.put("success", false);
            }
        } catch (Exception e) {
            String errorMsg = e.getMessage();
            String userMessage;

            if (errorMsg == null) {
                userMessage = "Error desconocido al actualizar el precio.";
            } else if (errorMsg.contains("olimpiada activa")) {
                userMessage = "No puedes cambiar el precio de una olimpiada que ya ha sido activada.";
            } else {
                userMessage = "Error al actualizar el precio: " + errorMsg;
            }

            response.put("message", userMessage);
            response.put("success", false);
        }
        return response;
    }
}
