package com.softcraft.ohhsansibackend.fechasolimpiada.domain.repository.implementation;

import com.softcraft.ohhsansibackend.fechasolimpiada.domain.models.FechaOlimpiada;
import com.softcraft.ohhsansibackend.fechasolimpiada.domain.repository.abstraction.IFechaOlimpiadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public class FechaOlimpiadaDomainRepository implements IFechaOlimpiadaRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FechaOlimpiadaDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public FechaOlimpiada upsertFechaOlimpiada(FechaOlimpiada fechaOlimpiada) {
        String sql = "SELECT * FROM upsertFechaOlimpiada(?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{
                fechaOlimpiada.getIdOlimpiada(),
                fechaOlimpiada.getIdOlimpiada(),
                fechaOlimpiada.getFechaInicio(),
                fechaOlimpiada.getFechaFin(),
                fechaOlimpiada.getNombreEvento(),
                fechaOlimpiada.getEsPublica()
        }, new BeanPropertyRowMapper<>(FechaOlimpiada.class));
    }

    @Override
    public boolean deleteFechaOlimpiada(int id) {
        String sql = "SELECT deleteFechaOlimpiada(?)";
        Boolean response = jdbcTemplate.queryForObject(sql, Boolean.class, id);
        return response != null && response;
    }

    @Override
    public List<FechaOlimpiada> getFechaOlimpiada() {
        String sql = "SELECT * FROM selectAllFechaOlimpiada()";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(FechaOlimpiada.class));
    }

    @Override
    public FechaOlimpiada getFechaOlimpiada(int id) {
        String sql = "SELECT * FROM selectFechaOlimpiadaById(?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{id},
                new BeanPropertyRowMapper<>(FechaOlimpiada.class));
    }

    @Override
    public FechaOlimpiada getFechaOlimpiadaPublic() {
        String sql = "SELECT * FROM selectFechaOlimpiadaPublic()";
        return jdbcTemplate.queryForObject(sql,
                new BeanPropertyRowMapper<>(FechaOlimpiada.class));
    }

}
