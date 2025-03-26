package com.softcraft.ohhsansibackend.plazoinscripcion.domain.models;

import java.time.LocalDate;

public class PlazoInscripcion {
    private int idPlazoInscripcion;
    private LocalDate fechaInicioInscripcion;
    private LocalDate fechaFinInscripcion;
    private LocalDate fechaResultados;
    private LocalDate fechaPremiacion;
    private Boolean fechaPlazoInscripcionActivo;

    public PlazoInscripcion() {
    }

    public PlazoInscripcion (int idPlazoInscripcion,  LocalDate fechaInicioInscripcion, LocalDate fechaFinInscripcion, LocalDate fechaResultados, LocalDate fechaPremiacion,  Boolean fechaPlazoInscripcionActivo) {
        this.idPlazoInscripcion = idPlazoInscripcion;
        this.fechaInicioInscripcion = fechaInicioInscripcion;
        this.fechaFinInscripcion = fechaFinInscripcion;
        this.fechaResultados = fechaResultados;
        this.fechaPremiacion = fechaPremiacion;
        this.fechaPlazoInscripcionActivo = fechaPlazoInscripcionActivo;
    }

    public int getIdPlazoInscripcion() {
        return idPlazoInscripcion;
    }

    public void setIdPlazoInscripcion(int idPlazoInscripcion) {
        this.idPlazoInscripcion = idPlazoInscripcion;
    }

    public LocalDate getFechaInicioInscripcion() {
        return fechaInicioInscripcion;
    }

    public void setFechaInicioInscripcion(LocalDate fechaInicioInscripcion) {
        this.fechaInicioInscripcion = fechaInicioInscripcion;
    }

    public LocalDate getFechaFinInscripcion() {
        return fechaFinInscripcion;
    }

    public void setFechaFinInscripcion(LocalDate fechaFinInscripcion) {
        this.fechaFinInscripcion = fechaFinInscripcion;
    }

    public LocalDate getFechaResultados() {
        return fechaResultados;
    }

    public void setFechaResultados(LocalDate fechaResultados) {
        this.fechaResultados = fechaResultados;
    }

    public LocalDate getFechaPremiacion() {
        return fechaPremiacion;
    }

    public void setFechaPremiacion(LocalDate fechaPremiacion) {
        this.fechaPremiacion = fechaPremiacion;
    }

    public Boolean getFechaPlazoInscripcionActivo() {
        return fechaPlazoInscripcionActivo;
    }

    public void setFechaPlazoInscripcionActivo(Boolean fechaPlazoInscripcionActivo) {
        this.fechaPlazoInscripcionActivo = fechaPlazoInscripcionActivo;
    }

}
