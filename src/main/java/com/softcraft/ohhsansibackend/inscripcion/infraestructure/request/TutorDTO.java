package com.softcraft.ohhsansibackend.inscripcion.infraestructure.request;

public class TutorDTO {
    private int idTipoTutor;
    private String emailTutor;
    private String nombresTutor;
    private String apellidosTutor;
    private int telefono;
    private int carnetIdentidadTutor;
    private Integer areaCompetencia;

    public TutorDTO(int idTipoTutor, String emailTutor, String nombresTutor, String apellidosTutor, int telefono, int carnetIdentidadTutor, Integer areaCompetencia) {
        this.idTipoTutor = idTipoTutor;
        this.emailTutor = emailTutor;
        this.nombresTutor = nombresTutor;
        this.apellidosTutor = apellidosTutor;
        this.telefono = telefono;
        this.carnetIdentidadTutor = carnetIdentidadTutor;
        this.areaCompetencia = areaCompetencia;
    }

    public TutorDTO() {
    }

    public Integer getAreaCompetencia() {
        return areaCompetencia;
    }

    public void setAreaCompetencia(Integer areaCompetencia) {
        this.areaCompetencia = areaCompetencia;
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
}
