package com.softcraft.ohhsansibackend.area.domain.models;

import jakarta.validation.constraints.*;


import java.math.BigDecimal;

public class Area {
    private int idArea;

    @NotBlank(message = "El nombre del área no puede estar vacío")
    @Size(max = 100, message = "El nombre del área no puede tener más de 100 caracteres")
    private String nombreArea;

    @NotBlank(message = "El nombre corto del área no puede estar vacío")
    @Size(max = 10, message = "El nombre corto del área no puede tener más de 10 caracteres")
    private String nombreCortoArea;

    @Size(max=500, message = "La descripción del área no puede tener más de 500 caracteres")
    @NotNull(message = "La descripción del área no puede estar vacía")
    private String descripcionArea;

    public Area() {

    }

    public Area(int idArea, String nombreArea, String descripcionArea, String nombreCortoArea) {
        this.idArea = idArea;
        this.nombreArea = nombreArea;
        this.descripcionArea = descripcionArea;
        this.nombreCortoArea = nombreCortoArea;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    public String getDescripcionArea() {
        return descripcionArea;
    }

    public void setDescripcionArea(String descripcionArea) {
        this.descripcionArea = descripcionArea;
    }

    public String getNombreCortoArea() {
        return nombreCortoArea;
    }

    public void setNombreCortoArea(String nombreCortoArea) {
        this.nombreCortoArea = nombreCortoArea;
    }
}


