package com.softcraft.ohhsansibackend.periodosolimpiada.infraestructure.dto;

import java.time.LocalDate;

public class EventoDTO {
    private int idPeriodo;
    private String nombrePeriodo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String tipoPeriodo;
    private String estadoPeriodo;
    private String estadoActual;
    private Boolean obligatorio;
    private int orden;

    public EventoDTO(int idPeriodo, String nombrePeriodo, LocalDate fechaInicio, LocalDate fechaFin, String tipoPeriodo, String estadoPeriodo, String estadoActual, Boolean obligatorio, int orden) {
        this.idPeriodo = idPeriodo;
        this.nombrePeriodo = nombrePeriodo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.tipoPeriodo = tipoPeriodo;
        this.estadoPeriodo = estadoPeriodo;
        this.estadoActual = estadoActual;
        this.obligatorio = obligatorio;
        this.orden = orden;
    }

    public EventoDTO() {
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

    public String getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(String estadoActual) {
        this.estadoActual = estadoActual;
    }

    public Boolean getObligatorio() {
        return obligatorio;
    }

    public void setObligatorio(Boolean obligatorio) {
        this.obligatorio = obligatorio;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getEstadoPeriodo() {
        return estadoPeriodo;
    }
    public void setEstadoPeriodo(String estadoPeriodo) {
        this.estadoPeriodo = estadoPeriodo;
    }
}
