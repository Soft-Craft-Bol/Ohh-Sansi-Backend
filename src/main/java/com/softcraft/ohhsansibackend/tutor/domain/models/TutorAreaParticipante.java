package com.softcraft.ohhsansibackend.tutor.domain.models;

public class TutorAreaParticipante {
    private int tutorAreaParticipante;
    private int idParticipanteTutor;
    private int idArea;

    public int getTutorAreaParticipante() {
        return tutorAreaParticipante;
    }

    public void setTutorAreaParticipante(int tutorAreaParticipante) {
        this.tutorAreaParticipante = tutorAreaParticipante;
    }

    public int getIdParticipanteTutor() {
        return idParticipanteTutor;
    }

    public void setIdParticipanteTutor(int idParticipanteTutor) {
        this.idParticipanteTutor = idParticipanteTutor;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }
}
