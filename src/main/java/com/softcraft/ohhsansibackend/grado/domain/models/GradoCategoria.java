package com.softcraft.ohhsansibackend.grado.domain.models;

import jakarta.validation.constraints.NotNull;

public class GradoCategoria {
    private int idGradoCategoria;

    @NotNull(message = "El id del grado no puede ser nulo")
    private int idGrado;

    @NotNull(message = "El id de la categoria no puede ser nulo")
    private int idCategoria;

    public GradoCategoria(int idGradoCategoria, int idGrado,  int idCategoria) {
        this.idGradoCategoria = idGradoCategoria;
        this.idGrado= idGrado;
        this.idCategoria = idCategoria;
    }

    public GradoCategoria() {
    }

    public int getIdGradoCategoria() {
        return idGradoCategoria;
    }

    public void setIdGradoCategoria(int idGradoCategoria) {
        this.idGradoCategoria = idGradoCategoria;
    }

    public int getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(int idGrado) {
        this.idGrado = idGrado;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
}
