package com.softcraft.ohhsansibackend.participante.application.usecases;

import com.softcraft.ohhsansibackend.catalogoolimpiadas.application.CatalogoService;
import com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.model.ParticipanteCatalogo;
import com.softcraft.ohhsansibackend.participante.domain.models.Participante;
import com.softcraft.ohhsansibackend.participante.infraestructure.request.AreaCatalogoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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


    public Map<String, Object> registerParticipantWithCatalogoComposition(int ciParticipante, List<AreaCatalogoDTO> areaCatalogoDTO) {
        try {
            if (areaCatalogoDTO.size() > 2) {
                throw new IllegalArgumentException("El número de áreas no puede ser mayor a 2");
            }
            Participante participante = participanteService.findByCarnetIdentidadService(ciParticipante);
            if (participante == null) {
                throw new RuntimeException("Participante no encontrado");
            }
            for (AreaCatalogoDTO area : areaCatalogoDTO) {
                ParticipanteCatalogo participanteCatalogo = new ParticipanteCatalogo();
                participanteCatalogo.setIdCategoria(area.getIdCategoria());
                participanteCatalogo.setIdArea(area.getIdArea());
                participanteCatalogo.setIdCatalogo(area.getIdCatalogo());
                participanteCatalogo.setIdOlimpiada(area.getIdOlimpiada());
                participanteCatalogo.setIdInscripcion(participante.getIdInscripcion());
                participanteCatalogo.setIdParticipante(participante.getIdParticipante());
                catalogoService.insertParticipanteCatalogo(participanteCatalogo);
            }
            return Map.of(
                    "message", "Participante registrado exitosamente con catálogo",
                    "areasRegistradas", areaCatalogoDTO.size()
            );
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error al registrar el participante con catálogo: " + e.getMessage());
        }
    }


}
