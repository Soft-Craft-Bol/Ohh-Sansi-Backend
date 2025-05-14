package com.softcraft.ohhsansibackend.grado.infraestructure.request;

import java.util.List;

public class GradoCategoriaDTO {
    private int idCategoria;
    private String nombreCategoria;
    private List<Integer> grados;

    public GradoCategoriaDTO(int idCategoria, String nombreCategoria, List<Integer> grados) {
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
        this.grados = grados;
    }

    public GradoCategoriaDTO() {}

    public int getIdCategoria() {
        return idCategoria;
    }
    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public List<Integer> getGrados() {
        return grados;
    }

    public void setGrados(List<Integer> grados) {
        this.grados = grados;
    }

}