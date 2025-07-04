package com.softcraft.ohhsansibackend.participante.application.usecases;

import com.softcraft.ohhsansibackend.participante.application.ports.ParticipanteTutorAdapter;
import com.softcraft.ohhsansibackend.participante.domain.models.ParticipanteTutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticipanteTutorService {

    private final ParticipanteTutorAdapter participanteTutorAdapter;

    @Autowired
    public ParticipanteTutorService(ParticipanteTutorAdapter participanteTutorAdapter) {
        this.participanteTutorAdapter = participanteTutorAdapter;
    }

    public ParticipanteTutor createParticipanteTutor(int idTutor, int idInscripcion, int idParticipante, int idTutorParentesco) {
        if (participanteTutorAdapter.existsByTutorAndParticipante(idTutor, idParticipante)) {
            throw new IllegalArgumentException("El tutor ya está registrado para este participante.");
        }

        ParticipanteTutor pt = null;
        try {
            if (idTutor <= 0 || idInscripcion <= 0 || idParticipante <= 0) {
                throw new IllegalArgumentException("Invalid parametros introducidos invalidos");
            }
            pt = participanteTutorAdapter.saveParticipanteTutor(idTutor, idInscripcion, idParticipante, idTutorParentesco);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error en el servicio de ParticipanteTutor: " + e.getMessage());
        }
        return pt;
    }
}