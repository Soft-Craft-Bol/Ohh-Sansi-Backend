package com.softcraft.ohhsansibackend.tutor.application.usecases;

import com.softcraft.ohhsansibackend.area.domain.models.Area;
import com.softcraft.ohhsansibackend.catalogoolimpiadas.application.CatalogoService;
import com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.model.ParticipanteCatalogo;
import com.softcraft.ohhsansibackend.participante.application.usecases.ParticipanteService;
import com.softcraft.ohhsansibackend.participante.domain.models.Participante;
import com.softcraft.ohhsansibackend.participante.domain.models.ParticipanteTutor;
import com.softcraft.ohhsansibackend.participante.domain.services.ParticipanteTutorDomainService;
import com.softcraft.ohhsansibackend.tutor.application.ports.TipoTutorAdapter;
import com.softcraft.ohhsansibackend.tutor.domain.models.TipoTutor;
import com.softcraft.ohhsansibackend.tutor.domain.models.Tutor;
import com.softcraft.ohhsansibackend.tutor.domain.models.TutorAreaParticipante;
import com.softcraft.ohhsansibackend.tutor.domain.repository.implementation.TutorAreaParticipanteDomainRepository;
import com.softcraft.ohhsansibackend.tutor.infraestructure.request.TutorAreaParticipanteDTO;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TutorAreaParticipanteService {
    private final TutorAreaParticipanteDomainRepository tutorAreaParticipanteDomainRepository;
    private final CatalogoService catalogoService;
    private final TutorService tutorService;
    private final TipoTutorAdapter tipoTutorAdapter;
    private final ParticipanteService participanteService;
    private final ParticipanteTutorDomainService participanteTutorDomainService;

    @Autowired
    public TutorAreaParticipanteService(
            TutorAreaParticipanteDomainRepository tutorAreaParticipanteDomainRepository,
            CatalogoService catalogoService,
            TutorService tutorService,
            TipoTutorAdapter tipoTutorAdapter,
            ParticipanteService participanteService,
            ParticipanteTutorDomainService participanteTutorDomainService
    ) {
        this.tutorAreaParticipanteDomainRepository = tutorAreaParticipanteDomainRepository;
        this.catalogoService = catalogoService;
        this.tutorService = tutorService;
        this.tipoTutorAdapter = tipoTutorAdapter;
        this.participanteService = participanteService;
        this.participanteTutorDomainService = participanteTutorDomainService;
    }
    public Map<String,Object> getTutorAreaParticipanteInfo(int ciParticipante){
        Map<String,Object> response = new HashMap<>();
        Participante participante = participanteService.findByCarnetIdentidadService(ciParticipante);
        if(participante==null){
            throw new IllegalArgumentException("Ci de participante no encontrado, documento invalido");
        }
        List<Area> areasParticipante = catalogoService.getAreaCatalogoByCiParticipante(ciParticipante);
        if(areasParticipante.isEmpty()){
            throw new IllegalArgumentException("No se encontraron areas de competencia registradas para el participante");
        }
        if(tutorService.countTutorsAcademicosByParticipanteId(participante.getIdParticipante())==0){
            throw new IllegalArgumentException("No se encontraron tutores académicos registrados para el participante, salta esta etapa o registra tutores académicos");
        }
        List<Tutor> tutorParticipante = tutorService.findTutorsByCarnetParticipante(ciParticipante);
        if(tutorParticipante.isEmpty()){
            throw new IllegalArgumentException("No se encontraron tutores registrados para el participante");
        }
        List<TipoTutor> tipoTutores = tipoTutorAdapter.findAllTipoTutor();
        if(tipoTutores.isEmpty()){
            throw new IllegalArgumentException("Error al obtener los tipos de tutores");
        }
        response.put("participante", participante);
        response.put("areasParticipante", areasParticipante);
        response.put("tutoresParticipante", tutorParticipante);
        response.put("tipoTutores", tipoTutores);
        response.put("status", "success");

        return response;
    }
    public ParticipanteTutor findParticipanteTutorById(int idParticipante, int idTutor){
        return participanteTutorDomainService.findParticipanteTutor(idTutor, idParticipante);
    }

    public Map<String, Object> saveTutorAreaParticipante(List<TutorAreaParticipanteDTO> tutorAreaParticipanteDTOs) {
        Map<String, Object> response = new HashMap<>();
        List<TutorAreaParticipante> insertedRecords = new ArrayList<>();
        for (TutorAreaParticipanteDTO dto : tutorAreaParticipanteDTOs) {
            ParticipanteTutor participanteTutor = participanteTutorDomainService.findParticipanteTutor(
                    dto.getIdTutor(),
                    dto.getIdParticipante()
            );

            if (participanteTutor == null) {
                throw new IllegalArgumentException("ParticipanteTutor no encontrado para los IDs proporcionados");
            }
            TutorAreaParticipante inserted = tutorAreaParticipanteDomainRepository.insert(
                    dto.getIdArea(),
                    participanteTutor.getIdParticipanteTutor()
            );
            insertedRecords.add(inserted);
        }
        response.put("status", "success");
        response.put("insertados", insertedRecords);
        return response;
    }

}
