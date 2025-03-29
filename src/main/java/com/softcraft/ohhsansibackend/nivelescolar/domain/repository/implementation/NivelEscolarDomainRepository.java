package com.softcraft.ohhsansibackend.nivelescolar.domain.repository.implementation;

import com.softcraft.ohhsansibackend.nivelescolar.domain.models.NivelEscolar;
import com.softcraft.ohhsansibackend.nivelescolar.domain.repository.abstraction.INivelEscolarRepository;
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
    public NivelEscolar save(NivelEscolar nivelEscolar) {
        String sql = "SELECT insertNivelEscolar(?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{nivelEscolar.getNombreNivelEscolar()},
                new BeanPropertyRowMapper<>(NivelEscolar.class));
    }

    @Override
    public boolean update(NivelEscolar nivelEscolar) {
        String sql = "SELECT updateNivelEscolar(?, ?)";
        Boolean rowsAffected =jdbcTemplate.queryForObject(sql, Boolean.class, nivelEscolar.getIdNivel(), nivelEscolar.getNombreNivelEscolar());
        return rowsAffected;
    }

    @Override
    public boolean delete(int idNivel) {
        String sql = "SELECT deleteNivelEscolar(?)";
        Boolean rowsAffected = jdbcTemplate.queryForObject(sql, Boolean.class, idNivel);
        return rowsAffected;
    }

    @Override
    public NivelEscolar findById(int idNivel) {
        String sql = "SELECT * FROM selectNivelEscolarById(?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{idNivel}, new BeanPropertyRowMapper<>(NivelEscolar.class));
    }
    @Override
    public List<NivelEscolar> findAll() {
        String sql = "SELECT * FROM selectAllNivelEscolars()";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(NivelEscolar.class));
    }
}
