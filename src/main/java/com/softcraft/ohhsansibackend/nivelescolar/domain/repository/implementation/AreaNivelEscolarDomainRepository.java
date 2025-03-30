package com.softcraft.ohhsansibackend.nivelescolar.domain.repository.implementation;

import com.softcraft.ohhsansibackend.area.domain.models.AreaNivelEscolar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class AreaNivelEscolarDomainRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AreaNivelEscolarDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public AreaNivelEscolar save(AreaNivelEscolar areaNivelEscolar) {
        String sql = "INSERT INTO area_nivel_escolar (id_nivel, id_area) VALUES (?, ?) RETURNING id_area_nivel_escolar";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, areaNivelEscolar.getIdNivel());
            ps.setInt(2, areaNivelEscolar.getIdArea());
            return ps;
        }, keyHolder);

        int newId = keyHolder.getKey().intValue();
        areaNivelEscolar.setIdAreaNivelEscolar(newId);
        return areaNivelEscolar;
    }
}