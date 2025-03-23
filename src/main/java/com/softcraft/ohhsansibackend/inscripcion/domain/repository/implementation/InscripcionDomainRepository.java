package com.softcraft.ohhsansibackend.inscripcion.domain.repository.implementation;

import com.softcraft.ohhsansibackend.exception.ResourceNotFoundException;
import com.softcraft.ohhsansibackend.inscripcion.domain.models.Inscripcion;
import com.softcraft.ohhsansibackend.inscripcion.domain.repository.abstraction.IInscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;


@Repository
public class InscripcionDomainRepository implements IInscripcionRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public InscripcionDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Inscripcion saveInscripcion(Inscripcion inscripcion) {
        String sql = "SELECT * FROM insertInscripcion(?, ?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{inscripcion.getFechaInscripcion(), inscripcion.getHoraInscripcion()},
                new BeanPropertyRowMapper<>(Inscripcion.class));
    }

    @Override
    public boolean updateInscription(Inscripcion inscripcion) {
        String sql = "SELECT updateInscripcion(?, ?, ?)";
        Boolean result = jdbcTemplate.queryForObject(sql, Boolean.class, inscripcion.getIdInscripcion(), inscripcion.getFechaInscripcion(), inscripcion.getHoraInscripcion());
        return result;

    }

    @Override
    public boolean deleteInscripcion(int idInscripcion) {
        String sql = "SELECT deleteInscripcion(?)";
        Boolean result = jdbcTemplate.queryForObject(sql, Boolean.class, idInscripcion);
        return result != null && result;
    }

    @Override
    public Inscripcion findByIdInscripcion(int idInscripcion) {
        String sql = "SELECT * FROM selectInscripcionById(?)";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{idInscripcion},
                    new BeanPropertyRowMapper<>(Inscripcion.class));
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Inscripcion con ID " + idInscripcion + " no encontrada");
        }
    }

    @Override
    public List <Inscripcion>findAllInscripcion() {
        String sql = "SELECT * FROM selectAllInscripciones()";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Inscripcion.class));
    }

    @Override
    public List<Inscripcion> findByDateAndTime(Date date, Time time) {
        String sql = "SELECT * FROM selectInscripcionByDateAndTime(?, ?)";
        return jdbcTemplate.query(sql, new Object[]{date, time},
                new BeanPropertyRowMapper<>(Inscripcion.class));
    }

    @Override
    public List<Inscripcion> findByRangeDate(LocalDate fechaInicio, LocalDate fechaFin) {
        String sql = "SELECT * FROM selectInscripcionesByDateRange(?, ?)";
        return jdbcTemplate.query(sql, new Object[]{
                Date.valueOf(fechaInicio),
                Date.valueOf(fechaFin)
        }, new BeanPropertyRowMapper<>(Inscripcion.class));
    }
}
