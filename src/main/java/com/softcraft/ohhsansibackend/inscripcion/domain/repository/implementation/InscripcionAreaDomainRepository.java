package com.softcraft.ohhsansibackend.inscripcion.domain.repository.implementation;

import com.softcraft.ohhsansibackend.inscripcion.domain.models.InscripcionArea;
import com.softcraft.ohhsansibackend.inscripcion.domain.repository.abstraction.IInscripcionAreaDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

@Repository
public class InscripcionAreaDomainRepository implements IInscripcionAreaDomainRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public InscripcionAreaDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public InscripcionArea insertInscripcionArea(int idInscripcion, int idArea) {
        String sql = "INSERT INTO inscripcion_area (id_inscripcion, id_area) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idInscripcion);
            ps.setInt(2, idArea);
            return ps;
        }, keyHolder);
        Map<String, Object> keys = keyHolder.getKeys();
        Integer idInscripcionArea = (Integer) keys.get("id_inscripcion_area");

        return new InscripcionArea(idInscripcionArea, idInscripcion, idArea);
    }

    @Override
    public List<InscripcionArea> findAllInscripcionAreas() {
        String sql = "SELECT * FROM selectAllInscripcionAreas()";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(InscripcionArea.class));
    }

    @Override
    public List<InscripcionArea> findByInscripcionId(int idInscripcion) {
        String sql = "SELECT * FROM selectInscripcionAreaByInscripcionId(?)";
        return jdbcTemplate.query(sql, new Object[]{idInscripcion}, new BeanPropertyRowMapper<>(InscripcionArea.class));
    }

    @Override
    public List<InscripcionArea> findByAreaId(int idArea) {
        String sql = "SELECT * FROM selectInscripcionAreaByAreaId(?)";
        return jdbcTemplate.query(sql, new Object[]{idArea}, new BeanPropertyRowMapper<>(InscripcionArea.class));
    }
}
