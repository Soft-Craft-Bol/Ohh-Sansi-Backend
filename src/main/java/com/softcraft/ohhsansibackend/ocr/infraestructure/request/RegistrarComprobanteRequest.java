package com.softcraft.ohhsansibackend.ocr.infraestructure.request;

public class RegistrarComprobanteRequest {

    private Long carnetIdentidad;
    private Double montoPagado;
    private String fechaPago;
    private String codTransaccion;
    private String imagenComprobante;
    private String nombreReceptor;
    private String estadoOrden;
    private String notasAdicionales;

    // Getters y setters
    public Long getCarnetIdentidad() {
        return carnetIdentidad;
    }

    public void setCarnetIdentidad(Long carnetIdentidad) {
        this.carnetIdentidad = carnetIdentidad;
    }

    public Double getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(Double montoPagado) {
        this.montoPagado = montoPagado;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getCodTransaccion() {
        return codTransaccion;
    }

    public void setCodTransaccion(String codTransaccion) {
        this.codTransaccion = codTransaccion;
    }

    public String getImagenComprobante() {
        return imagenComprobante;
    }

    public void setImagenComprobante(String imagenComprobante) {
        this.imagenComprobante = imagenComprobante;
    }

    public String getNombreReceptor() {
        return nombreReceptor;
    }

    public void setNombreReceptor(String nombreReceptor) {
        this.nombreReceptor = nombreReceptor;
    }

    public String getEstadoOrden() {
        return estadoOrden;
    }

    public void setEstadoOrden(String estadoOrden) {
        this.estadoOrden = estadoOrden;
    }

    public String getNotasAdicionales() {
        return notasAdicionales;
    }

    public void setNotasAdicionales(String notasAdicionales) {
        this.notasAdicionales = notasAdicionales;
    }
}

