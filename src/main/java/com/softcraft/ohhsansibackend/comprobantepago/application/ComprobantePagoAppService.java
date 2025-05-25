package com.softcraft.ohhsansibackend.comprobantepago.application;

import com.softcraft.ohhsansibackend.comprobantepago.domain.ComprobantePagoAppRepository;
import com.softcraft.ohhsansibackend.comprobantepago.domain.model.EstadoComprobantePago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ComprobantePagoAppService {
    private final ComprobantePagoAppRepository comprobantePagoRepository;
    @Autowired
    private ComprobantePagoAppService(ComprobantePagoAppRepository comprobantePagoRepository) {
        this.comprobantePagoRepository = comprobantePagoRepository;
    }

    public List<Map<String, Object>> getComprobantesPago(){
        try {
            return comprobantePagoRepository.getComprobantesPago();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los comprobantes de pago: " + e.getMessage());
        }
    }
    public Map<String, Object> cambiarEstadoComprobantePago(int idComprobantePago, int nuevoEstadoId) {
        Map<String, Object> response = new HashMap<>();
        try {
            EstadoComprobantePago nuevoEstado = EstadoComprobantePago.fromId(nuevoEstadoId);
            comprobantePagoRepository.actualizarEstadoComprobantePago(idComprobantePago, nuevoEstado.getId());
            response.put("success", true);
            response.put("message", "Estado del comprobante de pago actualizado correctamente.");
            response.put("idComprobantePago", idComprobantePago);
            response.put("nuevoEstado", nuevoEstado.name());
        } catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("message", "Estado no válido: " + e.getMessage());
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error al cambiar el estado del comprobante de pago: " + e.getMessage());
        }
        return response;
    }


}
