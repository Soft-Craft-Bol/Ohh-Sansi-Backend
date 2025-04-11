package com.softcraft.ohhsansibackend.colegio.domain.repository.implementation;

import com.softcraft.ohhsansibackend.colegio.domain.models.Colegio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ColegioDomainRepository implements com.softcraft.ohhsansibackend.colegio.domain.repository.abstraction.IColegioDomainRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ColegioDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Colegio> getColegios() {
        String sql = "SELECT * FROM selectAllColegios()";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Colegio.class));
    }
    @Override
    public List<Colegio> getColegiosByMunicipio(int idMunicipio) {
        String sql = "SELECT * FROM selectColegioByMunicipio(?)";
        return jdbcTemplate.query(sql, new Object[]{idMunicipio}, new BeanPropertyRowMapper<>(Colegio.class));
    }
}
