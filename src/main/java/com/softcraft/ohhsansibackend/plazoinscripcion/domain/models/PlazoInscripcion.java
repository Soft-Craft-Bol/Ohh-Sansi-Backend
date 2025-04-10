package com.softcraft.ohhsansibackend.plazoinscripcion.domain.models;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PlazoInscripcion {
    private int idPeriodoInscripcion;

    @NotBlank(message = "El nombre del plazo de inscripción no puede estar vacío")
    private String nombrePeriodoInscripcion;
    @FutureOrPresent(message = "La fecha de inicio de inscripción debe ser hoy o en el futuro")
    @NotNull(message="La fecha de inicio de inscripción es obligatoria")
    private LocalDate fechaInicioInscripcion;

    @NotNull(message = "La fecha de fin de inscripción es obligatoria")
    @FutureOrPresent(message = "La fecha de fin de inscripción debe ser hoy o en el futuro")
    private LocalDate fechaFinInscripcion;

    @FutureOrPresent(message = "La fecha de inicio de las olimpiadas debe ser hoy o en el futuro")
    private LocalDate fechaInicioOlimpiadas;

    @FutureOrPresent(message = "La fecha de fin de las olimpiadas debe ser hoy o en el futuro")
    private LocalDate fechaFinOlimpiadas;

    private LocalDate fechaResultados;
    private LocalDate fechaPremiacion;
    private Boolean fechaPlazoInscripcionActivo;

    private BigDecimal precioPeriodo;

    @AssertTrue(message = "La fecha de inicio no puede ser posterior a la fecha de cierre")
    public boolean isFechaInicioAntesDeFechaFin() {
        return fechaInicioInscripcion != null && fechaFinInscripcion != null && !fechaInicioInscripcion.isAfter(fechaFinInscripcion);
    }
    @AssertTrue(message = "La fecha de inicio de las olimpiadas no puede ser posterior a la fecha de fin de las olimpiadas")
    public boolean isFechaInicioOlimpiadasAntesDeFechaFinOlimpiadas() {
        return fechaInicioOlimpiadas != null && fechaFinOlimpiadas != null && !fechaInicioOlimpiadas.isAfter(fechaFinOlimpiadas);
    }

    public PlazoInscripcion() {
    }

    public PlazoInscripcion (int idPeriodoInscripcion, String nombrePeriodoInscripcion, LocalDate fechaInicioInscripcion, LocalDate fechaFinInscripcion, LocalDate fechaInicioOlimpiadas, LocalDate fechaFinOlimpiadas, LocalDate fechaResultados, LocalDate fechaPremiacion,  Boolean fechaPlazoInscripcionActivo, BigDecimal precioPeriodo) {
        this.idPeriodoInscripcion = idPeriodoInscripcion;
        this.nombrePeriodoInscripcion = nombrePeriodoInscripcion;
        this.fechaInicioInscripcion = fechaInicioInscripcion;
        this.fechaFinInscripcion = fechaFinInscripcion;
        this.fechaInicioOlimpiadas = fechaInicioOlimpiadas;
        this.fechaFinOlimpiadas = fechaFinOlimpiadas;
        this.fechaResultados = fechaResultados;
        this.fechaPremiacion = fechaPremiacion;
        this.fechaPlazoInscripcionActivo = fechaPlazoInscripcionActivo;
        this.precioPeriodo = precioPeriodo;
    }

    public int getIdPeriodoInscripcion() {
        return idPeriodoInscripcion;
    }

    public void setIdPeriodoInscripcion(int idPeriodoInscripcion) {
        this.idPeriodoInscripcion = idPeriodoInscripcion;
    }

    public String getNombrePeriodoInscripcion() {
        return nombrePeriodoInscripcion;
    }

    public void setNombrePeriodoInscripcion(String nombrePeriodoInscripcion) {
        this.nombrePeriodoInscripcion = nombrePeriodoInscripcion;
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

    public LocalDate getFechaInicioOlimpiadas() {
        return fechaInicioOlimpiadas;
    }

    public void setFechaInicioOlimpiadas(LocalDate fechaInicioOlimpiadas) {
        this.fechaInicioOlimpiadas = fechaInicioOlimpiadas;
    }

    public LocalDate getFechaFinOlimpiadas() {
        return fechaFinOlimpiadas;
    }

    public void setFechaFinOlimpiadas(LocalDate fechaFinOlimpiadas) {
        this.fechaFinOlimpiadas = fechaFinOlimpiadas;
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

    public BigDecimal getPrecioPeriodo() {
        return precioPeriodo;
    }

    public void setPrecioPeriodo(BigDecimal precioPeriodo) {
        this.precioPeriodo = precioPeriodo;
    }

}
