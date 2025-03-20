package com.softcraft.ohhsansibackend.domain.models;

public class Area {
    private Long idArea;
    private String nombreArea;

    public Area() {

    }

    public Area(Long idArea, String nombreArea) {
        this.idArea = idArea;
        this.nombreArea = nombreArea;
    }

    public Long getIdArea() {
        return idArea;
    }

    public void setIdArea(Long idArea) {
        this.idArea = idArea;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }
}


