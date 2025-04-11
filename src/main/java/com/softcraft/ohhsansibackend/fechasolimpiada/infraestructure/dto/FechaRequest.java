package com.softcraft.ohhsansibackend.plazoinscripcion.infraestructure.dto;

import java.time.LocalDate;

public class FechaRequest {
    private LocalDate date;

    public FechaRequest() {
    }

    public FechaRequest(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
