package com.softcraft.ohhsansibackend.departamento.domain.repository.implementation;

import com.softcraft.ohhsansibackend.departamento.domain.models.Departamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartamentoDomainRepository implements com.softcraft.ohhsansibackend.departamento.domain.repository.abstraction.IDepartamentoDomainRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DepartamentoDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Departamento getDepartamentoById(int id) {
        String sql = "SELECT * FROM selectDepartamentoById(?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Departamento.class));
    }

    @Override
    public List<Departamento> getAllDepartamentos() {
        String sql = "SELECT * FROM selectAllDepartamentos()";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Departamento.class));
    }
}
