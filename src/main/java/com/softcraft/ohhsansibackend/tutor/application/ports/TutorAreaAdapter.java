package com.softcraft.ohhsansibackend.tutor.application.ports;

import com.softcraft.ohhsansibackend.tutor.domain.models.TutorArea;
import com.softcraft.ohhsansibackend.tutor.domain.services.TutorAreaDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TutorAreaAdapter {

    private final TutorAreaDomainService tutorAreaDomainService;

    @Autowired
    public TutorAreaAdapter(TutorAreaDomainService tutorAreaDomainService) {
        this.tutorAreaDomainService = tutorAreaDomainService;
    }

    public TutorArea saveTutorArea(int idArea, int idTutor) {
        return tutorAreaDomainService.createTutorArea(idArea, idTutor);
    }
}