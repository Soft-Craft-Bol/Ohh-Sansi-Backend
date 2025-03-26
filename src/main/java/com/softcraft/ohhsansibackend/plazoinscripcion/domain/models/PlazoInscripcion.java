package com.softcraft.ohhsansibackend.plazoinscripcion.domain.models;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class PlazoInscripcion {
    private int idPlazoInscripcion;

    @FutureOrPresent(message = "La fecha de inicio de inscripción debe ser hoy o en el futuro")
    @NotNull(message="La fecha de inicio de inscripción es obligatoria")
    private LocalDate fechaInicioInscripcion;

    @NotNull(message = "La fecha de fin de inscripción es obligatoria")
    @FutureOrPresent(message = "La fecha de fin de inscripción debe ser hoy o en el futuro")
    private LocalDate fechaFinInscripcion;

    private LocalDate fechaResultados;
    private LocalDate fechaPremiacion;
    private Boolean fechaPlazoInscripcionActivo;

    @AssertTrue(message = "La fecha de inicio no puede ser posterior a la fecha de cierre")
    public boolean isFechaInicioAntesDeFechaFin() {
        return fechaInicioInscripcion != null && fechaFinInscripcion != null && !fechaInicioInscripcion.isAfter(fechaFinInscripcion);
    }

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
