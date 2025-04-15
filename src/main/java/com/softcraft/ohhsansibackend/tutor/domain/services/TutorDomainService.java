package com.softcraft.ohhsansibackend.tutor.domain.services;

import com.softcraft.ohhsansibackend.tutor.domain.models.Tutor;
import com.softcraft.ohhsansibackend.tutor.domain.repository.implementation.TutorDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorDomainService {

    private final TutorDomainRepository tutorDomainRepository;

    @Autowired
    public TutorDomainService(TutorDomainRepository tutorDomainRepository) {
        this.tutorDomainRepository = tutorDomainRepository;
    }

    public Tutor save(Tutor tutor) {
        return tutorDomainRepository.save(tutor);
    }

    public Tutor findByIdTutor(int idTutor) {
        return tutorDomainRepository.findByIdTutor(idTutor);
    }

    public List<Tutor> findAllTutor() {
        return tutorDomainRepository.findAllTutor();
    }

    public Tutor findByEmail(String email) {
        return tutorDomainRepository.findByEmail(email);
    }
    public Tutor findByCarnetIdentidad(int carnetIdentidad) {
        return tutorDomainRepository.findByCarnetIdentidad(carnetIdentidad);
    }
    public int countTutorsAcademicosByParticipanteId(int participanteId) {
        return tutorDomainRepository.countTutorsAcademicosByParticipanteId(participanteId);
    }
    public int countTutorsLegalesByParticipanteId(int participanteId) {
        return tutorDomainRepository.countTutorsLegalesByParticipanteId(participanteId);
    }
    public List<Tutor> findTutorsByCarnetParticipante(int ciParticipante){
        return tutorDomainRepository.findTutorsByCarnetParticipante(ciParticipante);
    }

}
