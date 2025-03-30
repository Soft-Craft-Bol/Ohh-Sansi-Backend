package com.softcraft.ohhsansibackend.municipio.domain.models;

public class Municipio {
    private int idDepartamento;
    private int idMunicipio;
    private String nombreMunicipio;
    private String numeroColegios;

    public Municipio() {
    }

    public Municipio(int idDepartamento, int idMunicipio, String nombreMunicipio, String numeroColegios) {
        this.idDepartamento = idDepartamento;
        this.idMunicipio = idMunicipio;
        this.nombreMunicipio = nombreMunicipio;
        this.numeroColegios = numeroColegios;
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

    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    public void setNombreMunicipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
    }

    public String getNumeroColegios() {
        return numeroColegios;
    }

    public void setNumeroColegios(String numeroColegios) {
        this.numeroColegios = numeroColegios;
    }
}
