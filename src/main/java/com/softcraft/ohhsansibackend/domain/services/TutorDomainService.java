package com.softcraft.ohhsansibackend.domain.services;

import com.softcraft.ohhsansibackend.domain.models.Tutor;
import com.softcraft.ohhsansibackend.domain.repository.implementation.TutorDomainRepository;
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
}
