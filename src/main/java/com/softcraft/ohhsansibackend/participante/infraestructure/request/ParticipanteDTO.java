package com.softcraft.ohhsansibackend.participante.infraestructure.request;


import java.util.Date;

public class ParticipanteDTO {
    private Integer idDepartamento;
    private Integer idMunicipio;
    private Integer idColegio;
    private Integer idGrado;
    private String nombreParticipante;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Date fechaNacimiento;
    private Integer carnetIdentidadParticipante;
    private String complementoCiParticipante;
    private String correoElectronicoParticipante;


    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public Integer getIdColegio() {
        return idColegio;
    }

    public void setIdColegio(Integer idColegio) {
        this.idColegio = idColegio;
    }

    public Integer getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(Integer idGrado) {
        this.idGrado = idGrado;
    }

    public String getNombreParticipante() {
        return nombreParticipante;
    }

    public void setNombreParticipante(String nombreParticipante) {
        this.nombreParticipante = nombreParticipante;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getCarnetIdentidadParticipante() {
        return carnetIdentidadParticipante;
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

    public String getCorreoElectronicoParticipante() {
        return correoElectronicoParticipante;
    }

    public void setCorreoElectronicoParticipante(String correoElectronicoParticipante) {
        this.correoElectronicoParticipante = correoElectronicoParticipante;
    }
}