package com.softcraft.ohhsansibackend.catalogoolimpiadas.application;

import com.softcraft.ohhsansibackend.area.domain.models.Area;
import com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.CatalogoDomainRepository;
import com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.model.ParticipanteCatalogo;
import com.softcraft.ohhsansibackend.participante.application.usecases.ParticipanteService;
import com.softcraft.ohhsansibackend.participante.domain.models.Participante;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CatalogoService {
    private final CatalogoDomainRepository catalogoDomainRepository;
    private final ParticipanteService participanteService;

    public CatalogoService(CatalogoDomainRepository catalogoDomainRepository, ParticipanteService participanteService) {
        this.catalogoDomainRepository = catalogoDomainRepository;
        this.participanteService = participanteService;
    }

    public List<Map<String, Object>> getUnregisteredAreasByGrado(int ciParticipante) {
        Participante participante = participanteService.findByCarnetIdentidadService(ciParticipante);
        if (participante == null) {
            throw new IllegalArgumentException("Participante no encontrado con el CI proporcionado.");
        }
        catalogoDomainRepository.validateRegisteredAreas(participante.getIdParticipante());
        List<Map<String, Object>> allAreas = catalogoDomainRepository.getCatalogoByGrado(participante.getIdGrado());
        if (allAreas.isEmpty()) {
            throw new IllegalArgumentException("No existen áreas disponibles para el grado escolar del participante.");
        }
        List<Integer> registeredAreaIds = catalogoDomainRepository.getRegisteredAreasByParticipante(participante.getIdParticipante());
        List<Map<String, Object>> unregisteredAreas = allAreas.stream()
                .filter(area -> !registeredAreaIds.contains((Integer) area.get("id_area")))
                .toList();
        if (unregisteredAreas.isEmpty()) {
            throw new IllegalArgumentException("El participante ya tiene todas las áreas registradas.");
        }
            return unregisteredAreas;
    }

    public ParticipanteCatalogo insertParticipanteCatalogo(ParticipanteCatalogo participanteCatalogo) {
        return catalogoDomainRepository.insertParticipanteCatalogo(participanteCatalogo);
    }

    public List<Area> getAreaCatalogoByCiParticipante(int ciParticipante) {
        return catalogoDomainRepository.getAreaByCiParticipante(ciParticipante);
    }
    public boolean existsParticipanteInCatalogo(int ciParticipante){
        return catalogoDomainRepository.existsParticipanteInCatalogo(ciParticipante);
    }
    public List<Integer> getRegisteredAreasByParticipante(int idParticipante) {
        return catalogoDomainRepository.getRegisteredAreasByParticipante(idParticipante);
    }
}
