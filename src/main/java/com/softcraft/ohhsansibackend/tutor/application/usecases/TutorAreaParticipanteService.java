package com.softcraft.ohhsansibackend.tutor.application.usecases;

import com.softcraft.ohhsansibackend.tutor.domain.repository.implementation.TutorAreaParticipanteDomainRepository;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutorAreaParticipanteService {
    private final TutorAreaParticipanteDomainRepository tutorAreaParticipanteDomainRepository;
    @Autowired
    public TutorAreaParticipanteService(TutorAreaParticipanteDomainRepository tutorAreaParticipanteDomainRepository) {
        this.tutorAreaParticipanteDomainRepository = tutorAreaParticipanteDomainRepository;
    }
}
