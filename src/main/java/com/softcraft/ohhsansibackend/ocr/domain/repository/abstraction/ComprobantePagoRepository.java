package com.softcraft.ohhsansibackend.ocr.domain.repository.abstraction;

public interface ComprobantePagoRepository {
    String registrarComprobantePago(
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