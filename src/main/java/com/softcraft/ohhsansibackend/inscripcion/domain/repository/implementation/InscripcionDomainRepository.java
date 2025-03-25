package com.softcraft.ohhsansibackend.inscripcion.domain.repository.implementation;

import com.softcraft.ohhsansibackend.inscripcion.domain.models.Inscripcion;
import com.softcraft.ohhsansibackend.inscripcion.domain.repository.abstraction.IInscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;


@Repository
public class InscripcionDomainRepository implements IInscripcionRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public InscripcionDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    @Override
//    public Inscripcion saveInscripcion(Inscripcion inscripcion) {
//        String sql = "SELECT insertInscripcion(?, ?)";
//        return jdbcTemplate.queryForObject(sql, new Object[]{inscripcion.getFechaInscripcion(), inscripcion.getHoraInscripcion()},
//                new BeanPropertyRowMapper<>(Inscripcion.class));
//    }
    @Override
    public Inscripcion saveInscripcion(Inscripcion inscripcion) {
        String sql = "INSERT INTO inscripcion (fecha_inscripcion, hora_inscripcion) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, inscripcion.getFechaInscripcion());
            ps.setTime(2, inscripcion.getHoraInscripcion());
            return ps;
        }, keyHolder);
        Map<String, Object> keys = keyHolder.getKeys();
        if (keys != null) {
            inscripcion.setIdInscripcion((Integer) keys.get("id_inscripcion"));
        }
        return inscripcion;
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
