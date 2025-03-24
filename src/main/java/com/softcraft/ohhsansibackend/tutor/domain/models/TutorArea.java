package com.softcraft.ohhsansibackend.tutor.domain.models;

public class TutorArea {
    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public int getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(int idTutor) {
        this.idTutor = idTutor;
    }

    public int getIdTutorArea() {
        return idTutorArea;
    }

    public void setIdTutorArea(int idTutorArea) {
        this.idTutorArea = idTutorArea;
    }

    private int idArea;
    private int idTutor;
    private int idTutorArea;
}
