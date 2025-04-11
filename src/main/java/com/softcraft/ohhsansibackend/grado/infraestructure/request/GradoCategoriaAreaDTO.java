package com.softcraft.ohhsansibackend.grado.infraestructure.request;

import java.util.List;

public class GradoCategoriaAreaDTO {
    private int flag;
    private String nombreCategoria;
    private List<Integer> idArea;
    private List<Integer> grados;

    public GradoCategoriaAreaDTO(int flag, String nombreCategoria, List<Integer> idArea, List<Integer> grados) {
        this.flag = flag;
        this.nombreCategoria = nombreCategoria;
        this.idArea = idArea;
        this.grados = grados;
    }

    public GradoCategoriaAreaDTO() {}

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public List<Integer> getIdArea() {
        return idArea;
    }

    public void setIdArea(List<Integer> idArea) {
        this.idArea = idArea;
    }

    public List<Integer> getGrados() {
        return grados;
    }

    public void setGrados(List<Integer> grados) {
        this.grados = grados;
    }

}