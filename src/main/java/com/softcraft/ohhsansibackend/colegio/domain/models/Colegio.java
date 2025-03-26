package com.softcraft.ohhsansibackend.colegio.domain.models;

public class Colegio {
    private int idDepartamento;
    private int idMunicipio;
    private int idColegio;
    private String nombreColegio;
    private String direccion;
    private String coordenadas;
    private Integer cantidadEstudiantesColegio;

    public Colegio() {
    }

    public Colegio(int idDepartamento, int idMunicipio, int idColegio, String nombreColegio, String direccion, String coordenadas, Integer cantidadEstudiantesColegio) {
        this.idDepartamento = idDepartamento;
        this.idMunicipio = idMunicipio;
        this.idColegio = idColegio;
        this.nombreColegio = nombreColegio;
        this.direccion = direccion;
        this.coordenadas = coordenadas;
        this.cantidadEstudiantesColegio = cantidadEstudiantesColegio;
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

    public String getNombreColegio() {
        return nombreColegio;
    }

    public void setNombreColegio(String nombreColegio) {
        this.nombreColegio = nombreColegio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public Integer getCantidadEstudiantesColegio() {
        return cantidadEstudiantesColegio;
    }

    public void setCantidadEstudiantesColegio(Integer cantidadEstudiantesColegio) {
        this.cantidadEstudiantesColegio = cantidadEstudiantesColegio;
    }
}
