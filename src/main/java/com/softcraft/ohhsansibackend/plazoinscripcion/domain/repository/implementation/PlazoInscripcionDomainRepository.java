package com.softcraft.ohhsansibackend.plazoinscripcion.domain.repository.implementation;

import com.softcraft.ohhsansibackend.plazoinscripcion.domain.models.PlazoInscripcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public class PlazoInscripcionDomainRepository implements com.softcraft.ohhsansibackend.plazoinscripcion.domain.repository.abstraction.IPlazoInscripcionRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PlazoInscripcionDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public PlazoInscripcion upsertPlazoInscripcion(PlazoInscripcion plazoInscripcion) {
        String sql = "SELECT * FROM upsertPlazoInscripcion(?, ?, ?, ?)";

        return jdbcTemplate.queryForObject(sql, new Object[]{
                plazoInscripcion.getFechaInicioInscripcion(),
                plazoInscripcion.getFechaFinInscripcion(),
                plazoInscripcion.getFechaResultados(),
                plazoInscripcion.getFechaPremiacion()
        }, new BeanPropertyRowMapper<>(PlazoInscripcion.class));
    }

    @Override
    public PlazoInscripcion insertPlazoInscripcion(PlazoInscripcion plazoInscripcion) {
        String sql = "SELECT savePlazoInscripcion( ?, ?, ?, ?, ?)";
        List<PlazoInscripcion> result = jdbcTemplate.query(sql, new Object[]{plazoInscripcion.getFechaInicioInscripcion(), plazoInscripcion.getFechaFinInscripcion(), plazoInscripcion.getFechaResultados(),plazoInscripcion.getFechaPremiacion(), plazoInscripcion.getFechaPlazoInscripcionActivo()},
                new BeanPropertyRowMapper<>(PlazoInscripcion.class));
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public PlazoInscripcion updatePlazoInscripcion(PlazoInscripcion plazoInscripcion) {
        String sql = "SELECT updatePlazoInscripcion(?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{plazoInscripcion.getIdPlazoInscripcion(), plazoInscripcion.getFechaInicioInscripcion(), plazoInscripcion.getFechaFinInscripcion(), plazoInscripcion.getFechaResultados(), plazoInscripcion.getFechaPremiacion(), plazoInscripcion.getFechaPlazoInscripcionActivo()},
                new BeanPropertyRowMapper<>(PlazoInscripcion.class));
    }

    @Override
    public boolean deletePlazoInscripcion(int id) {
        String sql = "SELECT deletePlazoInscripcion(?)";
        Boolean response = jdbcTemplate.queryForObject(sql, Boolean.class, id);
        return response != null && response;
    }

    @Override
    public PlazoInscripcion getPlazoInscripcion(int id) {
        String sql = "SELECT * FROM selectPlazoInscripcionById(?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{id},
                new BeanPropertyRowMapper<>(PlazoInscripcion.class));
    }

    @Override
    public PlazoInscripcion getPlazoInscripcionActivo() {
        String sql = "SELECT * FROM selectPlazoInscripcionActivo()";
        return jdbcTemplate.queryForObject(sql,
                new BeanPropertyRowMapper<>(PlazoInscripcion.class));
    }

    @Override
    public PlazoInscripcion getPlazoInscripcionByDate(LocalDate date) {
        String sql = "SELECT * FROM selectPlazoInscripcionByDate(?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{date},
                new BeanPropertyRowMapper<>(PlazoInscripcion.class));
    }
}
