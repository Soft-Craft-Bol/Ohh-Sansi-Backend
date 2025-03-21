package com.softcraft.ohhsansibackend.domain.models;

public class ParticipanteTutor {
    private int idParticipanteTutor;
    private int idTutor;
    private int idParticipante;
    private int idInscripcion;

    public int getIdParticipanteTutor() {
        return idParticipanteTutor;
    }

    public void setIdParticipanteTutor(int idParticipanteTutor) {
        this.idParticipanteTutor = idParticipanteTutor;
    }

    public int getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(int idTutor) {
        this.idTutor = idTutor;
    }

    public int getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(int idParticipante) {
        this.idParticipante = idParticipante;
    }

    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }
}
