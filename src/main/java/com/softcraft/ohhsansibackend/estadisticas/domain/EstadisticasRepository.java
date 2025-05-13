package com.softcraft.ohhsansibackend.estadisticas.domain;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class EstadisticasRepository {

    private final JdbcTemplate jdbcTemplate;

    public EstadisticasRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> getEstadisticasOrdenesPago() {
        String sql = "SELECT * FROM get_estadisticas_ordenes_pago()";
        return jdbcTemplate.queryForList(sql);
    }
}
