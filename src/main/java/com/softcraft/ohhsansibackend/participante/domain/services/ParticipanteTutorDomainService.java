package com.softcraft.ohhsansibackend.participante.domain.services;

import com.softcraft.ohhsansibackend.participante.domain.models.ParticipanteTutor;
import com.softcraft.ohhsansibackend.participante.domain.repository.implementation.ParticipanteTutorDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticipanteTutorDomainService {

    private final ParticipanteTutorDomainRepository participanteTutorRepository;

    @Autowired
    public ParticipanteTutorDomainService(ParticipanteTutorDomainRepository participanteTutorRepository) {
        this.participanteTutorRepository = participanteTutorRepository;
    }

    public ParticipanteTutor createParticipanteTutor(int idTutor, int idInscripcion, int idParticipante) {
        ParticipanteTutor participanteTutor = new ParticipanteTutor();
        participanteTutor.setIdTutor(idTutor);
        participanteTutor.setIdInscripcion(idInscripcion);
        participanteTutor.setIdParticipante(idParticipante);
        return participanteTutorRepository.save(participanteTutor);
    }
}