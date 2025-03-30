package com.softcraft.ohhsansibackend.departamento.domain.models;

public class Departamento {
    private int idDepartamento;
    private String nombreDepartamento;
    private String nombreCorto;

    public Departamento() {
    }

    public Departamento(int idDepartamento, String nombreDepartamento, String nombreCorto) {
        this.idDepartamento = idDepartamento;
        this.nombreDepartamento = nombreDepartamento;
        this.nombreCorto = nombreCorto;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }


}
