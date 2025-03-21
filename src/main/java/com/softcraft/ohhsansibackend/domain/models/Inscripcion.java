package com.softcraft.ohhsansibackend.domain.models;

import java.sql.Date;
import java.sql.Time;

public class Inscripcion {
    private int idInscripcion;
    private Date fechaInscripcion;
    private Time horaInscripcion;

    public Inscripcion() {

    }

    public Inscripcion(int idInscripcion, Date fechaInscripcion, Time horaInscripcion) {
        this.idInscripcion = idInscripcion;
        this.fechaInscripcion = fechaInscripcion;
        this.horaInscripcion = horaInscripcion;
    }

    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public Time getHoraInscripcion() {
        return horaInscripcion;
    }

    public void setHoraInscripcion(Time horaInscripcion) {
        this.horaInscripcion = horaInscripcion;
    }
}
