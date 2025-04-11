package com.softcraft.ohhsansibackend.fechasolimpiada.domain.models;

import java.math.BigDecimal;

public class Olimpiada {

    private int idOlimpiada;
    private String nombreOlimpiada;
    private Boolean estadoOlimpiada;
    private BigDecimal precioOlimpiada;

    public Olimpiada(int idOlimpiada, String nombreOlimpiada, Boolean estadoOlimpiada, BigDecimal precioOlimpiada) {
        this.idOlimpiada = idOlimpiada;
        this.nombreOlimpiada = nombreOlimpiada;
        this.estadoOlimpiada = estadoOlimpiada;
        this.precioOlimpiada = precioOlimpiada;
    }

    public Olimpiada() {
    }

    public int getIdOlimpiada() {
        return idOlimpiada;
    }

    public void setIdOlimpiada(int idOlimpiada) {
        this.idOlimpiada = idOlimpiada;
    }

    public String getNombreOlimpiada() {
        return nombreOlimpiada;
    }

    public void setNombreOlimpiada(String nombreOlimpiada) {
        this.nombreOlimpiada = nombreOlimpiada;
    }

    public Boolean getEstadoOlimpiada() {
        return estadoOlimpiada;
    }

    public void setEstadoOlimpiada(Boolean estadoOlimpiada) {
        this.estadoOlimpiada = estadoOlimpiada;
    }

    public BigDecimal getPrecioOlimpiada() {
        return precioOlimpiada;
    }

    public void setPrecioOlimpiada(BigDecimal precioOlimpiada) {
        this.precioOlimpiada = precioOlimpiada;
    }
}
