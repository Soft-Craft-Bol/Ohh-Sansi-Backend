package com.softcraft.ohhsansibackend.categoria.domain.models;

import com.softcraft.ohhsansibackend.validation.LettersAndSpaces;
import jakarta.validation.constraints.*;

public class Category {
    private int idCategoria;

    @NotBlank(message = "El nombre de la categoría no puede estar vacía")
    @Size(max = 100,  message = "El nombre de la categoría no puede tener más de 100 caracteres")
    @LettersAndSpaces
    private String nombreCategoria;
    public Category() {

    }
    public Category(int idCategoria, String nombreCategoria) {
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
    }

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
}
