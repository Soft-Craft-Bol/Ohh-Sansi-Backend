package com.softcraft.ohhsansibackend.tutor.application.usecases;

import com.softcraft.ohhsansibackend.exception.DuplicateResourceException;
import com.softcraft.ohhsansibackend.exception.ResourceNotFoundException;
import com.softcraft.ohhsansibackend.inscripcion.application.ports.InscripcionAdapter;
import com.softcraft.ohhsansibackend.participante.application.usecases.ParticipanteService;
import com.softcraft.ohhsansibackend.participante.application.usecases.ParticipanteTutorService;
import com.softcraft.ohhsansibackend.participante.domain.models.Participante;
import com.softcraft.ohhsansibackend.tutor.domain.models.Tutor;
import com.softcraft.ohhsansibackend.tutor.application.ports.TutorAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TutorService {

    private final TutorAdapter tutorAdapter;
    private final ParticipanteService participanteService;
    private final ParticipanteTutorService participanteTutorService;
    private final InscripcionAdapter inscripcionAdapter;
    @Autowired
    public TutorService(TutorAdapter tutorAdapter, ParticipanteService participanteService, ParticipanteTutorService participanteTutorService, InscripcionAdapter inscripcionAdapter) {
        this.tutorAdapter = tutorAdapter;
        this.participanteService = participanteService;
        this.participanteTutorService = participanteTutorService;
        this.inscripcionAdapter = inscripcionAdapter;
    }
    public Map<String, Object> save(List<Tutor> tutors, int carnetParticipante) {
        if (tutors.size() > 3) {
            throw new IllegalArgumentException("No se pueden registrar más de 3 tutores.");
        }

        try {
            Participante searchParticipante = participanteService.findByCarnetIdentidadService(carnetParticipante);

            int tutoresAcademicos = tutorAdapter.countTutorsAcademicosByParticipanteId(searchParticipante.getIdParticipante());
            int tutoresLegales = tutorAdapter.countTutorsLegalesByParticipanteId(searchParticipante.getIdParticipante());
            if (tutoresAcademicos+tutoresLegales  >= 3) {
                throw new IllegalArgumentException("El participante ya tiene el número máximo de tutores registrados permitidos (3).");
            }
            if ((tutoresAcademicos) >= 2) {
                if(inscripcionAdapter.calculateEdad(searchParticipante) >= 15 ){
                    throw new IllegalArgumentException("El participante ya tiene el número máximo de tutores académicos registrados permitidos (2)." +
                            "No es obligatorio registrar un tutor Legar");
                }else{
                    throw new IllegalArgumentException("El participante ya tiene el número máximo de tutores académicos registrados permitidos (2)." +
                            "Tienes que registrar un tutor Legar");
                }
            }
            for (Tutor tutor : tutors) {
                Tutor existingTutor = tutorAdapter.findByCarnetIdentidad(tutor.getCarnetIdentidadTutor());

                if (existingTutor != null) {
                    participanteTutorService.createParticipanteTutor(
                            existingTutor.getIdTutor().intValue(),
                            searchParticipante.getIdInscripcion(),
                            searchParticipante.getIdParticipante()
                    );
                } else {
                    Tutor tutorCreated = tutorAdapter.save(tutor);
                    participanteTutorService.createParticipanteTutor(
                            tutorCreated.getIdTutor().intValue(),
                            searchParticipante.getIdInscripcion(),
                            searchParticipante.getIdParticipante()
                    );
                }
            }
        } catch (DuplicateKeyException e) {
            throw new DuplicateResourceException("Email o carnet de identidad del tutor ya registrados");
        }
        return Map.of("message", "Tutores registrados exitosamente");
    }

    public Map<String, Object> findByIdTutor(int idTutor) {
        try {
            Tutor tutor = tutorAdapter.findByIdTutor(idTutor);
            return Map.of("tutor", tutor);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Error al buscar el tutor");
        }
    }
    public Tutor findByCarnet(String email, int carnetIdentidad) {
        return tutorAdapter.findByCarnetIdentidad(carnetIdentidad);
    }


    public Map<String, Object> findAllTutor() {
        List<Tutor> tutores = tutorAdapter.findAllTutor();
        if (tutores.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron tutores");
        }
        return Map.of("tutores", tutores);
    }

    public List<Tutor> findTutorsByCarnetParticipante(int ciParticipante){
        return tutorAdapter.findTutorsByCarnetParticipante(ciParticipante);
    }
    public int countTutorsAcademicosByParticipanteId(int participanteId) {
        return tutorAdapter.countTutorsAcademicosByParticipanteId(participanteId);
    }
    public int countTutorsLegalesByParticipanteId(int participanteId) {
        return tutorAdapter.countTutorsLegalesByParticipanteId(participanteId);
    }
}
