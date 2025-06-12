package com.softcraft.ohhsansibackend.area.domain.models;

public class Convocatoria {
    private int idArea;
    private int idOlimpiada;
    private int idPdfConvocatoria;


    public Convocatoria() {}

    public Convocatoria(int idArea, int idOlimpiada, int idPdfConvocatoria) {
        this.idArea = idArea;
        this.idOlimpiada = idOlimpiada;
        this.idPdfConvocatoria = idPdfConvocatoria;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public int getIdOlimpiada() {
        return idOlimpiada;
    }

    public void setIdOlimpiada(int idOlimpiada) {
        this.idOlimpiada = idOlimpiada;
    }

    public int getIdPdfConvocatoria() {
        return idPdfConvocatoria;
    }

    public void setIdPdfConvocatoria(int idPdfConvocatoria) {
        this.idPdfConvocatoria = idPdfConvocatoria;
    }
}
