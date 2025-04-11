package com.softcraft.ohhsansibackend.categoria.domain.models;

import com.softcraft.ohhsansibackend.validation.LettersAndSpaces;
import jakarta.validation.constraints.*;

public class Category {
    private int idCategoria;

    @NotBlank(message = "El código de la categoría no puede estar vacío")
    @Size(max = 10,  message = "El código de la categoría no puede tener más de 10 caracteres")
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
