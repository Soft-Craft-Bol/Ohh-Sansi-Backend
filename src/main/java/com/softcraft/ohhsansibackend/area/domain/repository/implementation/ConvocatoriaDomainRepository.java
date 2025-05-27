package com.softcraft.ohhsansibackend.area.domain.repository.implementation;

import com.softcraft.ohhsansibackend.area.domain.models.Convocatoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ConvocatoriaDomainRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ConvocatoriaDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Convocatoria convocatoria) {
        String sql = """
            INSERT INTO convocatoria (id_area, id_olimpiada, id_pdf_convocatoria)
            VALUES (?, ?, ?);
        """;

        jdbcTemplate.update(sql, convocatoria.getIdArea(), convocatoria.getIdOlimpiada(), convocatoria.getIdPdfConvocatoria());
    }
}