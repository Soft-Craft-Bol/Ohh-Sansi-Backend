package com.softcraft.ohhsansibackend.participante.application.usecases;

import com.softcraft.ohhsansibackend.catalogoolimpiadas.application.CatalogoService;
import com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.model.ParticipanteCatalogo;
import com.softcraft.ohhsansibackend.participante.application.ports.ParticipanteAdapter;
import com.softcraft.ohhsansibackend.participante.domain.dto.ParticipanteAreasDTO;
import com.softcraft.ohhsansibackend.participante.domain.dto.ParticipanteTutorAreaDTO;
import com.softcraft.ohhsansibackend.participante.domain.models.Participante;
import com.softcraft.ohhsansibackend.participante.domain.services.ParticipanteDomainService;
import com.softcraft.ohhsansibackend.participante.infraestructure.request.AreaCatalogoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ParticipanteCatalogoInscriptionService {
    private final ParticipanteService participanteService;
    private final CatalogoService catalogoService;
    private final ParticipanteAdapter participanteAdapter;
    private final ParticipanteDomainService participanteDomainService;
    @Autowired
    public ParticipanteCatalogoInscriptionService(ParticipanteService participanteService, CatalogoService catalogoService, ParticipanteAdapter participanteAdapter, ParticipanteDomainService participanteDomainService) {
        this.participanteService = participanteService;
        this.catalogoService = catalogoService;
        this.participanteAdapter = participanteAdapter;
        this.participanteDomainService = participanteDomainService;
    }


    public Map<String, Object> registerParticipantWithCatalogoComposition(int ciParticipante, List<AreaCatalogoDTO> areaCatalogoDTO) {
        List<Map<String, Object>> results = new ArrayList<>();
        try {
            if (areaCatalogoDTO.size() > 2) {
                throw new IllegalArgumentException("El número de áreas no puede ser mayor a 2");
            }
            Participante participante = participanteService.findByCarnetIdentidadService(ciParticipante);
            if (participante == null) {
                throw new RuntimeException("Participante no encontrado");
            }
            int areasParticipante = participanteAdapter.countParticipantesEnCatalogoParticipante(participante.getIdParticipante());
            if(areasParticipante >= 2){
                throw new RuntimeException("El participante ya tiene áreas registradas en el catálogo, areas registradas :"+ areasParticipante);
            }
            System.out.println("Antes de inserter");
            for (int i = 0; i < areaCatalogoDTO.size(); i++) {
                AreaCatalogoDTO area = areaCatalogoDTO.get(i);
                try {
                    ParticipanteCatalogo participanteCatalogo = new ParticipanteCatalogo();
                    participanteCatalogo.setIdCategoria(area.getIdCategoria());
                    participanteCatalogo.setIdArea(area.getIdArea());
                    participanteCatalogo.setIdCatalogo(area.getIdCatalogo());
                    participanteCatalogo.setIdOlimpiada(area.getIdOlimpiada());
                    participanteCatalogo.setIdInscripcion(participante.getIdInscripcion());
                    participanteCatalogo.setIdParticipante(participante.getIdParticipante());
                    catalogoService.insertParticipanteCatalogo(participanteCatalogo);
                    System.out.println("llega 2");
                    results.add(Map.of(
                            "area", i + 1,
                            "status", "success",
                            "message", "Área registrada correctamente"
                    ));
                } catch (Exception e) {
                    results.add(Map.of(
                            "area", i + 1,
                            "status", "error",
                            "message", "Error al registrar el área: " + e.getMessage()
                    ));
                }
            }

            return Map.of(
                    "message", "Proceso completado",
                    "results", results
            );
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error general: " + e.getMessage());
        }
    }

    public int getParticipanteNumeroDeAreasEnCatalogo(int idParticipante){
        return 1;
    }

    public Optional<ParticipanteAreasDTO> execute(int carnetIdentidad) {
        return participanteDomainService.obtenerAreasPorCarnet(carnetIdentidad);
    }

    public Optional<ParticipanteTutorAreaDTO> getTutorArea(int carnetIdentidad){
        return participanteDomainService.obtenerTutorAreaPorCarnet(carnetIdentidad);
    }

}
