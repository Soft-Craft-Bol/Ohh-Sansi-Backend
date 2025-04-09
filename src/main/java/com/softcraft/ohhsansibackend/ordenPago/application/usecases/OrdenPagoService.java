package com.softcraft.ohhsansibackend.ordenPago.application.usecases;

import com.softcraft.ohhsansibackend.inscripcion.application.usecases.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdenPagoService {
    private final InscripcionService inscripcionService;

    @Autowired
    public OrdenPagoService(InscripcionService inscripcionService) {
        this.inscripcionService = inscripcionService;
    }

    public Long getInscriptionIdbyCodigoUnico(String uniqueCode) {
        return inscripcionService.findIdByCodigoUnico(uniqueCode);
    }
}
