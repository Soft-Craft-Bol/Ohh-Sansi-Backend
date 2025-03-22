package com.softcraft.ohhsansibackend.area.domain.models;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.UniqueElements;

import java.math.BigDecimal;

public class Area {
    private int idArea;

    @UniqueElements(message = "El nombre del área ya existe")
    @NotBlank(message = "El nombre del área no puede estar vacío")
    @Size(max = 100, message = "El nombre del área no puede tener más de 100 caracteres")
    private String nombreArea;

    @NotNull(message = "El precio del área no puede ser nulo.")
    @PositiveOrZero(message = "El precio del área debe ser un numero positivo o cero.")
    private BigDecimal precioArea;

    @NotBlank(message = "El nombre corto del área no puede estar vacío")
    @Size(max = 10, message = "El nombre corto del área no puede tener más de 10 caracteres")
    private String nombreCortoArea;

    public Area() {

    }

    public Area(int idArea, String nombreArea, BigDecimal precioArea, String nombreCortoArea) {
        this.idArea = idArea;
        this.nombreArea = nombreArea;
        this.precioArea = precioArea;
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
}


