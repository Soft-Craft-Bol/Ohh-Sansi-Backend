package com.softcraft.ohhsansibackend.inscripcion.infraestructure.dto.inscripcionmasiva;

public class MasiveTutorDTO {
    private int idTipoTutor;
    private String emailTutor;
    private String nombresTutor;
    private String apellidosTutor;
    private int telefono;
    private int carnetIdentidadTutor;
    private String complementoCiTutor;
    private int idTutorParentesco;

    public MasiveTutorDTO(){}

    public MasiveTutorDTO(int idTipoTutor, String emailTutor, String nombresTutor, String apellidosTutor, int telefono, int carnetIdentidadTutor, String complementoCiTutor, int idTutorParentesco) {
        this.idTipoTutor = idTipoTutor;
        this.emailTutor = emailTutor;
        this.nombresTutor = nombresTutor;
        this.apellidosTutor = apellidosTutor;
        this.telefono = telefono;
        this.carnetIdentidadTutor = carnetIdentidadTutor;
        this.complementoCiTutor = complementoCiTutor;
        this.idTutorParentesco = idTutorParentesco;
    }

    public int getIdTipoTutor() {
        return idTipoTutor;
    }

    public void setIdTipoTutor(int idTipoTutor) {
        this.idTipoTutor = idTipoTutor;
    }

    public String getEmailTutor() {
        return emailTutor;
    }

    public void setEmailTutor(String emailTutor) {
        this.emailTutor = emailTutor;
    }

    public String getNombresTutor() {
        return nombresTutor;
    }

    public void setNombresTutor(String nombresTutor) {
        this.nombresTutor = nombresTutor;
    }

    public String getApellidosTutor() {
        return apellidosTutor;
    }

    public void setApellidosTutor(String apellidosTutor) {
        this.apellidosTutor = apellidosTutor;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getCarnetIdentidadTutor() {
        return carnetIdentidadTutor;
    }

    public void setCarnetIdentidadTutor(int carnetIdentidadTutor) {
        this.carnetIdentidadTutor = carnetIdentidadTutor;
    }

    public String getComplementoCiTutor() {
        return complementoCiTutor;
    }

    public void setComplementoCiTutor(String complementoCiTutor) {
        this.complementoCiTutor = complementoCiTutor;
    }

    public int getIdTutorParentesco() {
        return idTutorParentesco;
    }

    public void setIdTutorParentesco(int idTutorParentesco) {
        this.idTutorParentesco = idTutorParentesco;
    }
}
