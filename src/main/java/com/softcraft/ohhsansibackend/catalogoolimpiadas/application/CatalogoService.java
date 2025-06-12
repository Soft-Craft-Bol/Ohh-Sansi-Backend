package com.softcraft.ohhsansibackend.catalogoolimpiadas.application;

import com.softcraft.ohhsansibackend.area.domain.models.Area;
import com.softcraft.ohhsansibackend.catalogoolimpiadas.application.adapter.CatalogoOlimpiadaAdapter;
import com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.CatalogoDomainRepository;
import com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.model.ParticipanteCatalogo;
import com.softcraft.ohhsansibackend.participante.application.usecases.ParticipanteService;
import com.softcraft.ohhsansibackend.participante.domain.models.Participante;
import com.softcraft.ohhsansibackend.participante.domain.repository.implementation.ParticipanteDomainRepository;
import com.softcraft.ohhsansibackend.participante.infraestructure.request.AreaCatalogoDTO;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CatalogoService {
    private final CatalogoDomainRepository catalogoDomainRepository;
    private final ParticipanteService participanteService;
    private final ParticipanteDomainRepository participanteDomainRepository;


    public CatalogoService(CatalogoDomainRepository catalogoDomainRepository, @Lazy ParticipanteService participanteService,@Lazy ParticipanteDomainRepository participanteDomainRepository) {
        this.catalogoDomainRepository = catalogoDomainRepository;
        this.participanteService = participanteService;
        this.participanteDomainRepository = participanteDomainRepository;
    }

    public List<Map<String, Object>> getAreasByGradoParticipante(int ciParticipante) {
        // Obtener el participante
        Participante participante = participanteService.findByCarnetIdentidadService(ciParticipante);
        System.out.println("Participante encontrado: " + participante);
        if (participante == null) {
            throw new IllegalArgumentException("Participante no encontrado con el CI proporcionado.");
        }


        List<Map<String, Object>> areas = catalogoDomainRepository
                .getRegisterAreaParticipante(participante.getIdParticipante(), participante.getIdGrado());

        if (areas.isEmpty()) {
            throw new IllegalArgumentException("No existen áreas disponibles para el grado escolar del participante.");
        }

        return areas;
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
