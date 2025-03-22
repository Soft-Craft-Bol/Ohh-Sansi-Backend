package com.softcraft.ohhsansibackend.nivelescolar.domain.models;

import jakarta.validation.constraints.NotNull;

public class NivelEscolarCategorias {
    private int idNivelEscolarCategorias;

    @NotNull(message = "El id del nivel no puede ser nulo")
    private int idNivel;

    @NotNull(message = "El id de la categoria no puede ser nulo")
    private int idCategoria;

    public NivelEscolarCategorias() {
    }

    public NivelEscolarCategorias(int idNivelEscolarCategorias, int idNivel, int idCategoria) {
        this.idNivelEscolarCategorias = idNivelEscolarCategorias;
        this.idNivel = idNivel;
        this.idCategoria = idCategoria;
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
