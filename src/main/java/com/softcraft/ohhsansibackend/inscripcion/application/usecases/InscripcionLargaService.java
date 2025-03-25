package com.softcraft.ohhsansibackend.inscripcion.application.usecases;

import com.softcraft.ohhsansibackend.area.application.usecases.AreaService;
import com.softcraft.ohhsansibackend.area.domain.models.Area;
import com.softcraft.ohhsansibackend.colegio.application.usecases.ColegioService;
import com.softcraft.ohhsansibackend.exception.ResourceNotFoundException;
import com.softcraft.ohhsansibackend.inscripcion.domain.models.Inscripcion;
import com.softcraft.ohhsansibackend.inscripcion.infraestructure.request.InscripcionDTO;
import com.softcraft.ohhsansibackend.inscripcion.infraestructure.request.TutorDTO;
import com.softcraft.ohhsansibackend.participante.application.usecases.ParticipanteService;
import com.softcraft.ohhsansibackend.participante.application.usecases.ParticipanteTutorService;
import com.softcraft.ohhsansibackend.participante.domain.models.Participante;
import com.softcraft.ohhsansibackend.participante.domain.models.ParticipanteTutor;
import com.softcraft.ohhsansibackend.tutor.application.usecases.TutorAreaService;
import com.softcraft.ohhsansibackend.tutor.application.usecases.TutorService;
import com.softcraft.ohhsansibackend.tutor.domain.models.Tutor;
import com.softcraft.ohhsansibackend.tutor.domain.models.TutorArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class InscripcionLargaService {
    private final InscripcionService inscripcionService;
    private final ParticipanteService participanteService;
    private final ParticipanteTutorService participanteTutorService;
    private final TutorService tutorService;
    private final TutorAreaService tutorAreaService;
    private final AreaService areaService;
    private final InscripcionAreaService inscripcionAreaService;

    @Autowired
    public InscripcionLargaService(InscripcionService inscripcionService, ParticipanteService participanteService,
                                   ParticipanteTutorService participanteTutorService, TutorService tutorService,
                                   TutorAreaService tutorAreaService, AreaService areaService,
                                   InscripcionAreaService inscripcionAreaService) {
        this.inscripcionService = inscripcionService;
        this.participanteService = participanteService;
        this.participanteTutorService = participanteTutorService;
        this.tutorService = tutorService;
        this.tutorAreaService = tutorAreaService;
        this.areaService = areaService;
        this.inscripcionAreaService = inscripcionAreaService;
    }

    public Map<String, Object> registerInscripcion(InscripcionDTO inscripcionDTO) {
        Inscripcion inscripcion = new Inscripcion();
        int inscripcionId = inscripcionService.createInscripcionAndReturnId(inscripcion);
        Participante participante = new Participante();
        participante.setIdInscripcion(inscripcionId);
        participante.setIdDepartamento(inscripcionDTO.getParticipante().getIdDepartamento());
        participante.setIdMunicipio(inscripcionDTO.getParticipante().getIdMunicipio());
        participante.setIdColegio(inscripcionDTO.getParticipante().getIdColegio());
        participante.setIdNivel(inscripcionDTO.getParticipante().getIdNivelGradoEscolar());
        participante.setApellidoPaterno(inscripcionDTO.getParticipante().getApellidoPaterno());
        participante.setApellidoMaterno(inscripcionDTO.getParticipante().getApellidoMaterno());
        participante.setNombreParticipante(inscripcionDTO.getParticipante().getNombreParticipante());
        participante.setFechaNacimiento(inscripcionDTO.getParticipante().getFechaNacimiento());
        participante.setCorreoElectronicoParticipante(inscripcionDTO.getParticipante().getCorreoElectronicoParticipante());
        participante.setCarnetIdentidadParticipante(inscripcionDTO.getParticipante().getCarnetIdentidadParticipante());
        participanteService.save(participante);
        if (participante.getIdParticipante() == 0) {
            throw new IllegalStateException("participante no añadido, falta id");
        }
        for (Map.Entry<String, TutorDTO> entry : inscripcionDTO.getTutores().entrySet()) {
            TutorDTO tutorDTO = entry.getValue();
            Tutor tutor = tutorService.findByEmailOrCarnet(tutorDTO.getEmailTutor(), tutorDTO.getCarnetIdentidadTutor());
            if (tutor == null) {
                tutor = new Tutor();
                tutor.setIdTipoTutor(tutorDTO.getIdTipoTutor());
                tutor.setEmailTutor(tutorDTO.getEmailTutor());
                tutor.setNombresTutor(tutorDTO.getNombresTutor());
                tutor.setApellidosTutor(tutorDTO.getApellidosTutor());
                tutor.setTelefono(tutorDTO.getTelefono());
                tutor.setCarnetIdentidadTutor(tutorDTO.getCarnetIdentidadTutor());
                tutorService.save(tutor);
                if (tutor.getIdTutor() == null) {
                    throw new IllegalStateException("tutor no añadido, falta id");
                }
            }
            ParticipanteTutor participanteTutor = new ParticipanteTutor();
            participanteTutor.setIdTutor(tutor.getIdTutor().intValue());
            participanteTutor.setIdInscripcion(inscripcionId);
            participanteTutor.setIdParticipante(participante.getIdParticipante());
            participanteTutorService.createParticipanteTutor(participanteTutor.getIdTutor(), participanteTutor.getIdInscripcion(), participanteTutor.getIdParticipante());
        }
        for (Map.Entry<String, Integer> entry : inscripcionDTO.getTutorAreaDecompetencia().entrySet()) {
            int idArea = entry.getValue();
            inscripcionAreaService.createInscripcionArea(inscripcionId, idArea);
        }

        return Map.of("success", true, "message", "Inscripcion larga registrada exitosamente", "inscripcionId", inscripcionId);
    }
}