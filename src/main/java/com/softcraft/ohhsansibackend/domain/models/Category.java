package com.softcraft.ohhsansibackend.domain.models;

import jakarta.validation.constraints.*;

public class Category {
    private int idCategoria;

    @NotBlank(message = "El código de la categoría no puede estar vacío")
    @Size(max = 10,  message = "El código de la categoría no puede tener más de 10 caracteres")
    private String codigoCategoria;

    @NotNull(message = "El id del área es requerido")
    private int idArea;

    public Category() {

    }

    public Category(int idArea, int idCategoria, String codigoCategoria) {
        this.idCategoria = idCategoria;
        this.codigoCategoria = codigoCategoria;
        this.idArea = idArea;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCodigoCategoria() {
        return codigoCategoria;
    }

    public void setCodigoCategoria(String codigoCategoria) {
        this.codigoCategoria = codigoCategoria;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }
}
