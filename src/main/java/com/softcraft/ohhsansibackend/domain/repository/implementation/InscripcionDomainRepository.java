package com.softcraft.ohhsansibackend.domain.repository.implementation;

import com.softcraft.ohhsansibackend.domain.models.Inscripcion;
import com.softcraft.ohhsansibackend.domain.repository.abstraction.IInscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
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
        String sql = "SELECT insertInscripcion(?, ?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{inscripcion.getFechaInscripcion(), inscripcion.getHoraInscripcion()},
                new BeanPropertyRowMapper<>(Inscripcion.class));
    }

    @Override
    public boolean updateInscription(Inscripcion inscripcion) {
        String sql = "SELECT updateInscription(?, ?, ?)";
        Boolean result = jdbcTemplate.queryForObject(sql, Boolean.class, inscripcion.getIdInscripcion(), inscripcion.getFechaInscripcion(), inscripcion.getHoraInscripcion());
        return result;

    }

    @Override
    public boolean deleteInscripcion(int idInscripcion) {
        String sql = "SELECT deleteInscription(?)";
        Boolean result = jdbcTemplate.queryForObject(sql, Boolean.class);
        return result;
    }

    @Override
    public Inscripcion findByIdInscripcion(int idInscripcion) {
        String sql = "SELECT * FROM selectInscripcionById(?)";
        List<Inscripcion> inscripciones = jdbcTemplate.query(sql, new Object[]{idInscripcion}, new BeanPropertyRowMapper<>(Inscripcion.class));
        return inscripciones.isEmpty() ? null : inscripciones.get(0);
    }

    @Override
    public List <Inscripcion>findAllInscripcion() {
        String sql = "SELECT * FROM selectAllInscripciones()";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Inscripcion.class));
    }

    @Override
    public List<Inscripcion> findByDateAndTime(String date, String time) {
        String sql = "SELECT * FROM selectInscripcionByDateAndTime(?, ?)";
        return jdbcTemplate.query(sql, new Object[]{date, time},
                new BeanPropertyRowMapper<>(Inscripcion.class));
    }

    @Override
    public List<Inscripcion> findByDate(String date) {
        String sql = "SELECT * FROM selectInscripcionesByDateRange(?)";
        return jdbcTemplate.query(sql, new Object[]{date},
                new BeanPropertyRowMapper<>(Inscripcion.class));
    }
}
