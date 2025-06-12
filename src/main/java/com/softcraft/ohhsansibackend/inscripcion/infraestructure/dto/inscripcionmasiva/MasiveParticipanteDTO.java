package com.softcraft.ohhsansibackend.inscripcion.infraestructure.dto.inscripcionmasiva;

public class MasiveParticipanteDTO {
    private int idDepartamento;
    private int idMunicipio;
    private int idColegio;
    private int idGrado;
    private String participanteHash;
    private String nombreParticipante;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private int fechaNacimiento;
    private int carnetIdentidadParticipante;
    private String complemento;
    private String emailParticipante;
    private boolean tutorRequerido;
    public MasiveParticipanteDTO() {
    }

    public MasiveParticipanteDTO(int idDepartamento, int idMunicipio, int idColegio, int idGrado, String participanteHash, String nombreParticipante, String apellidoPaterno, String apellidoMaterno, int fechaNacimiento, int carnetIdentidadParticipante, String complemento, String emailParticipante, boolean tutorRequerido) {
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
        this.complemento = complemento;
        this.emailParticipante = emailParticipante;
        this.tutorRequerido = tutorRequerido;
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

    public int getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(int idGrado) {
        this.idGrado = idGrado;
    }

    public String getParticipanteHash() {
        return participanteHash;
    }

    public void setParticipanteHash(String participanteHash) {
        this.participanteHash = participanteHash;
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

    public int getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(int fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getCarnetIdentidadParticipante() {
        return carnetIdentidadParticipante;
    }

    public void setCarnetIdentidadParticipante(int carnetIdentidadParticipante) {
        this.carnetIdentidadParticipante = carnetIdentidadParticipante;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
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


