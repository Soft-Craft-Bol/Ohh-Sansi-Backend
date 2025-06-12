package com.softcraft.ohhsansibackend.ocr.application.ports;


import com.softcraft.ohhsansibackend.ocr.domain.dto.EstadoPagoDto;

public interface PagoQueryPort {
    EstadoPagoDto consultarEstadoPago(String codigoInscripcion);
}