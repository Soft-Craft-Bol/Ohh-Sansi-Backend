package com.softcraft.ohhsansibackend.inscripcion.domain.models;

import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.sql.Time;

public class Inscripcion {
    private int idInscripcion;

    private Date fechaInscripcion;
    private Time horaInscripcion;
    private String codigoUnicoInscripcion;


    public Inscripcion() {

    }

    public Inscripcion(int idInscripcion, Date fechaInscripcion, Time horaInscripcion, String codigoUnicoInscripcion) {
        this.idInscripcion = idInscripcion;
        this.fechaInscripcion = fechaInscripcion;
        this.horaInscripcion = horaInscripcion;
        this.codigoUnicoInscripcion = codigoUnicoInscripcion;
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

    public String getCodigoUnicoInscripcion() {
        return codigoUnicoInscripcion;
    }

    public void setCodigoUnicoInscripcion(String codigoUnicoInscripcion) {
        this.codigoUnicoInscripcion = codigoUnicoInscripcion;
    }
}
