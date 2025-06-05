package com.softcraft.ohhsansibackend.inscripcion.infraestructure.dto.inscripcionmasiva;

public class MasiveProfesorDTO {
    private String emailTutor;
    private String nombresTutor;
    private String apellidosTutor;
    private int telefono;
    private int carnetIdentidadTutor;
    private String complementoCiTutor;
    private int idArea;

    public MasiveProfesorDTO() {
    }

    public MasiveProfesorDTO(String emailTutor, String nombresTutor, String apellidosTutor, int telefono, int carnetIdentidadTutor, String complementoCiTutor, int idArea) {
        this.emailTutor = emailTutor;
        this.nombresTutor = nombresTutor;
        this.apellidosTutor = apellidosTutor;
        this.telefono = telefono;
        this.carnetIdentidadTutor = carnetIdentidadTutor;
        this.complementoCiTutor = complementoCiTutor;
        this.idArea = idArea;
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

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }
}
