package com.softcraft.ohhsansibackend.domain.models;

public class Category {
    private int idCategoria;
    private String codigoCategoria;
    private int idArea;

    public Category() {

    }

    public Category(int idArea, int idCategoria, String codigoCategoria) {
        this.idCategoria = idCategoria;
        this.codigoCategoria = codigoCategoria;
        this.idArea = idArea;
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

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }
}
