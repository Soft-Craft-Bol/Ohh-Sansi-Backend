package com.softcraft.ohhsansibackend.comprobantepago.domain.model;

public class EstadoComprobantePago {
    private int idEstadoComprobante;
    private String nombreEstadoComprobante;

    public EstadoComprobantePago(int idEstadoComprobante, String nombreEstadoComprobante) {
        this.idEstadoComprobante = idEstadoComprobante;
        this.nombreEstadoComprobante = nombreEstadoComprobante;
    }

    public int getIdEstadoComprobante() {
        return idEstadoComprobante;
    }

    public void setIdEstadoComprobante(int idEstadoComprobante) {
        this.idEstadoComprobante = idEstadoComprobante;
    }

    public String getNombreEstadoComprobante() {
        return nombreEstadoComprobante;
    }

    public void setNombreEstadoComprobante(String nombreEstadoComprobante) {
        this.nombreEstadoComprobante = nombreEstadoComprobante;
    }
}
