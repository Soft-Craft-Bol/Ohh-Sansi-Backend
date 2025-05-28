package com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.model;

public class CatalogoOlimpiada {
    private int idOlimpiada;
    private int idArea;
    private int idCategoria;
    private Integer idCatalogo;

    public CatalogoOlimpiada(int idOlimpiada, int idArea, int idCategoria, Integer idCatalogo) {
        this.idOlimpiada = idOlimpiada;
        this.idArea = idArea;
        this.idCategoria = idCategoria;
        this.idCatalogo = idCatalogo;
    }

    public CatalogoOlimpiada() {
    }

    public int getIdOlimpiada() {
        return idOlimpiada;
    }

    public void setIdOlimpiada(int idOlimpiada) {
        this.idOlimpiada = idOlimpiada;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Integer getIdCatalogo() {
        return idCatalogo;
    }

    public void setIdCatalogo(Integer idCatalogo) {
        this.idCatalogo = idCatalogo;
    }
}
