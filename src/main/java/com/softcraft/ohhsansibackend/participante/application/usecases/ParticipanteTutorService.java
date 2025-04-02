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

    public ParticipanteTutor createParticipanteTutor(int idTutor, int idInscripcion, int idParticipante) {
        return participanteTutorAdapter.saveParticipanteTutor(idTutor, idInscripcion, idParticipante);
    }
}