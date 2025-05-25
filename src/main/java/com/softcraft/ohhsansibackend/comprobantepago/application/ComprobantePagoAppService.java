package com.softcraft.ohhsansibackend.comprobantepago.application;

import com.softcraft.ohhsansibackend.comprobantepago.domain.ComprobantePagoAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ComprobantePagoAppService {
    private final ComprobantePagoAppRepository comprobantePagoRepository;
    @Autowired
    private ComprobantePagoAppService(ComprobantePagoAppRepository comprobantePagoRepository) {
        this.comprobantePagoRepository = comprobantePagoRepository;
    }

    public List<Map<String, Object>> getComprobantesPagoRechazados(){
        try {
            return comprobantePagoRepository.getComprobantesPagoRechazados();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los comprobantes de pago rechazados: " + e.getMessage());
        }
    }


}
