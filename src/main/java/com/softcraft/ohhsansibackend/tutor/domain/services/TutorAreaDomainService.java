package com.softcraft.ohhsansibackend.tutor.domain.services;

import com.softcraft.ohhsansibackend.tutor.domain.models.TutorArea;
import com.softcraft.ohhsansibackend.tutor.domain.repository.abstraction.TutorAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutorAreaDomainService {

    private final TutorAreaRepository tutorAreaRepository;

    @Autowired
    public TutorAreaDomainService(TutorAreaRepository tutorAreaRepository) {
        this.tutorAreaRepository = tutorAreaRepository;
    }

    public TutorArea createTutorArea(int idArea, int idTutor) {
        TutorArea tutorArea = new TutorArea();
        tutorArea.setIdArea(idArea);
        tutorArea.setIdTutor(idTutor);
        return tutorAreaRepository.save(tutorArea);
    }
}