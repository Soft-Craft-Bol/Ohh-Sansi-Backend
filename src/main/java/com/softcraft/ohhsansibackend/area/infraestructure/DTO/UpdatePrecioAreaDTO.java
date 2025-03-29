package com.softcraft.ohhsansibackend.area.infraestructure.DTO;

import java.math.BigDecimal;

public class UpdatePrecioAreaDTO {
    private int idArea;
    private BigDecimal precioArea;

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public BigDecimal getPrecioArea() {
        return precioArea;
    }

    public void setPrecioArea(BigDecimal precioArea) {
        this.precioArea = precioArea;
    }
}
