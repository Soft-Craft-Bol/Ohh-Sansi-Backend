package com.softcraft.ohhsansibackend.fechasolimpiada.domain.models;

import java.time.LocalDate;

public class FechaOlimpiada {

    private int idFechaOlimpiada;
    private int idOlimpiada;
    private String nombreEvento;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Boolean esPublica;

    public FechaOlimpiada() {
    }

    public FechaOlimpiada(int idFechaOlimpiada, int idOlimpiada, String nombreEvento, LocalDate fechaInicio, LocalDate fechaFin, boolean esPublica) {
        this.idFechaOlimpiada = idFechaOlimpiada;
        this.idOlimpiada = idOlimpiada;
        this.nombreEvento = nombreEvento;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.esPublica = esPublica;
    }

    public int getIdFechaOlimpiada() {
        return idFechaOlimpiada;
    }

    public void setIdFechaOlimpiada(int idFechaOlimpiada) {
        this.idFechaOlimpiada = idFechaOlimpiada;
    }

    public int getIdOlimpiada() {
        return idOlimpiada;
    }

    public void setIdOlimpiada(int idOlimpiada) {
        this.idOlimpiada = idOlimpiada;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
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

    public Boolean getEsPublica() {
        return esPublica;
    }

    public void setEsPublica(Boolean esPublica) {
        this.esPublica = esPublica;
    }
}