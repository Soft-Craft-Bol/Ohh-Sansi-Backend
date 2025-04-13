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

    public List<Map<String,Object>> getCatalogoByGrado(int ciParticipante) {
        Participante participante = participanteService.findByCarnetIdentidadService(ciParticipante);
        return catalogoDomainRepository.getCatalogoByGrado(participante.getIdGrado());
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

}
