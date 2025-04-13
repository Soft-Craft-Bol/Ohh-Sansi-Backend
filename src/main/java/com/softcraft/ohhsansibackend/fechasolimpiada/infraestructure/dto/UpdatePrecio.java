package com.softcraft.ohhsansibackend.fechasolimpiada.infraestructure.dto;

import java.math.BigDecimal;

public class UpdatePrecio {
    private int idOlimpiada;
    private BigDecimal precioOlimpiada;

    public UpdatePrecio(int idOlimpiada, BigDecimal precioOlimpiada) {
        this.idOlimpiada = idOlimpiada;
        this.precioOlimpiada = precioOlimpiada;
    }

    public UpdatePrecio() {
    }

    public int getIdOlimpiada() {
        return idOlimpiada;
    }

    public void setIdOlimpiada(int idOlimpiada) {
        this.idOlimpiada = idOlimpiada;
    }

    public BigDecimal getPrecioOlimpiada() {
        return precioOlimpiada;
    }

    public void setPrecioOlimpiada(BigDecimal precioOlimpiada) {
        this.precioOlimpiada = precioOlimpiada;
    }
}
