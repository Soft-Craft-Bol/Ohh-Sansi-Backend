package com.softcraft.ohhsansibackend.participante.domain.models;

public class ParticipanteTutor {
    private int idTutor;
    private int idInscripcion;
    private int idParticipante;
    private int idParticipanteTutor;
    private int idTutorParentesco;

    public int getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(int idTutor) {
        this.idTutor = idTutor;
    }

    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public int getIdTutorParentesco(){return idTutorParentesco;}

    public void setIdTutorParentesco(int idTutorParentesco){this.idTutorParentesco = idTutorParentesco;}

    public int getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(int idParticipante) {
        this.idParticipante = idParticipante;
    }

    public int getIdParticipanteTutor() {
        return idParticipanteTutor;
    }

    public void setIdParticipanteTutor(int idParticipanteTutor) {
        this.idParticipanteTutor = idParticipanteTutor;
    }
}
