package com.softcraft.ohhsansibackend.estadisticas.application;

import com.softcraft.ohhsansibackend.estadisticas.domain.EstadisticasRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EstadisticasService {

    private final EstadisticasRepository estadisticasRepository;

    public EstadisticasService(EstadisticasRepository estadisticasRepository) {
        this.estadisticasRepository = estadisticasRepository;
    }

    public List<Map<String, Object>> obtenerEstadisticasOrdenesPago() {
        return estadisticasRepository.getEstadisticasOrdenesPago();
    }
}