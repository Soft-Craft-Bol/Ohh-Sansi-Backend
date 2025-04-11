package com.softcraft.ohhsansibackend.grado.domain.models;

import jakarta.validation.constraints.NotNull;

public class GradeCategory {
    private int idGradoCategorias;

    @NotNull(message = "El id del nivel no puede ser nulo")
    private int idNivel;

    private int idArea;

    @NotNull(message = "El id de la categoria no puede ser nulo")
    private int idCategoria;

    public NivelEscolarCategorias(int idNivelEscolarCategorias, int idNivel, int idArea, int idCategoria) {
        this.idNivelEscolarCategorias = idNivelEscolarCategorias;
        this.idNivel = idNivel;
        this.idArea = idArea;
        this.idCategoria = idCategoria;
    }

    public NivelEscolarCategorias() {
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public int getIdNivelEscolarCategorias() {
        return idNivelEscolarCategorias;
    }

    public void setIdNivelEscolarCategorias(int idNivelEscolarCategorias) {
        this.idNivelEscolarCategorias = idNivelEscolarCategorias;
    }

    public int getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(int idNivel) {
        this.idNivel = idNivel;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
}
