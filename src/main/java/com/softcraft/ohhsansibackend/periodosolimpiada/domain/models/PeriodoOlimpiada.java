package com.softcraft.ohhsansibackend.periodosolimpiada.domain.models;

import java.time.LocalDate;

public class PeriodoOlimpiada {

    private int idPeriodo;
    private int idOlimpiada;
    private String nombrePeriodo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int idEstado;
    private String tipoPeriodo;
    private Boolean obligatorio;
    private int orden;

    public PeriodoOlimpiada() {
    }

    public PeriodoOlimpiada(int idPeriodo, int idOlimpiada, String nombrePeriodo, LocalDate fechaInicio, LocalDate fechaFin, int idEstado, String tipoPeriodo, Boolean obligatorio, int orden) {
        this.idPeriodo = idPeriodo;
        this.idOlimpiada = idOlimpiada;
        this.nombrePeriodo = nombrePeriodo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.idEstado = idEstado;
        this.tipoPeriodo = tipoPeriodo;
        this.obligatorio = obligatorio;
        this.orden = orden;
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
    public int getIdEstado() {
        return idEstado;
    }
    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }
    public String getTipoPeriodo() {
        return tipoPeriodo;
    }
    public void setTipoPeriodo(String tipoPeriodo) {
        this.tipoPeriodo = tipoPeriodo;
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

}