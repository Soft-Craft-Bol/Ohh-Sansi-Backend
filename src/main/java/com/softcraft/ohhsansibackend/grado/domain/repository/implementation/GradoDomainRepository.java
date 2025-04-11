package com.softcraft.ohhsansibackend.grado.domain.repository.implementation;

import com.softcraft.ohhsansibackend.grado.domain.models.Grade;
import com.softcraft.ohhsansibackend.grado.domain.repository.abstraction.IGradoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GradoDomainRepository implements IGradoRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GradoDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Grade save(Grade grade) {
        String sql = "SELECT insertGrade(?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{grade.getNombreGrado()},
                new BeanPropertyRowMapper<>(Grade.class));
    }

    @Override
    public boolean update(Grade grade) {
        String sql = "SELECT updateGrado(?, ?)";
        Boolean rowsAffected =jdbcTemplate.queryForObject(sql, Boolean.class, grade.getIdGrado(), grade.getNombreGrado());
        return rowsAffected;
    }

    @Override
    public boolean delete(int idGrado) {
        String sql = "SELECT deleteGrado(?)";
        Boolean rowsAffected = jdbcTemplate.queryForObject(sql, Boolean.class, idGrado);
        return rowsAffected;
    }

    @Override
    public Grade findById(int idGrado) {
        String sql = "SELECT * FROM selectGradoById(?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{idGrado}, new BeanPropertyRowMapper<>(Grade.class));
    }
    @Override
    public List<Grade> findAll() {
        String sql = "SELECT * FROM selectAllGrades()";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Grade.class));
    }
}
