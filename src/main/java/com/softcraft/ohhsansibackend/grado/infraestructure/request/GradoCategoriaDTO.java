package com.softcraft.ohhsansibackend.grado.infraestructure.request;

import java.util.List;

public class GradoCategoriaDTO {
    private String nombreCategoria;
    private List<Integer> grados;

    public GradoCategoriaDTO( String nombreCategoria, List<Integer> grados) {
        this.nombreCategoria = nombreCategoria;
        this.grados = grados;
    }

    public GradoCategoriaDTO() {}


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