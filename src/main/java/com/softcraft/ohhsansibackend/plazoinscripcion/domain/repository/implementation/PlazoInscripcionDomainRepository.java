package com.softcraft.ohhsansibackend.plazoinscripcion.domain.repository.implementation;

import com.softcraft.ohhsansibackend.plazoinscripcion.domain.models.PlazoInscripcion;
import com.softcraft.ohhsansibackend.plazoinscripcion.domain.repository.abstraction.IPlazoInscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public class PlazoInscripcionDomainRepository implements IPlazoInscripcionRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PlazoInscripcionDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public PlazoInscripcion upsertPlazoInscripcion(PlazoInscripcion plazoInscripcion) {
        String sql = "SELECT * FROM upsertPlazoInscripcion(?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.queryForObject(sql, new Object[]{
                plazoInscripcion.getNombrePeriodoInscripcion(),
                plazoInscripcion.getFechaInicioInscripcion(),
                plazoInscripcion.getFechaFinInscripcion(),
                plazoInscripcion.getFechaInicioOlimpiadas(),
                plazoInscripcion.getFechaFinOlimpiadas(),
                plazoInscripcion.getFechaResultados(),
                plazoInscripcion.getFechaPremiacion()
        }, new BeanPropertyRowMapper<>(PlazoInscripcion.class));
    }

    @Override
    public boolean deletePlazoInscripcion(int id) {
        String sql = "SELECT deletePlazoInscripcion(?)";
        Boolean response = jdbcTemplate.queryForObject(sql, Boolean.class, id);
        return response != null && response;
    }

    @Override
    public List<PlazoInscripcion> getPlazosInscripcion() {
        String sql = "SELECT * FROM selectAllPlazoInscripcion()";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(PlazoInscripcion.class));
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

    @Override
    public PlazoInscripcion insertPrecioPeriodo(PlazoInscripcion plazoInscripcion) {
        String sql = "SELECT insertPrecioPlazoInscripcion(?, ?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{plazoInscripcion.getIdPeriodoInscripcion(), plazoInscripcion.getPrecioPeriodo()},
                new BeanPropertyRowMapper<>(PlazoInscripcion.class));
    }
}
