package com.softcraft.ohhsansibackend.fechasolimpiada.application.usecases;

import com.softcraft.ohhsansibackend.fechasolimpiada.domain.models.FechaOlimpiada;
import com.softcraft.ohhsansibackend.fechasolimpiada.domain.models.Olimpiada;
import com.softcraft.ohhsansibackend.fechasolimpiada.domain.repository.implementation.FechaOlimpiadaDomainRepository;
import com.softcraft.ohhsansibackend.fechasolimpiada.infraestructure.dto.EventoDTO;
import com.softcraft.ohhsansibackend.fechasolimpiada.infraestructure.dto.OlimpiadaEventosDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OlimpiadaEventoService {
    private final OlimpiadaService olimpiadaService;
    private final FechaOlimpiadaService fechaOlimpiadaService;
    private final FechaOlimpiadaDomainRepository fechaOlimpiadaDomainRepository;

    @Autowired
    public OlimpiadaEventoService(OlimpiadaService olimpiadaService, FechaOlimpiadaService fechaOlimpiadaService, FechaOlimpiadaDomainRepository fechaOlimpiadaDomainRepository) {
        this.olimpiadaService = olimpiadaService;
        this.fechaOlimpiadaService = fechaOlimpiadaService;
        this.fechaOlimpiadaDomainRepository = fechaOlimpiadaDomainRepository;
    }

    public Map<String, Object> registrarOlimpiadaConEventos(OlimpiadaEventosDTO dto) {
        Map<String, Object> response = new HashMap<>();
        try {
            Olimpiada olimpiada = new Olimpiada();
            olimpiada.setAnio(dto.getAnio());
            olimpiada.setEstadoOlimpiada(dto.isEstadoOlimpiada());

            Map<String, Object> responseSave = olimpiadaService.saveOlimpiada(olimpiada);

            if (!(boolean) responseSave.getOrDefault("success", false)) {
                response.put("success", false);
                response.put("message", responseSave.getOrDefault("message", "No se pudo registrar la olimpiada."));
                return response;
            }

            Olimpiada saved = (Olimpiada) responseSave.get("olimpiada");

            for (EventoDTO evento : dto.getEventos()) {
                FechaOlimpiada fecha = new FechaOlimpiada();
                fecha.setIdOlimpiada(saved.getIdOlimpiada());
                fecha.setNombreEvento(evento.getNombreEvento());
                fecha.setFechaInicio(evento.getFechaInicio());
                fecha.setFechaFin(evento.getFechaFin());
                fecha.setEsPublica(evento.getEsPublica());

                fechaOlimpiadaService.upsertFechaOlimpiada(fecha);
            }

            response.put("message", "Olimpiada y eventos registrados correctamente");
            response.put("success", true);
            return response;
        } catch (DataAccessException dae) {
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
            return response;
        }
    }
}
