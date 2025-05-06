package com.softcraft.ohhsansibackend.participante.domain.dto;

public class ParticipanteResumenDTO {
    private String nombreParticipante;
    private String apellidoPaterno;
    private int carnetIdentidadParticipante;
    private String complementoCiParticipante;
    private String nombreGrado;

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

    public int getCarnetIdentidadParticipante() {
        return carnetIdentidadParticipante;
    }

    public void setCarnetIdentidadParticipante(int carnetIdentidadParticipante) {
        this.carnetIdentidadParticipante = carnetIdentidadParticipante;
    }

    public String getComplementoCiParticipante() {
        return complementoCiParticipante;
    }

    public void setComplementoCiParticipante(String complementoCiParticipante) {
        this.complementoCiParticipante = complementoCiParticipante;
    }

    public String getNombreGrado() {
        return nombreGrado;
    }

    public void setNombreGrado(String nombreGrado) {
        this.nombreGrado = nombreGrado;
    }
}