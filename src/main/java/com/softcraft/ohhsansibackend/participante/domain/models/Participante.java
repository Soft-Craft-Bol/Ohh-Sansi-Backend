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
    private String participanteHash;
    @NotNull(message = "apellidoPaterno es requerido")
    private String apellidoPaterno;
    @NotNull(message = "apellidoMaterno es requerido")
    private String apellidoMaterno;
    @NotNull(message = "nombreParticipante es requerido")
    private String nombreParticipante;
    @NotNull(message = "fechaNacimiento es requerido")
    private Date fechaNacimiento;
    @NotNull(message = "correoElectronicoParticipante es requerido")
    @Email(message = "correoElectronicoParticipante debe ser un email")
    private String correoElectronicoParticipante;
    @NotNull(message = "carnetIdentidadParticipante es requerido")
    private Integer carnetIdentidadParticipante;
    @NotNull(message = "el idNivel es requerido")
    private int idNivel;

    public int getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(int idNivel) {
        this.idNivel = idNivel;
    }

    public void setCarnetIdentidadParticipante(Integer carnetIdentidadParticipante) {
        this.carnetIdentidadParticipante = carnetIdentidadParticipante;
    }

    public Participante() {

    }

    public Participante(int idInscripcion, int idParticipante, int idDepartamento, int idMunicipio, int idColegio, String participanteHash, String apellidoPaterno, String apellidoMaterno, String nombreParticipante, Date fechaNacimiento, String correoElectronicoParticipante, int carnetIdentidadParticipante) {
        this.idInscripcion = idInscripcion;
        this.idParticipante = idParticipante;
        this.idDepartamento = idDepartamento;
        this.idMunicipio = idMunicipio;
        this.idColegio = idColegio;

        this.participanteHash = participanteHash;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.nombreParticipante = nombreParticipante;
        this.fechaNacimiento = fechaNacimiento;
        this.correoElectronicoParticipante = correoElectronicoParticipante;
        this.carnetIdentidadParticipante = carnetIdentidadParticipante;
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

    public String getCorreoElectronicoParticipante() {
        return correoElectronicoParticipante;
    }

    public void setCorreoElectronicoParticipante(String correoElectronicoParticipante) {
        this.correoElectronicoParticipante = correoElectronicoParticipante;
    }

    public int getCarnetIdentidadParticipante() {
        return carnetIdentidadParticipante;
    }

    public void setCarnetIdentidadParticipante(int carnetIdentidadParticipante) {
        this.carnetIdentidadParticipante = carnetIdentidadParticipante;
    }
}