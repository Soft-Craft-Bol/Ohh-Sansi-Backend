package com.softcraft.ohhsansibackend.ordenPago.dto;

public class AreaCategoriaNivelDTO {
    private int idArea;
    private String nombreArea;
    private double precioArea;
    private String nombreCortoArea;
    private String descripcionArea;
    private int idCategoria;
    private String codigoCategoria;
    private int idNivel;
    private String codigoNivel;
    private String nombreNivelEscolar;

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

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCodigoCategoria() {
        return codigoCategoria;
    }

    public void setCodigoCategoria(String codigoCategoria) {
        this.codigoCategoria = codigoCategoria;
    }

    public int getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(int idNivel) {
        this.idNivel = idNivel;
    }

    public String getCodigoNivel() {
        return codigoNivel;
    }

    public void setCodigoNivel(String codigoNivel) {
        this.codigoNivel = codigoNivel;
    }

    public String getNombreNivelEscolar() {
        return nombreNivelEscolar;
    }

    public void setNombreNivelEscolar(String nombreNivelEscolar) {
        this.nombreNivelEscolar = nombreNivelEscolar;
    }
}