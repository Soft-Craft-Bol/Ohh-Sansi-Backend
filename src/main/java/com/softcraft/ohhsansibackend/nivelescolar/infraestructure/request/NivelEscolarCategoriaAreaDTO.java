package com.softcraft.ohhsansibackend.nivelescolar.infraestructure.request;

import java.util.List;

public class NivelEscolarCategoriaAreaDTO {
    private int flag;
    private String codCategory;
    private String descripcion;
    private List<Integer> idArea;
    private List<Integer> nivelesEscolares;
    private boolean status;

    public NivelEscolarCategoriaAreaDTO(int flag, String codCategory, String descripcion, List<Integer> idArea, List<Integer> nivelesEscolares, boolean status) {
        this.flag = flag;
        this.codCategory = codCategory;
        this.descripcion = descripcion;
        this.idArea = idArea;
        this.nivelesEscolares = nivelesEscolares;
        this.status = status;
    }

    public NivelEscolarCategoriaAreaDTO() {

    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getCodCategory() {
        return codCategory;
    }

    public void setCodCategory(String codCategory) {
        this.codCategory = codCategory;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Integer> getIdArea() {
        return idArea;
    }

    public void setIdArea(List<Integer> idArea) {
        this.idArea = idArea;
    }

    public List<Integer> getNivelesEscolares() {
        return nivelesEscolares;
    }

    public void setNivelesEscolares(List<Integer> nivelesEscolares) {
        this.nivelesEscolares = nivelesEscolares;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}