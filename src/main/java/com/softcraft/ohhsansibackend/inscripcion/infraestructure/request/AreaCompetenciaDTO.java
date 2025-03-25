package com.softcraft.ohhsansibackend.inscripcion.infraestructure.request;

public class AreaCompetenciaDTO {
    private int idArea;

    public AreaCompetenciaDTO() {
    }

    public AreaCompetenciaDTO(int idArea) {
        this.idArea = idArea;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }
}
