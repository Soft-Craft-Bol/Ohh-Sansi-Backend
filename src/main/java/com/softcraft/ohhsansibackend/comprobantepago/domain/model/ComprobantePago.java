package com.softcraft.ohhsansibackend.comprobantepago.domain.model;

import org.apache.poi.hpsf.Decimal;

import java.math.BigDecimal;
import java.sql.Date;

public class ComprobantePago {
    private int idInscripcion;
    private int idOrdenPago;
    private int idComprobantePago;
    private BigDecimal montoPagado;
    private java.sql.Date fechaPago;
    private String codTransaccion;
    private String imagenComprobante;
    private String nombreReceptor;
    private String notasAdicionales;
    private int idEstadoComprobante;
    public ComprobantePago(){

    }

    public ComprobantePago(int idInscripcion, int idOrdenPago, int idComprobantePago, BigDecimal montoPagado, java.sql.Date fechaPago, String codTransaccion, String imagenComprobante, String nombreReceptor, String notasAdicionales, int idEstadoComprobante) {
        this.idInscripcion = idInscripcion;
        this.idOrdenPago = idOrdenPago;
        this.idComprobantePago = idComprobantePago;
        this.montoPagado = montoPagado;
        this.fechaPago = fechaPago;
        this.codTransaccion = codTransaccion;
        this.imagenComprobante = imagenComprobante;
        this.nombreReceptor = nombreReceptor;
        this.notasAdicionales = notasAdicionales;
        this.idEstadoComprobante = idEstadoComprobante;
    }

    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public int getIdOrdenPago() {
        return idOrdenPago;
    }

    public void setIdOrdenPago(int idOrdenPago) {
        this.idOrdenPago = idOrdenPago;
    }

    public int getIdComprobantePago() {
        return idComprobantePago;
    }

    public void setIdComprobantePago(int idComprobantePago) {
        this.idComprobantePago = idComprobantePago;
    }

    public BigDecimal getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(BigDecimal montoPagado) {
        this.montoPagado = montoPagado;
    }

    public java.sql.Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(java.sql.Date fechaPago) {
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

    public String getNotasAdicionales() {
        return notasAdicionales;
    }

    public void setNotasAdicionales(String notasAdicionales) {
        this.notasAdicionales = notasAdicionales;
    }

    public int getIdEstadoComprobante() {
        return idEstadoComprobante;
    }

    public void setIdEstadoComprobante(int idEstadoComprobante) {
        this.idEstadoComprobante = idEstadoComprobante;
    }
}
