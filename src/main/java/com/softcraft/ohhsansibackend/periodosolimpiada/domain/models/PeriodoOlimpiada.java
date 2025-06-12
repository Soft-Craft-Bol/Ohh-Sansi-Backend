package com.softcraft.ohhsansibackend.periodosolimpiada.domain.models;


import java.time.LocalDate;

public class PeriodoOlimpiada {

    private int idPeriodo;
    private int idOlimpiada;
    private String nombrePeriodo;
    private int idEstado;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String tipoPeriodo;


    public PeriodoOlimpiada() {
    }

    public PeriodoOlimpiada(int idPeriodo, int idOlimpiada, String nombrePeriodo, int idEstado, LocalDate fechaInicio, LocalDate fechaFin, String tipoPeriodo) {
        this.idPeriodo = idPeriodo;
        this.idOlimpiada = idOlimpiada;
        this.nombrePeriodo = nombrePeriodo;
        this.idEstado = idEstado;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.tipoPeriodo = tipoPeriodo;
    }

    public int getIdPeriodo() {
        return idPeriodo;
    }
    public void setIdPeriodo(int idPeriodo) {
        this.idPeriodo = idPeriodo;
    }
    public int getIdOlimpiada() {
        return idOlimpiada;
    }
    public void setIdOlimpiada(int idOlimpiada) {
        this.idOlimpiada = idOlimpiada;
    }
    public String getNombrePeriodo() {
        return nombrePeriodo;
    }
    public void setNombrePeriodo(String nombrePeriodo) {
        this.nombrePeriodo = nombrePeriodo;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public LocalDate getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
    public String getTipoPeriodo() {
        return tipoPeriodo;
    }
    public void setTipoPeriodo(String tipoPeriodo) {
        this.tipoPeriodo = tipoPeriodo;
    }

    @Override
    public String toString() {
        return "PeriodoOlimpiada{" +
                "idPeriodo=" + idPeriodo +
                ", idOlimpiada=" + idOlimpiada +
                ", nombrePeriodo='" + nombrePeriodo + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", tipoPeriodo='" + tipoPeriodo + '\'' +
                '}';
    }
}