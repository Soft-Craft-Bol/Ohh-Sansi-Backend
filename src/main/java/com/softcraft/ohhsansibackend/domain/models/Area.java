package com.softcraft.ohhsansibackend.domain.models;

public class Area {
    private int idArea;
    private String nombreArea;
    private Number precioArea;
    private String nombreCortoArea;

    public Area() {

    }

    public Area(int idArea, String nombreArea, Number precioArea, String nombreCortoArea) {
        this.idArea = idArea;
        this.nombreArea = nombreArea;
        this.precioArea = precioArea;
        this.nombreCortoArea = nombreCortoArea;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    public Number getPrecioArea() {
        return precioArea;
    }

    public void setPrecioArea(Number precioArea) {
        this.precioArea = precioArea;
    }

    public String getNombreCortoArea() {
        return nombreCortoArea;
    }

    public void setNombreCortoArea(String nombreCortoArea) {
        this.nombreCortoArea = nombreCortoArea;
    }
}


