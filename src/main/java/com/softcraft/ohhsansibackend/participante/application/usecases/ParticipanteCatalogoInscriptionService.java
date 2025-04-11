package com.softcraft.ohhsansibackend.participante.application.usecases;

import com.softcraft.ohhsansibackend.catalogoolimpiadas.application.CatalogoService;
import com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.model.ParticipanteCatalogo;
import com.softcraft.ohhsansibackend.participante.domain.models.Participante;
import com.softcraft.ohhsansibackend.participante.infraestructure.request.AreaCatalogoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ParticipanteCatalogoInscriptionService {
    private final ParticipanteService participanteService;
    private final CatalogoService catalogoService;
    @Autowired
    public ParticipanteCatalogoInscriptionService(ParticipanteService participanteService, CatalogoService catalogoService) {
        this.participanteService = participanteService;
        this.catalogoService = catalogoService;
    }


    public Map<String, Object> registerParticipantWithCatalogoComposition(int ciParticipante, AreaCatalogoDTO areaCatalogoDTO) {
        try {
            Participante participante = participanteService.findByCarnetIdentidadService(ciParticipante);
            if (participante == null) {
                throw new RuntimeException("Participante no encontrado");
            }
            ParticipanteCatalogo participanteCatalogo = new ParticipanteCatalogo();
            participanteCatalogo.setIdCategoria(areaCatalogoDTO.getIdCategoria());
            participanteCatalogo.setIdArea(areaCatalogoDTO.getIdArea());
            participanteCatalogo.setIdCatalogo(areaCatalogoDTO.getIdCatalogo());
            participanteCatalogo.setIdOlimpiada(areaCatalogoDTO.getIdOlimpiada());
            participanteCatalogo.setIdInscripcion(participante.getIdInscripcion());
            participanteCatalogo.setIdParticipante(participante.getIdParticipante());
            catalogoService.insertParticipanteCatalogo(participanteCatalogo);
            return Map.of(
                    "message", "Participante registrado exitosamente con catalogo",
                    "participanteCatalogo", participanteCatalogo);
        } catch (Exception e) {
            throw new RuntimeException("Error al registrar el participante con catalogo: " + e.getMessage());
        }


    }


}
