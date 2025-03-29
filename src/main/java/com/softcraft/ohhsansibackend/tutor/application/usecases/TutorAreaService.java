package com.softcraft.ohhsansibackend.tutor.application.usecases;

import com.softcraft.ohhsansibackend.tutor.application.ports.TutorAreaAdapter;
import com.softcraft.ohhsansibackend.tutor.domain.models.TutorArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutorAreaService {

    private final TutorAreaAdapter tutorAreaAdapter;

    @Autowired
    public TutorAreaService(TutorAreaAdapter tutorAreaAdapter) {
        this.tutorAreaAdapter = tutorAreaAdapter;
    }

    public TutorArea createTutorArea(int idArea, int idTutor) {
        return tutorAreaAdapter.saveTutorArea(idArea, idTutor);
    }
}
