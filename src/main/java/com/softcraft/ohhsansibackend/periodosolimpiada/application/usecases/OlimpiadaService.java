package com.softcraft.ohhsansibackend.periodosolimpiada.application.usecases;

import com.softcraft.ohhsansibackend.periodosolimpiada.application.ports.OlimpiadaAdapter;
import com.softcraft.ohhsansibackend.periodosolimpiada.domain.models.Olimpiada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Year;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class OlimpiadaService {
    private final OlimpiadaAdapter olimpiadaAdapter;

    @Autowired
    public OlimpiadaService(OlimpiadaAdapter olimpiadaAdapter) {
        this.olimpiadaAdapter = olimpiadaAdapter;
    }

    public Integer getAnioOlimpiada(Integer idOlimpiada) {
        try {
            Optional<Olimpiada> olimpiada = olimpiadaAdapter.findById(idOlimpiada);
            return olimpiada.map(Olimpiada::getAnio).orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

    public ResponseEntity<Map<String, Object>> saveOlimpiada(Olimpiada olimpiada) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (olimpiada.getAnio() < Year.now().getValue()) {
                return ResponseEntity.badRequest()
                        .body(Map.of(
                                "status", "error",
                                "message", "No se pueden crear olimpiadas para años pasados"
                        ));
            }
            Olimpiada saved = olimpiadaAdapter.saveOlimpiada(olimpiada);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of(
                            "status", "success",
                            "message", "Olimpiada registrada exitosamente",
                            "data", saved
                    ));
        } catch (DataAccessException dae) {
            String errorMessage = getFriendlyErrorMessage(dae);
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of(
                            "status", "error",
                            "message", errorMessage
                    ));

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of(
                            "status", "error",
                            "message", "Error interno al procesar la solicitud"
                    ));
        }
    }

    private String getFriendlyErrorMessage(DataAccessException dae) {
        String rootCause = dae.getRootCause() != null ? dae.getRootCause().getMessage().toLowerCase() : "";

        if (rootCause.contains("no se pueden activar") || rootCause.contains("año anterior")) {
            return "No puedes activar una olimpiada de un año anterior al actual";
        } else if (rootCause.contains("ya existe")) {
            return "Ya existe una olimpiada registrada con el mismo nombre";
        } else if (rootCause.contains("existe una olimpiada registrada entre este rango de fechas")) {
            return "Esta olimpiada se solapa con sus fechas con otra ya registrada";
        } else if (rootCause.contains("año futuro")) {
            return "Solo se pueden crear olimpiadas para el año actual o futuros";
        } else {
            return "Error en la base de datos al registrar la olimpiada";
        }
    }

    private Map<String, Object> errorResponse(String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", message);
        return response;
    }

    public ResponseEntity<Map<String, Object>> updateOlimpiada(Olimpiada olimpiada) {
        Map<String, Object> response = new HashMap<>();
        try {
            Olimpiada updatedOlimpiada = olimpiadaAdapter.updateOlimpiada(olimpiada);
            response.put("message", "Olimpiada actualizada exitosamente");
            response.put("data", updatedOlimpiada);
        } catch (DataAccessException dae) {
            String errorMessage = getFriendlyErrorMessage(dae);
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of(
                            "status", "error",
                            "message", errorMessage
                    ));
        } catch (Exception e) {
            response.put("message", "Error al actualizar la olimpiada");
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public Map<String, Object> getOlimpiada() {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("message", "Lista de olimpiadas obtenida exitosamente");
            response.put("data", olimpiadaAdapter.getAllOlimpiadas());
        } catch (Exception e) {
            response.put("message", "Error al obtener la lista de olimpiadas");
        }
        return response;
    }

    public Olimpiada findOlimpiadaById(int idOlimpiada) {
        return olimpiadaAdapter.findOlimpiadaById(idOlimpiada);
    }
}
