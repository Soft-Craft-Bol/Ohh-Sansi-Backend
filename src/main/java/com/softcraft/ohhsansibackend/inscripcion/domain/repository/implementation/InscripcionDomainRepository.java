package com.softcraft.ohhsansibackend.inscripcion.domain.repository.implementation;

import com.softcraft.ohhsansibackend.exception.ResourceNotFoundException;
import com.softcraft.ohhsansibackend.inscripcion.domain.models.Inscripcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
public class InscripcionDomainRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public InscripcionDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Inscripcion saveInscripcion(Inscripcion inscripcion) {
        String sql = "INSERT INTO inscripcion (fecha_inscripcion, hora_inscripcion, codigo_unico_inscripcion) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, inscripcion.getFechaInscripcion());
            ps.setTime(2, inscripcion.getHoraInscripcion());
            ps.setString(3, inscripcion.getCodigoUnicoInscripcion());
            return ps;
        }, keyHolder);
        Map<String, Object> keys = keyHolder.getKeys();
        if (keys != null) {
            inscripcion.setIdInscripcion((Integer) keys.get("id_inscripcion"));
        }
        return inscripcion;
    }


    public Inscripcion findByIdInscripcion(int idInscripcion) {
        String sql = "SELECT * FROM inscripcion WHERE id_inscripcion = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{idInscripcion},
                    new BeanPropertyRowMapper<>(Inscripcion.class));
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Inscripcion con ID " + idInscripcion + " no encontrada");
        }
    }


    public List <Inscripcion>findAllInscripcion() {
        String sql = "SELECT * FROM inscripcion";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Inscripcion.class));
    }


    public Long findIdByCodigoUnico(String codigoUnicoInscripcion) {
        String sql = "SELECT id_inscripcion FROM inscripcion WHERE codigo_unico_inscripcion = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{codigoUnicoInscripcion}, Long.class);
    }
    public List<Map<String, Object>> findInscripcionById(int idInscripcion) {
        String sql = "SELECT * FROM inscripcion WHERE id_inscripcion = ?";
        return jdbcTemplate.queryForList(sql, idInscripcion);
    }

    public List<Map<String, Object>> findParticipantesByInscripcionId(int idInscripcion) {
        String sql = "SELECT * FROM participante WHERE id_inscripcion = ?";
        return jdbcTemplate.queryForList(sql, idInscripcion);
    }

    public List<Map<String, Object>> findInscripcionAreasByInscripcionId(int idInscripcion) {
        String sql = "SELECT * FROM inscripcion_area WHERE id_inscripcion = ?";
        return jdbcTemplate.queryForList(sql, idInscripcion);
    }

    public List<Map<String, Object>> findAreasByInscripcionId(int idInscripcion) {
        String sql = "SELECT a.nombre_area, a.nombre_corto_area, a.descripcion_area " +
                "FROM area a, inscripcion_area ia " +
                "WHERE ia.id_inscripcion = ? AND a.id_area = ia.id_area";
        return jdbcTemplate.queryForList(sql, idInscripcion);
    }

    public List<Map<String, Object>> findTutoresByInscripcionId(int idInscripcion) {
        String sql = "SELECT t.email_tutor, t.nombres_tutor, t.apellidos_tutor, t.telefono, " +
                "t.carnet_identidad_tutor, tt.nombre_tipo_tutor " +
                "FROM tutor t, participante_tutor pt, participante p, tipo_tutor tt " +
                "WHERE t.id_tutor = pt.id_tutor AND p.id_participante = pt.id_participante " +
                "AND pt.id_inscripcion = ? AND tt.id_tipo_tutor = t.id_tipo_tutor";
        return jdbcTemplate.queryForList(sql, idInscripcion);
    }
}
