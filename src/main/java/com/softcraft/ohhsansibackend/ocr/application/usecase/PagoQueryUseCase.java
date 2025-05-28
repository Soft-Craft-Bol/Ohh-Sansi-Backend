package com.softcraft.ohhsansibackend.ocr.application.usecase;

// application/usecases/PagoQueryUseCase.java
import com.softcraft.ohhsansibackend.ocr.application.ports.PagoQueryPort;
import com.softcraft.ohhsansibackend.ocr.domain.dto.EstadoPagoDto;
import com.softcraft.ohhsansibackend.ocr.domain.service.PagoService;
import org.springframework.stereotype.Service;

@Service
public class PagoQueryUseCase implements PagoQueryPort {

    private final PagoService pagoService;

    public PagoQueryUseCase(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @Override
    public EstadoPagoDto consultarEstadoPago(String codigoInscripcion) {
        return pagoService.consultarEstadoPago(codigoInscripcion);
    }
}
