package com.softcraft.ohhsansibackend.ocr.domain.service;

import com.softcraft.ohhsansibackend.ocr.domain.repository.abstraction.ComprobantePagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComprobantePagoService {

    private final ComprobantePagoRepository comprobantePagoRepository;

    @Autowired
    public ComprobantePagoService(ComprobantePagoRepository comprobantePagoRepository) {
        this.comprobantePagoRepository = comprobantePagoRepository;
    }

    public String registrarComprobante(
            Long carnetIdentidad,
            Double montoPagado,
            String fechaPago,
            String codTransaccion,
            String imagenComprobante,
            String nombreReceptor,
            String estadoOrden,
            String notasAdicionales) {

        return comprobantePagoRepository.registrarComprobantePago(
                carnetIdentidad,
                montoPagado,
                fechaPago,
                codTransaccion,
                imagenComprobante,
                nombreReceptor,
                estadoOrden,
                notasAdicionales
        );
    }
}
