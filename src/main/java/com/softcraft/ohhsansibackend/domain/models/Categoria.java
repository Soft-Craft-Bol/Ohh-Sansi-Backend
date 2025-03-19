package com.softcraft.ohhsansibackend.domain.models;

public class Categoria {
    private Long idCategoria;
    private Long idArea;
    private Long idTipoMateriaCategoria;
    private String codigoCategoria;

    public Categoria() {

    }

    public Categoria(Long idCategoria, Long idArea, Long idTipoMateriaCategoria, String codigoCategoria) {
        this.idCategoria = idCategoria;
        this.idArea = idArea;
        this.idTipoMateriaCategoria = idTipoMateriaCategoria;
        this.codigoCategoria = codigoCategoria;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Long getIdArea() {
        return idArea;
    }

    public void setIdArea(Long idArea) {
        this.idArea = idArea;
    }

    public Long getIdTipoMateriaCategoria() {
        return idTipoMateriaCategoria;
    }

    public void setIdTipoMateriaCategoria(Long idTipoMateriaCategoria) {
        this.idTipoMateriaCategoria = idTipoMateriaCategoria;
    }


    public String getCodigoCategoria() {
        return codigoCategoria;
    }

    public void setCodigoCategoria(String codigoCategoria) {
        this.codigoCategoria = codigoCategoria;
    }

}
