package com.softcraft.ohhsansibackend.inscripcion.infraestructure.request;

import java.sql.Date;

public class ParticipanteDTO {
    private int idDepartamento;
    private int idMunicipio;
    private int idColegio;
    private int idNivelGradoEscolar;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombreParticipante;
    private Date fechaNacimiento;
    private String correoElectronicoParticipante;
    private int carnetIdentidadParticipante;

    public ParticipanteDTO(int idDepartamento, int idMunicipio, int idColegio, int idNivelGradoEscolar, String apellidoPaterno, String apellidoMaterno, String nombreParticipante, Date fechaNacimiento, String correoElectronicoParticipante, int carnetIdentidadParticipante) {
        this.idDepartamento = idDepartamento;
        this.idMunicipio = idMunicipio;
        this.idColegio = idColegio;
        this.idNivelGradoEscolar = idNivelGradoEscolar;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.nombreParticipante = nombreParticipante;
        this.fechaNacimiento = fechaNacimiento;
        this.correoElectronicoParticipante = correoElectronicoParticipante;
        this.carnetIdentidadParticipante = carnetIdentidadParticipante;
    }

    public ParticipanteDTO() {
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

    public int getIdNivelGradoEscolar() {
        return idNivelGradoEscolar;
    }

    public void setIdNivelGradoEscolar(int idNivelGradoEscolar) {
        this.idNivelGradoEscolar = idNivelGradoEscolar;
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
