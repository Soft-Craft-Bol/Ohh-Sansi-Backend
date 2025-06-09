package com.softcraft.ohhsansibackend.estadoinscripcion.domain;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EstadoInscripcionDomainRepository {
    private final JdbcTemplate jdbcTemplate;
    public EstadoInscripcionDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int countParticipanteByCarnetIdentidad(int carnetIdentidad) {
        String sql = """
        SELECT COUNT(pt.id_participante)
        FROM participante p, participante_tutor pt
        WHERE p.id_participante = pt.id_participante
        AND p.carnet_identidad_participante = ?
    """;
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{carnetIdentidad}, Integer.class);
        return (count != null) ? count : 0;
    }
}
