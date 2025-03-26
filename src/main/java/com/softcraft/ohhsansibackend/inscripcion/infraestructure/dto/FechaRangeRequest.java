package com.softcraft.ohhsansibackend.inscripcion.infraestructure.dto;

import java.time.LocalDate;

public class FechaRangeRequest {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public FechaRangeRequest() {}

    public FechaRangeRequest(LocalDate fechaInicio, LocalDate fechaFin) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
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
}
