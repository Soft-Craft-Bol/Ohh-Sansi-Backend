package com.softcraft.ohhsansibackend.periodosolimpiada.infraestructure.dto;

import java.sql.Date;

public class EventoDTO {
    private int idOlimpiada;
    private int idPeriodo;
    private String nombrePeriodo;
    private Date fechaInicio;
    private Date fechaFin;
    private String tipoPeriodo;
    private String estadoActual;


    public EventoDTO(int idOlimpiada, int idPeriodo, String nombrePeriodo, Date fechaInicio, Date fechaFin, String tipoPeriodo, String estadoActual) {
        this.idOlimpiada = idOlimpiada;
        this.idPeriodo = idPeriodo;
        this.nombrePeriodo = nombrePeriodo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.tipoPeriodo = tipoPeriodo;
        this.estadoActual = estadoActual;
    }
    public EventoDTO() {
    }
    public int getIdOlimpiada() {
        return idOlimpiada;
    }
    public void setIdOlimpiada(int idOlimpiada) {
        this.idOlimpiada = idOlimpiada;
    }
    public int getIdPeriodo() {
        return idPeriodo;
    }
    public void setIdPeriodo(int idPeriodo) {
        this.idPeriodo = idPeriodo;
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
    public String getEstadoActual() {
        return estadoActual;
    }
    public void setEstadoActual(String estadoActual) {
        this.estadoActual = estadoActual;
    }
}
