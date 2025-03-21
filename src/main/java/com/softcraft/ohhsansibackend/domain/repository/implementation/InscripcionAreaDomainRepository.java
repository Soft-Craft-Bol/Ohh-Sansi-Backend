package com.softcraft.ohhsansibackend.domain.repository.implementation;

import com.softcraft.ohhsansibackend.domain.models.InscripcionArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InscripcionAreaDomainRepository implements com.softcraft.ohhsansibackend.domain.repository.abstraction.IInscripcionAreaDomainRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public InscripcionAreaDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public InscripcionArea insertInscripcionArea(int idInscripcion, int idArea) {
        String sql = "SELECT insertInscripcionArea(?, ?) AS id";
        Integer idInscripcionArea = jdbcTemplate.queryForObject(sql, Integer.class, idInscripcion, idArea);

        if (idInscripcionArea != null) {
            return new InscripcionArea(idInscripcionArea, idInscripcion, idArea);
        } else {
            throw new RuntimeException("Error al insertar InscripcionArea");
        }
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
