package com.softcraft.ohhsansibackend.ocr.application.ports;

public interface ComprobantePagoPort {
    String registrarComprobante(
            Long carnetIdentidad,
            Double montoPagado,
            String fechaPago,
            String codTransaccion,
            String imagenComprobante,
            String nombreReceptor,
            String estadoOrden,
            String notasAdicionales
    );
}
