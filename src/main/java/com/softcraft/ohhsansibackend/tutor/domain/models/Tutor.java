package com.softcraft.ohhsansibackend.tutor.domain.models;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;

public class Tutor {
    private Long idTutor;
    private int idTipoTutor;
    @NotNull(message = "El email del tutor es requerido")
    @Email(message = "Incorrecto, debe ser un email")
    private String emailTutor;
    @NotNull(message = "El nombre del tutor es requerido")
    private String nombresTutor;
    @NotNull(message = "El apellido del tutor es requerido")
    private String apellidosTutor;
    @NotNull(message = "El telefono del tutor es requerido")
    private int telefono;
    @NotNull(message = "El carnet de identidad del tutor es requerido")
    private int carnetIdentidadTutor;
    private String complementoCiTutor;

    public Tutor(){}

    public Tutor(int idTipoTutor, String emailTutor, String nombresTutor, String apellidosTutor, int telefono, int carnetIdentidadTutor, String complementoCiTutor) {
        this.idTipoTutor = idTipoTutor;
        this.emailTutor = emailTutor;
        this.nombresTutor = nombresTutor;
        this.apellidosTutor = apellidosTutor;
        this.telefono = telefono;
        this.carnetIdentidadTutor = carnetIdentidadTutor;
        this.complementoCiTutor = complementoCiTutor;
    }

    public String getComplementoCiTutor() {
        return complementoCiTutor;
    }

    public void setComplementoCiTutor(String complementoCiTutor) {
        this.complementoCiTutor = complementoCiTutor;
    }

    public Long getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(Long idTutor) {
        this.idTutor = idTutor;
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
