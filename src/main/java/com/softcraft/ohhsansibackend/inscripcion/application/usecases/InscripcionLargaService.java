package com.softcraft.ohhsansibackend.inscripcion.application.usecases;

import com.softcraft.ohhsansibackend.area.application.usecases.AreaService;
import com.softcraft.ohhsansibackend.inscripcion.domain.models.Inscripcion;
import com.softcraft.ohhsansibackend.inscripcion.infraestructure.request.AreaCompetenciaDTO;
import com.softcraft.ohhsansibackend.inscripcion.infraestructure.request.InscripcionDTO;
import com.softcraft.ohhsansibackend.inscripcion.infraestructure.request.ParticipanteDTO;
import com.softcraft.ohhsansibackend.inscripcion.infraestructure.request.TutorDTO;
import com.softcraft.ohhsansibackend.participante.application.usecases.ParticipanteService;
import com.softcraft.ohhsansibackend.participante.application.usecases.ParticipanteTutorService;
import com.softcraft.ohhsansibackend.participante.domain.models.Participante;
import com.softcraft.ohhsansibackend.participante.domain.models.ParticipanteTutor;
import com.softcraft.ohhsansibackend.tutor.application.usecases.TutorAreaService;
import com.softcraft.ohhsansibackend.tutor.application.usecases.TutorService;
import com.softcraft.ohhsansibackend.tutor.domain.models.Tutor;
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
        if (inscripcionDTO.getParticipante() == null) {
            throw new IllegalArgumentException("Datos del participante son requeridos");
        }
        Inscripcion inscripcion = new Inscripcion();
        int inscripcionId = inscripcionService.createInscripcionAndReturnId(inscripcion);
        Participante participante = mapParticipanteDTOToEntity(inscripcionDTO.getParticipante(), inscripcionId);
        participanteService.save(participante);

        if (participante.getIdParticipante() == 0) {
            throw new IllegalStateException("Participante no fue registrado correctamente");
        }
        if (inscripcionDTO.getAreasCompetenciaEstudiante() != null) {
            for (AreaCompetenciaDTO areaDTO : inscripcionDTO.getAreasCompetenciaEstudiante()) {
                inscripcionAreaService.createInscripcionArea(inscripcionId, areaDTO.getIdArea());
            }
        }
        if (inscripcionDTO.getTutores() != null) {
            for (TutorDTO tutorDTO : inscripcionDTO.getTutores()) {
                Tutor tutor = registerOrUpdateTutor(tutorDTO);
                registerParticipanteTutor(participante, tutor, inscripcionId);
                if (tutorDTO.getAreaCompetencia() != null) {
                    tutorAreaService.createTutorArea(tutorDTO.getAreaCompetencia(), tutor.getIdTutor().intValue());
                }
            }
        }

        return Map.of(
                "success", true,
                "message", "Inscripción registrada exitosamente",
                "inscripcionId", inscripcionId,
                "participanteId", participante.getIdParticipante()
        );
    }

    private Participante mapParticipanteDTOToEntity(ParticipanteDTO participanteDTO, int inscripcionId) {
        Participante participante = new Participante();
        participante.setIdInscripcion(inscripcionId);
        participante.setIdDepartamento(participanteDTO.getIdDepartamento());
        participante.setIdMunicipio(participanteDTO.getIdMunicipio());
        participante.setIdColegio(participanteDTO.getIdColegio());
        participante.setIdGrado(participanteDTO.getIdNivelGradoEscolar());
        participante.setApellidoPaterno(participanteDTO.getApellidoPaterno());
        participante.setApellidoMaterno(participanteDTO.getApellidoMaterno());
        participante.setNombreParticipante(participanteDTO.getNombreParticipante());
        participante.setFechaNacimiento(participanteDTO.getFechaNacimiento());
        //participante.setCorreoElectronicoParticipante(participanteDTO.getCorreoElectronicoParticipante());
        participante.setCarnetIdentidadParticipante(participanteDTO.getCarnetIdentidadParticipante());
        return participante;
    }

    private Tutor registerOrUpdateTutor(TutorDTO tutorDTO) {
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
        }
        if (tutor.getIdTutor() == null) {
            throw new IllegalStateException("Error al registrar el tutor");
        }
        return tutor;
    }
    private void registerParticipanteTutor(Participante participante, Tutor tutor, int inscripcionId) {
        ParticipanteTutor participanteTutor = new ParticipanteTutor();
        participanteTutor.setIdTutor(tutor.getIdTutor().intValue());
        participanteTutor.setIdInscripcion(inscripcionId);
        participanteTutor.setIdParticipante(participante.getIdParticipante());
        participanteTutorService.createParticipanteTutor(
                participanteTutor.getIdTutor(),
                participanteTutor.getIdInscripcion(),
                participanteTutor.getIdParticipante()
        );
    }
}