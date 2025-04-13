package com.softcraft.ohhsansibackend.fechasolimpiada.domain.repository.implementation;

import com.softcraft.ohhsansibackend.fechasolimpiada.domain.models.Olimpiada;
import com.softcraft.ohhsansibackend.fechasolimpiada.domain.repository.abstraction.IOlimpiadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class OlimpiadaDomainRepository implements IOlimpiadaRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OlimpiadaDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Olimpiada saveOlimpiada(Olimpiada olimpiada) {
        String sql = "SELECT * FROM insertOlimpiada(?, ?, ?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{
                olimpiada.getNombreOlimpiada(),
                olimpiada.getEstadoOlimpiada(),
                olimpiada.getPrecioOlimpiada()
        }, new BeanPropertyRowMapper<>(Olimpiada.class));
    }

    @Override
    public boolean deleteOlimpiada(int idOlimpiada) {
        String sql = "SELECT deleteOlimpiada(?)";
        Boolean response = jdbcTemplate.queryForObject(sql, Boolean.class, idOlimpiada);
        return response != null && response;
    }

    @Override
    public Olimpiada getOlimpiada(int idOlimpiada) {
        String sql = "SELECT * FROM selectOlimpiadaById(?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{idOlimpiada},
                new BeanPropertyRowMapper<>(Olimpiada.class));
    }

    @Override
    public Olimpiada getOlimpiadaPublic() {
        String sql = "SELECT * FROM selectOlimpiadaPublic()";
        return jdbcTemplate.queryForObject(sql,
                new BeanPropertyRowMapper<>(Olimpiada.class));
    }

    @Override
    public List<Olimpiada> getOlimpiadas() {
        String sql = "SELECT * FROM selectOlimpiada()";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Olimpiada.class));
    }

    @Override
    public boolean updatePrecioOlimpiada(int idOlimpiada, BigDecimal nuevoPrecio) {
        String sql = "SELECT updatePrecioOlimpiada(?, ?)";
        Boolean result = jdbcTemplate.queryForObject(sql, Boolean.class, idOlimpiada, nuevoPrecio);
        return result != null && result;
    }



}
