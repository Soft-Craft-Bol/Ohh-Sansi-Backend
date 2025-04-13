package com.softcraft.ohhsansibackend.fechasolimpiada.infraestructure.dto;

import java.time.LocalDate;
import java.util.Date;

public class EventoDTO {
    private int idFechaOlimpiada;
    private String nombreEvento;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Boolean esPublica;

    public EventoDTO(int idFechaOlimpiada, String nombreEvento, LocalDate fechaInicio, LocalDate fechaFin, Boolean esPublica) {
        this.idFechaOlimpiada = idFechaOlimpiada;
        this.nombreEvento = nombreEvento;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.esPublica = esPublica;
    }

    public EventoDTO() {
    }

    public int getIdFechaOlimpiada() {
        return idFechaOlimpiada;
    }

    public void setIdFechaOlimpiada(int idFechaOlimpiada) {
        this.idFechaOlimpiada = idFechaOlimpiada;
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
