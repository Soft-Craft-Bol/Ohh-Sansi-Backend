package com.softcraft.ohhsansibackend.ordenPago.dto;

public class AreaNivelDTO {
    private int idArea;
    private String nombreArea;
    private double precioArea;
    private String nombreCortoArea;
    private String descripcionArea;
    private String codigoNivel;

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

    public double getPrecioArea() {
        return precioArea;
    }

    public void setPrecioArea(double precioArea) {
        this.precioArea = precioArea;
    }

    public String getNombreCortoArea() {
        return nombreCortoArea;
    }

    public void setNombreCortoArea(String nombreCortoArea) {
        this.nombreCortoArea = nombreCortoArea;
    }

    public String getDescripcionArea() {
        return descripcionArea;
    }

    public void setDescripcionArea(String descripcionArea) {
        this.descripcionArea = descripcionArea;
    }

    public String getCodigoNivel() {
        return codigoNivel;
    }

    public void setCodigoNivel(String codigoNivel) {
        this.codigoNivel = codigoNivel;
    }
}