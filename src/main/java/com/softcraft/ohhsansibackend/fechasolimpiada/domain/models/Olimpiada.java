package com.softcraft.ohhsansibackend.fechasolimpiada.domain.models;

import java.math.BigDecimal;

public class Olimpiada {

    private int idOlimpiada;
    private int anio;
    private String nombreOlimpiada;
    private Boolean estadoOlimpiada;
    private BigDecimal precioOlimpiada;

    public Olimpiada(int idOlimpiada, int anio, String nombreOlimpiada, Boolean estadoOlimpiada, BigDecimal precioOlimpiada) {
        this.idOlimpiada = idOlimpiada;
        this.anio = anio;
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

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
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
