package com.softcraft.ohhsansibackend.plazoinscripcion.domain.models;

public class FechaOlimpiada {

    private int idPeriodoInscripcionArea;
    private int idPeriodoInscripcion;
    private int idArea;

    public FechaOlimpiada(int idPeriodoInscripcionArea, int idPeriodoInscripcion, int idArea) {
        this.idPeriodoInscripcionArea = idPeriodoInscripcionArea;
        this.idPeriodoInscripcion = idPeriodoInscripcion;
        this.idArea = idArea;
    }

    public FechaOlimpiada() {
    }

    public int getIdPeriodoInscripcionArea() {
        return idPeriodoInscripcionArea;
    }

    public void setIdPeriodoInscripcionArea(int idPeriodoInscripcionArea) {
        this.idPeriodoInscripcionArea = idPeriodoInscripcionArea;
    }

    public int getIdPeriodoInscripcion() {
        return idPeriodoInscripcion;
    }

    public void setIdPeriodoInscripcion(int idPeriodoInscripcion) {
        this.idPeriodoInscripcion = idPeriodoInscripcion;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }
}
