package com.softcraft.ohhsansibackend.ocr.domain.service;

import com.softcraft.ohhsansibackend.ocr.domain.repository.abstraction.EstadoPagoRepository;
import com.softcraft.ohhsansibackend.ocr.domain.dto.EstadoPagoDto;
import org.springframework.stereotype.Service;

@Service
public class PagoService {

    private final EstadoPagoRepository estadoPagoRepository;

    public PagoService(EstadoPagoRepository estadoPagoRepository) {
        this.estadoPagoRepository = estadoPagoRepository;
    }

    public EstadoPagoDto consultarEstadoPago(String codigoInscripcion) {
        return estadoPagoRepository.obtenerEstadoPago(codigoInscripcion)
                .orElseThrow(() -> new RuntimeException("No se encontró información de pago"));
    }
}