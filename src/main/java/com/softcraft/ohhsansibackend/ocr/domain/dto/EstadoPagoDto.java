package com.softcraft.ohhsansibackend.ocr.domain.dto;

import java.math.BigDecimal;

public class EstadoPagoDto {
    private Integer idInscripcion;
    private Integer idParticipante;
    private String nombreParticipante;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String carnetIdentidadParticipante;
    private BigDecimal montoTotalPago;
    private String responsablePago;
    private String estadoPago;
    private String mensaje;

    public EstadoPagoDto() {
    }
    public EstadoPagoDto(Integer idInscripcion, Integer idParticipante, String nombreParticipante, String apellidoMaterno, String apellidoPaterno, BigDecimal montoTotalPago, String carnetIdentidadParticipante, String responsablePago, String mensaje, String estadoPago) {
        this.idInscripcion = idInscripcion;
        this.idParticipante = idParticipante;
        this.nombreParticipante = nombreParticipante;
        this.apellidoMaterno = apellidoMaterno;
        this.apellidoPaterno = apellidoPaterno;
        this.montoTotalPago = montoTotalPago;
        this.carnetIdentidadParticipante = carnetIdentidadParticipante;
        this.responsablePago = responsablePago;
        this.mensaje = mensaje;
        this.estadoPago = estadoPago;
    }

    public EstadoPagoDto(int idInscripcion, int idParticipante, String nombreParticipante, String apellidoPaterno, String apellidoMaterno, String carnetIdentidadParticipante, BigDecimal montoTotalPago, String responsablePago, String estadoPago, String mensaje) {
        this.idInscripcion = idInscripcion;
        this.idParticipante = idParticipante;
        this.nombreParticipante = nombreParticipante;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.carnetIdentidadParticipante = carnetIdentidadParticipante;
        this.montoTotalPago = montoTotalPago;
        this.responsablePago = responsablePago;
        this.estadoPago = estadoPago;
        this.mensaje = mensaje;
    }

    public Integer getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(Integer idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public Integer getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(Integer idParticipante) {
        this.idParticipante = idParticipante;
    }

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

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public BigDecimal getMontoTotalPago() {
        return montoTotalPago;
    }

    public void setMontoTotalPago(BigDecimal montoTotalPago) {
        this.montoTotalPago = montoTotalPago;
    }

    public String getCarnetIdentidadParticipante() {
        return carnetIdentidadParticipante;
    }

    public void setCarnetIdentidadParticipante(String carnetIdentidadParticipante) {
        this.carnetIdentidadParticipante = carnetIdentidadParticipante;
    }

    public String getResponsablePago() {
        return responsablePago;
    }

    public void setResponsablePago(String responsablePago) {
        this.responsablePago = responsablePago;
    }

    public String getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}