package com.softcraft.ohhsansibackend.periodosolimpiada.application.usecases;

import com.softcraft.ohhsansibackend.catalogoolimpiadas.application.usecases.CatalogoOlimpiadaService;
import com.softcraft.ohhsansibackend.exception.ResourceNotFoundException;
import com.softcraft.ohhsansibackend.periodosolimpiada.application.ports.PeriodoOlimpiadaAdapter;
import com.softcraft.ohhsansibackend.periodosolimpiada.domain.models.Olimpiada;
import com.softcraft.ohhsansibackend.periodosolimpiada.domain.models.PeriodoOlimpiada;
import com.softcraft.ohhsansibackend.periodosolimpiada.infraestructure.dto.OlimpiadaEventosDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PeriodoOlimpiadaService {
    private final PeriodoOlimpiadaAdapter periodoOlimpiadaAdapter;
    private final OlimpiadaService olimpiadaService;
    private final CatalogoOlimpiadaService catalogoOlimpiadaService;

    @Autowired
    public PeriodoOlimpiadaService(PeriodoOlimpiadaAdapter periodoOlimpiadaAdapter, OlimpiadaService olimpiadaService, CatalogoOlimpiadaService catalogoOlimpiadaService) {
        this.periodoOlimpiadaAdapter = periodoOlimpiadaAdapter;
        this.olimpiadaService = olimpiadaService;
        this.catalogoOlimpiadaService = catalogoOlimpiadaService;
    }

    public Map<String, Object> insertPeriodoOlimpiada(PeriodoOlimpiada periodoOlimpiada) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (periodoOlimpiada.getFechaInicio().isBefore(LocalDate.now())) {
                response.put("status", "error");
                response.put("message", "No se pueden crear períodos con fechas anteriores a la fecha actual");
                return response;
            }

            // Validación de fecha fin anterior a inicio
            if (periodoOlimpiada.getFechaFin().isBefore(periodoOlimpiada.getFechaInicio())) {
                response.put("status", "error");
                response.put("message", "La fecha de fin no puede ser anterior a la fecha de inicio");
                return response;
            }

            // Validación de año de olimpiada
            Integer anioOlimpiada = olimpiadaService.getAnioOlimpiada(periodoOlimpiada.getIdOlimpiada());
            if (anioOlimpiada == null) {
                response.put("status", "error");
                response.put("message", "La olimpiada especificada no existe");
                return response;
            }

            if (periodoOlimpiada.getFechaInicio().getYear() != anioOlimpiada ||
                    periodoOlimpiada.getFechaFin().getYear() != anioOlimpiada) {
                response.put("status", "error");
                response.put("message", "Las fechas del período deben coincidir con el año de la olimpiada (" + anioOlimpiada + ")");
                return response;
            }

            PeriodoOlimpiada inserted = periodoOlimpiadaAdapter.insertPeriodoOlimpiada(periodoOlimpiada);

            response.put("status", "success");
            response.put("message", "Período registrado exitosamente");
            response.put("data", inserted);

        } catch (DataAccessException dae) {
            String errorMessage = getFriendlyErrorMessage(dae);
            response.put("status", "error");
            response.put("message", errorMessage);

        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Error interno al registrar el período: " + e.getMessage());
        }
        return response;
    }
    private String getFriendlyErrorMessage(DataAccessException dae) {
        String rootCause = dae.getRootCause() != null ? dae.getRootCause().getMessage() : dae.getMessage();

        if (rootCause.contains("solapa")) {
            return "Existe un conflicto con las fechas proporcionadas: " +
                    rootCause.substring(rootCause.indexOf('"') + 1, rootCause.lastIndexOf('"'));
        } else if (rootCause.contains("anterior")) {
            return "La fecha de fin no puede ser anterior a la fecha de inicio";
        } else {
            return "Error al registrar el período. Verifique los datos e intente nuevamente";
        }
    }

    public List<OlimpiadaEventosDTO> getOlimpiadasconEventos() {
        return periodoOlimpiadaAdapter.getOlimpiadasconEventos();
    }
    public Map<String, Object> encontrarPeriodoInscripcionActualMap(){
        Map<String, Object> response = new HashMap<>();
        try {
            PeriodoOlimpiada periodoOlimpiada = periodoOlimpiadaAdapter.encontrarPeriodoInscripcionActual();
            if (periodoOlimpiada == null) {
                response.put("status", "error");
                response.put("message", "No se encontró un período de inscripción actual");
            } else {
                Olimpiada olimpiada = olimpiadaService.findOlimpiadaById(periodoOlimpiada.getIdOlimpiada());
                response.put("status", "success");
                response.put("olimpiada", olimpiada);
                response.put("periodoOlimpiada", periodoOlimpiada);
            response.put("catalogoOlimpiada", catalogoOlimpiadaService.getCatalogoOlimpiadaById(periodoOlimpiada.getIdOlimpiada()));
            }
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Error al encontrar el período de inscripción actual: " + e.getMessage());
        }
        return response;
    }
}
