package com.softcraft.ohhsansibackend.periodosolimpiada.domain.repository.implementation;

import com.softcraft.ohhsansibackend.periodosolimpiada.domain.models.Olimpiada;
import com.softcraft.ohhsansibackend.periodosolimpiada.domain.repository.abstraction.IOlimpiadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class OlimpiadaDomainRepository implements IOlimpiadaRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OlimpiadaDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Olimpiada saveOlimpiada(Olimpiada olimpiada) {
        String sql = "SELECT * FROM crear_olimpiada(?, ?, ?, ?, ?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{
                olimpiada.getAnio(),
                olimpiada.getNombreOlimpiada(),
                olimpiada.getPrecioOlimpiada(),
                olimpiada.getFechaInicio(),
                olimpiada.getFechaFin()
        }, new BeanPropertyRowMapper<>(Olimpiada.class));
    }

    @Override
    public boolean deleteOlimpiada(int idOlimpiada) {
        String sql = "SELECT deleteOlimpiada(?)";
        Boolean response = jdbcTemplate.queryForObject(sql, Boolean.class, idOlimpiada);
        return response != null && response;
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

    @Override
    public Optional<Olimpiada> findById(Integer idOlimpiada) {
        String sql = "SELECT * FROM olimpiada WHERE id_olimpiada = ?";
        try {
            Olimpiada olimpiada = jdbcTemplate.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<>(Olimpiada.class),
                    idOlimpiada
            );
            return Optional.ofNullable(olimpiada);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
