package com.softcraft.ohhsansibackend.estadisticas.infraestructure;

import com.softcraft.ohhsansibackend.estadisticas.application.EstadisticasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/estadisticas")
public class EstadisticasController {

    private final EstadisticasService estadisticasService;

    public EstadisticasController(EstadisticasService estadisticasService) {
        this.estadisticasService = estadisticasService;
    }

    @GetMapping("/ordenes-pago")
    public ResponseEntity<List<Map<String, Object>>> getEstadisticasOrdenesPago() {
        List<Map<String, Object>> estadisticas = estadisticasService.obtenerEstadisticasOrdenesPago();
        return ResponseEntity.ok(estadisticas);
    }
}

