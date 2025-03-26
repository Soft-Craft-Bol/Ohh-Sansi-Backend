package com.softcraft.ohhsansibackend.participante.application.ports;

import com.softcraft.ohhsansibackend.participante.domain.models.ParticipanteTutor;
import com.softcraft.ohhsansibackend.participante.domain.services.ParticipanteTutorDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ParticipanteTutorAdapter {

    private final ParticipanteTutorDomainService participanteTutorDomainService;

    @Autowired
    public ParticipanteTutorAdapter(ParticipanteTutorDomainService participanteTutorDomainService) {
        this.participanteTutorDomainService = participanteTutorDomainService;
    }

    public ParticipanteTutor saveParticipanteTutor(int idTutor, int idInscripcion, int idParticipante) {
        return participanteTutorDomainService.createParticipanteTutor(idTutor, idInscripcion, idParticipante);
    }
}