package com.softcraft.ohhsansibackend.tutor.domain.models;

import java.util.List;

public class TutorRegistrationRequest {
    private Integer idTutorParentesco;
    private List<Tutor> tutors;

    public TutorRegistrationRequest() {
    }

    public Integer getIdTutorParentesco() {
        return idTutorParentesco;
    }

    public void setIdTutorParentesco(Integer idTutorParentesco) {
        this.idTutorParentesco = idTutorParentesco;
    }

    public List<Tutor> getTutors() {
        return tutors;
    }

    public void setTutors(List<Tutor> tutors) {
        this.tutors = tutors;
    }
}