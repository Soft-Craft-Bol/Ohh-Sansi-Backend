package com.softcraft.ohhsansibackend.ordenPago.domain.models;

import java.math.BigDecimal;
import java.sql.Date;

public class OrdenDePago {
    private int idInscripcion;
    private int idOrdenPago;
    private int idMetodoPago;
    private int idEstado;
    private Date fechaEmisionOrdenPago;
    private Date fechaVencimiento;
    private BigDecimal montoTotalPago;
    private String codOrdenPago;
    private String emisor;
    private String precioLiteral;
    private String responsablePago;
    private int cantidad;
    private String concepto;
    private BigDecimal precio_unitario;

    public String getPrecioLiteral() {
        return precioLiteral;
    }

    public void setPrecioLiteral(String precioLiteral) {
        this.precioLiteral = precioLiteral;
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

    public int getIdMetodoPago() {
        return idMetodoPago;
    }

    public void setIdMetodoPago(int idMetodoPago) {
        this.idMetodoPago = idMetodoPago;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public Date getFechaEmisionOrdenPago() {
        return fechaEmisionOrdenPago;
    }

    public void setFechaEmisionOrdenPago(Date fechaEmisionOrdenPago) {
        this.fechaEmisionOrdenPago = fechaEmisionOrdenPago;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public BigDecimal getMontoTotalPago() {
        return montoTotalPago;
    }

    public void setMontoTotalPago(BigDecimal montoTotalPago) {
        this.montoTotalPago = montoTotalPago;
    }

    public String getCodOrdenPago() {
        return codOrdenPago;
    }

    public void setCodOrdenPago(String codOrdenPago) {
        this.codOrdenPago = codOrdenPago;
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public String getResponsablePago() {
        return responsablePago;
    }

    public void setResponsablePago(String responsablePago) {
        this.responsablePago = responsablePago;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public BigDecimal getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(BigDecimal precio_unitario) {
        this.precio_unitario = precio_unitario;
    }
}
