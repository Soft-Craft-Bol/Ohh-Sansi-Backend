package com.softcraft.ohhsansibackend.ocr.infraestructure.rest;

import com.softcraft.ohhsansibackend.ocr.application.ports.PagoQueryPort;
import com.softcraft.ohhsansibackend.ocr.domain.dto.EstadoPagoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagos")
public class PagoController {

    private final PagoQueryPort pagoQueryPort;

    public PagoController(PagoQueryPort pagoQueryPort) {
        this.pagoQueryPort = pagoQueryPort;
    }

    @GetMapping("/estado/{codigoInscripcion}")
    public ResponseEntity<EstadoPagoDto> consultarEstadoPago(
            @PathVariable String codigoInscripcion) {
        EstadoPagoDto estado = pagoQueryPort.consultarEstadoPago(codigoInscripcion);
        return ResponseEntity.ok(estado);
    }
}