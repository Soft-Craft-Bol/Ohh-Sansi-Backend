package com.softcraft.ohhsansibackend.ocr.application.usecase;


import com.softcraft.ohhsansibackend.ocr.application.ports.ComprobantePagoPort;
import com.softcraft.ohhsansibackend.ocr.domain.service.ComprobantePagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistrarComprobanteUseCase implements ComprobantePagoPort {

    private final ComprobantePagoService comprobantePagoService;

    @Autowired
    public RegistrarComprobanteUseCase(ComprobantePagoService comprobantePagoService) {
        this.comprobantePagoService = comprobantePagoService;
    }

    @Override
    public String registrarComprobante(
            Long carnetIdentidad,
            Double montoPagado,
            String fechaPago,
            String codTransaccion,
            String imagenComprobante,
            String nombreReceptor,
            String estadoOrden,
            String notasAdicionales) {

        return comprobantePagoService.registrarComprobante(
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
