package com.softcraft.ohhsansibackend.tutor.application.ports;

import com.softcraft.ohhsansibackend.tutor.domain.models.Tutor;
import com.softcraft.ohhsansibackend.tutor.domain.services.TutorDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TutorAdapter {

    private final TutorDomainService tutorDomainService;

    @Autowired
    public TutorAdapter(TutorDomainService tutorDomainService) {
        this.tutorDomainService = tutorDomainService;
    }

    public Tutor save(Tutor tutor) {
        return tutorDomainService.save(tutor);
    }

    public Tutor findByIdTutor(int idTutor) {
        return tutorDomainService.findByIdTutor(idTutor);
    }

    public List<Tutor> findAllTutor() {
        return tutorDomainService.findAllTutor();
    }

    public Tutor findByEmail(String email) {
        return tutorDomainService.findByEmail(email);
    }
    public Tutor findByCarnetIdentidad(int carnetIdentidad) {
        return tutorDomainService.findByCarnetIdentidad(carnetIdentidad);
    }
    public int countTutorsByParticipanteId(int participanteId) {
        return tutorDomainService.countTutorsByParticipanteId(participanteId);
    }

    public List<Tutor> findTutorsByCarnetParticipante(int ciParticipante){
        return tutorDomainService.findTutorsByCarnetParticipante(ciParticipante);
    }
}