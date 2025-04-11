package com.softcraft.ohhsansibackend.participante.domain.models;


import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;

import java.util.Date;

public class Participante {
    @NotNull(message = "idInscripcion es requerido")
    private int idInscripcion;
    private int idParticipante;
    @NotNull(message = "idDepartamento es requerido")
    private int idDepartamento;
    @NotNull(message = "idMunicipio es requerido")
    private int idMunicipio;
    @NotNull(message = "idColegio es requerido")
    private int idColegio;
    @NotNull(message = "el idGrado es requerido")
    private int idGrado;
    private String participanteHash;
    @NotNull(message = "nombreParticipante es requerido")
    private String nombreParticipante;
    @NotNull(message = "apellidoPaterno es requerido")
    private String apellidoPaterno;
    @NotNull(message = "apellidoMaterno es requerido")
    private String apellidoMaterno;
    @NotNull(message = "fechaNacimiento es requerido")
    private Date fechaNacimiento;
    @NotNull(message = "carnetIdentidadParticipante es requerido")
    private Integer carnetIdentidadParticipante;
    private String complementoCiParticipante;
    @NotNull(message = "correoElectronicoParticipante es requerido")
    @Email(message = "correoElectronicoParticipante debe ser un email")
    private String emailParticipante;
    private boolean tutorRequerido;



    public Participante() {

    }

    public Participante(int idInscripcion, int idParticipante, int idDepartamento, int idMunicipio, int idColegio, int idGrado, String participanteHash, String nombreParticipante, String apellidoPaterno, String apellidoMaterno, Date fechaNacimiento, Integer carnetIdentidadParticipante, String complementoCiParticipante, String emailParticipante, boolean tutorRequerido) {
        this.idInscripcion = idInscripcion;
        this.idParticipante = idParticipante;
        this.idDepartamento = idDepartamento;
        this.idMunicipio = idMunicipio;
        this.idColegio = idColegio;
        this.idGrado = idGrado;
        this.participanteHash = participanteHash;
        this.nombreParticipante = nombreParticipante;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.carnetIdentidadParticipante = carnetIdentidadParticipante;
        this.complementoCiParticipante = complementoCiParticipante;
        this.emailParticipante = emailParticipante;
        this.tutorRequerido = tutorRequerido;
    }

    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public int getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(int idParticipante) {
        this.idParticipante = idParticipante;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public int getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(int idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public int getIdColegio() {
        return idColegio;
    }

    public void setIdColegio(int idColegio) {
        this.idColegio = idColegio;
    }



    public String getParticipanteHash() {
        return participanteHash;
    }

    public void setParticipanteHash(String participanteHash) {
        this.participanteHash = participanteHash;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getNombreParticipante() {
        return nombreParticipante;
    }

    public void setNombreParticipante(String nombreParticipante) {
        this.nombreParticipante = nombreParticipante;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getCarnetIdentidadParticipante() {
        return carnetIdentidadParticipante;
    }

    public void setCarnetIdentidadParticipante(int carnetIdentidadParticipante) {
        this.carnetIdentidadParticipante = carnetIdentidadParticipante;
    }
    public int getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(int idGrado) {
        this.idGrado = idGrado;
    }

    public void setCarnetIdentidadParticipante(Integer carnetIdentidadParticipante) {
        this.carnetIdentidadParticipante = carnetIdentidadParticipante;
    }

    public String getComplementoCiParticipante() {
        return complementoCiParticipante;
    }

    public void setComplementoCiParticipante(String complementoCiParticipante) {
        this.complementoCiParticipante = complementoCiParticipante;
    }

    public String getEmailParticipante() {
        return emailParticipante;
    }

    public void setEmailParticipante(String emailParticipante) {
        this.emailParticipante = emailParticipante;
    }

    public boolean isTutorRequerido() {
        return tutorRequerido;
    }

    public void setTutorRequerido(boolean tutorRequerido) {
        this.tutorRequerido = tutorRequerido;
    }
}