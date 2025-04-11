package com.softcraft.ohhsansibackend.grado.domain.repository.implementation;

import com.softcraft.ohhsansibackend.grado.domain.models.Grade;
import com.softcraft.ohhsansibackend.grado.domain.repository.abstraction.INivelEscolarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NivelEscolarDomainRepository implements INivelEscolarRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public NivelEscolarDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Grade save(Grade grade) {
        String sql = "SELECT insertNivelEscolar(?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{grade.getNombreNivelEscolar()},
                new BeanPropertyRowMapper<>(Grade.class));
    }

    @Override
    public boolean update(Grade grade) {
        String sql = "SELECT updateNivelEscolar(?, ?)";
        Boolean rowsAffected =jdbcTemplate.queryForObject(sql, Boolean.class, grade.getIdNivel(), grade.getNombreNivelEscolar());
        return rowsAffected;
    }

    @Override
    public boolean delete(int idNivel) {
        String sql = "SELECT deleteNivelEscolar(?)";
        Boolean rowsAffected = jdbcTemplate.queryForObject(sql, Boolean.class, idNivel);
        return rowsAffected;
    }

    @Override
    public Grade findById(int idNivel) {
        String sql = "SELECT * FROM selectNivelEscolarById(?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{idNivel}, new BeanPropertyRowMapper<>(Grade.class));
    }
    @Override
    public List<Grade> findAll() {
        String sql = "SELECT * FROM selectAllNivelEscolars()";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Grade.class));
    }
}
