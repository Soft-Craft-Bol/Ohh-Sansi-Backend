package com.softcraft.ohhsansibackend.ordenPago.domain.models;

public class EstadoOrdenDePago {
    private int idEstado;
    private String estado;
    public EstadoOrdenDePago() {
    }

    public EstadoOrdenDePago(int idEstado, String estado) {
        this.idEstado = idEstado;
        this.estado = estado;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
