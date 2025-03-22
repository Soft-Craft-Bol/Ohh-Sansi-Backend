package com.softcraft.ohhsansibackend.inscripcion.domain.models;

import jakarta.validation.constraints.*;

public class InscripcionArea {
    private int idInscripcionArea;

    @NotNull(message="El id de inscripción es requerido")
    private int idInscripcion;

    @NotNull(message="El id de área es requerido")
    private int idArea;

    public InscripcionArea() {

    }

    public InscripcionArea(int idInscripcionArea, int idInscripcion, int idArea) {
        this.idInscripcionArea = idInscripcionArea;
        this.idInscripcion = idInscripcion;
        this.idArea = idArea;
    }

    public int getIdInscripcionArea() {
        return idInscripcionArea;
    }

    public void setIdInscripcionArea(int idInscripcionArea) {
        this.idInscripcionArea = idInscripcionArea;
    }

    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }
}
