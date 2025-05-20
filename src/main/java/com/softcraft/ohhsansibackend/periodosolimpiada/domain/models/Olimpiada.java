package com.softcraft.ohhsansibackend.periodosolimpiada.domain.models;

import java.math.BigDecimal;
import java.util.Date;

public class Olimpiada {

    private int idOlimpiada;
    private int anio;
    private String nombreOlimpiada;
    private String nombreEstado;
    private BigDecimal precioOlimpiada;
    private Date fechaInicio;
    private Date fechaFin;

    public Olimpiada(int idOlimpiada, int anio, String nombreOlimpiada, String nombreEstado, BigDecimal precioOlimpiada, Date fechaInicio, Date fechaFin) {
        this.idOlimpiada = idOlimpiada;
        this.anio = anio;
        this.nombreOlimpiada = nombreOlimpiada;
        this.nombreEstado = nombreEstado;
        this.precioOlimpiada = precioOlimpiada;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
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

    public Date getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public Date getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
}
