package com.softcraft.ohhsansibackend.fechasolimpiada.application.usecases;

import com.softcraft.ohhsansibackend.fechasolimpiada.domain.models.FechaOlimpiada;
import com.softcraft.ohhsansibackend.fechasolimpiada.domain.models.Olimpiada;
import com.softcraft.ohhsansibackend.fechasolimpiada.domain.repository.implementation.FechaOlimpiadaDomainRepository;
import com.softcraft.ohhsansibackend.fechasolimpiada.infraestructure.dto.EventoDTO;
import com.softcraft.ohhsansibackend.fechasolimpiada.infraestructure.dto.OlimpiadaEventosDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        Olimpiada olimpiada = new Olimpiada();
        olimpiada.setNombreOlimpiada(dto.getNombreOlimpiada());
        olimpiada.setEstadoOlimpiada(dto.isEstadoOlimpiada());

        Map<String, Object> responseSave = olimpiadaService.saveOlimpiada(olimpiada);

        if (!(boolean) responseSave.getOrDefault("success", false)) {
            throw new RuntimeException("Error al guardar olimpiada: " + responseSave.get("message"));
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
    }


}
