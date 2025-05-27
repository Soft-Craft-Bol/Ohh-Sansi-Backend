package com.softcraft.ohhsansibackend.periodosolimpiada.domain.models;

import java.math.BigDecimal;

public class Olimpiada {

    private int idOlimpiada;
    private int anio;
    private String nombreOlimpiada;
    private String nombreEstado;
    private BigDecimal precioOlimpiada;

    public Olimpiada(int idOlimpiada, int anio, String nombreOlimpiada, String nombreEstado, BigDecimal precioOlimpiada) {
        this.idOlimpiada = idOlimpiada;
        this.anio = anio;
        this.nombreOlimpiada = nombreOlimpiada;
        this.nombreEstado = nombreEstado;
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

    public String getNombreEstado() {
        return nombreEstado;
    }
    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public BigDecimal getPrecioOlimpiada() {
        return precioOlimpiada;
    }

    public void setPrecioOlimpiada(BigDecimal precioOlimpiada) {
        this.precioOlimpiada = precioOlimpiada;
    }

    @Override
    public String toString() {
        return "Olimpiada{" +
                "idOlimpiada=" + idOlimpiada +
                ", anio=" + anio +
                ", nombreOlimpiada='" + nombreOlimpiada + '\'' +
                ", nombreEstado='" + nombreEstado + '\'' +
                ", precioOlimpiada=" + precioOlimpiada +
                '}';
    }
}
