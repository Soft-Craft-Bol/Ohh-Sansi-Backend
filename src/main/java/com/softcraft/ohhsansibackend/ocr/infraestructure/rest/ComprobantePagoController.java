package com.softcraft.ohhsansibackend.ocr.infraestructure.rest;

import com.softcraft.ohhsansibackend.ocr.application.usecase.RegistrarComprobanteUseCase;
import com.softcraft.ohhsansibackend.ocr.infraestructure.request.RegistrarComprobanteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comprobante-pago")
public class ComprobantePagoController {

    private final RegistrarComprobanteUseCase registrarComprobanteUseCase;

    @Autowired
    public ComprobantePagoController(RegistrarComprobanteUseCase registrarComprobanteUseCase) {
        this.registrarComprobanteUseCase = registrarComprobanteUseCase;
    }

    @PostMapping
    public ResponseEntity<String> registrarComprobante(@RequestBody RegistrarComprobanteRequest request) {
        // Llamamos al caso de uso
        String resultado = registrarComprobanteUseCase.registrarComprobante(
                request.getCarnetIdentidad(),
                request.getMontoPagado(),
                request.getFechaPago(),
                request.getCodTransaccion(),
                request.getImagenComprobante(),
                request.getNombreReceptor(),
                request.getEstadoOrden(),
                request.getNotasAdicionales()
        );

        // Retornamos el resultado
        if (resultado.startsWith("ERROR")) {
            return ResponseEntity.badRequest().body(resultado);
        } else {
            return ResponseEntity.ok(resultado);
        }
    }
}
