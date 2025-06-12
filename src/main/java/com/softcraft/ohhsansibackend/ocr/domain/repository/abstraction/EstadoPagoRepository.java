package com.softcraft.ohhsansibackend.ocr.domain.repository.abstraction;

import com.softcraft.ohhsansibackend.ocr.domain.dto.EstadoPagoDto;
import java.util.Optional;

public interface EstadoPagoRepository {
    Optional<EstadoPagoDto> obtenerEstadoPago(String codigoUnicoInscripcion);
}