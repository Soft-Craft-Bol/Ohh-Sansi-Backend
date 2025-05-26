package com.softcraft.ohhsansibackend.periodosolimpiada.domain.models;

import java.sql.Date;

public class PeriodoOlimpiada {

    private int idPeriodo;
    private int idOlimpiada;
    private String nombrePeriodo;
    private Date fechaInicio;
    private Date fechaFin;
    private String tipoPeriodo;


    public PeriodoOlimpiada() {
    }

    public PeriodoOlimpiada(int idPeriodo, int idOlimpiada, String nombrePeriodo, Date fechaInicio, Date fechaFin, String tipoPeriodo) {
        this.idPeriodo = idPeriodo;
        this.idOlimpiada = idOlimpiada;
        this.nombrePeriodo = nombrePeriodo;
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
    public String getTipoPeriodo() {
        return tipoPeriodo;
    }
    public void setTipoPeriodo(String tipoPeriodo) {
        this.tipoPeriodo = tipoPeriodo;
    }
}