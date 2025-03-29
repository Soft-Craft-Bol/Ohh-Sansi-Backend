package com.softcraft.ohhsansibackend.area.domain.models;

import jakarta.validation.constraints.*;


import java.math.BigDecimal;

public class Area {
    private int idArea;

    @NotBlank(message = "El nombre del área no puede estar vacío")
    @Size(max = 100, message = "El nombre del área no puede tener más de 100 caracteres")
    private String nombreArea;

    private BigDecimal precioArea;

    private String nombreCortoArea;

    @NotBlank(message = "La descripción del área no puede estar vacía")
    @Size(max = 255, message = "La descripción del área no puede tener más de 100 caracteres")
    private String descripcionArea;

    @NotNull(message = "El estado del área no puede ser nulo")
    @AssertTrue(message = "El estado del área debe ser verdadero o falso")
    private Boolean areaStatus;
    public Area() {

    }

    public Area(int idArea, String nombreArea, BigDecimal precioArea, String nombreCortoArea , String descripcionArea, Boolean areaStatus) {
        this.idArea = idArea;
        this.nombreArea = nombreArea;
        this.precioArea = precioArea;
        this.nombreCortoArea = nombreCortoArea;
        this.descripcionArea = descripcionArea;
        this.areaStatus = areaStatus;
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

    public BigDecimal getPrecioArea() {
        return precioArea;
    }

    public void setPrecioArea(BigDecimal precioArea) {
        this.precioArea = precioArea;
    }

    public String getNombreCortoArea() {
        return nombreCortoArea;
    }

    public void setNombreCortoArea(String nombreCortoArea) {
        this.nombreCortoArea = nombreCortoArea;
    }

    public String getDescripcionArea() {
        return descripcionArea;
    }

    public void setDescripcionArea(String descripcionArea) {
        this.descripcionArea = descripcionArea;
    }

    public Boolean getAreaStatus() {
        return areaStatus;
    }

    public void setAreaStatus(Boolean areaStatus) {
        this.areaStatus = areaStatus;
    }
}


