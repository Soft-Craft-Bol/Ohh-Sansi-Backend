package com.softcraft.ohhsansibackend.grado.infraestructure.request;

import java.util.List;

public class NivelEscolarCategoriaAreaDTO {
    private int flag;
    private String codCategory;
    private List<Integer> idArea;
    private List<Integer> nivelesEscolares;

    public NivelEscolarCategoriaAreaDTO(int flag, String codCategory, List<Integer> idArea, List<Integer> nivelesEscolares) {
        this.flag = flag;
        this.codCategory = codCategory;
        this.idArea = idArea;
        this.nivelesEscolares = nivelesEscolares;
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

}