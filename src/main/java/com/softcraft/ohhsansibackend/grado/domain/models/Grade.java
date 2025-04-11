package com.softcraft.ohhsansibackend.grado.domain.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Grade {
    private int idGrado;

    @NotBlank(message = "El nombre del grado no puede estar vacío")
    @Size(min = 15, max = 55, message = "El nombre del grado debe tener entre 15 y 55 caracteres")
    private String nombreGrado;

    public Grade() {
    }

    public Grade(int idGrado, String nombreGrado) {
        this.idGrado = idGrado;
        this.nombreGrado = nombreGrado;
    }

    public int getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(int idGrado) {
        this.idGrado = idGrado;
    }

    public String getNombreGrado() {
        return nombreGrado;
    }

    public void setNombreGrado(String nombreGrado) {
        this.nombreGrado = nombreGrado;
    }
}
