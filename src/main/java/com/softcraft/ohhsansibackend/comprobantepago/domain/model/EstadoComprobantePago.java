package com.softcraft.ohhsansibackend.comprobantepago.domain.model;

public class EstadoComprobantePago {
    private int idEstadoComprobantePago;
    private String nombreEstadoComprobante;

    public int getIdEstadoComprobantePago() {
        return idEstadoComprobantePago;
    }
    public EstadoComprobantePago() {
    }

    public EstadoComprobantePago(int idEstadoComprobantePago, String nombreEstadoComprobante) {
        this.idEstadoComprobantePago = idEstadoComprobantePago;
        this.nombreEstadoComprobante = nombreEstadoComprobante;
    }

    public void setIdEstadoComprobantePago(int idEstadoComprobantePago) {
        this.idEstadoComprobantePago = idEstadoComprobantePago;
    }

    public String getNombreEstadoComprobante() {
        return nombreEstadoComprobante;
    }

    public void setNombreEstadoComprobante(String nombreEstadoComprobante) {
        this.nombreEstadoComprobante = nombreEstadoComprobante;
    }
}
