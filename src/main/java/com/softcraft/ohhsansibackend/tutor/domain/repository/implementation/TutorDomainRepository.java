package com.softcraft.ohhsansibackend.tutor.domain.repository.implementation;

import com.softcraft.ohhsansibackend.tutor.domain.models.Tutor;
import com.softcraft.ohhsansibackend.tutor.domain.repository.abstraction.ITutorRepository;
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
public class TutorDomainRepository implements ITutorRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Tutor save(Tutor tutor) {
        String sql = "INSERT INTO tutor (id_tipo_tutor, email_tutor, nombres_tutor, apellidos_tutor, telefono, carnet_identidad_tutor) VALUES (?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, tutor.getIdTipoTutor());
            ps.setString(2, tutor.getEmailTutor());
            ps.setString(3, tutor.getNombresTutor());
            ps.setString(4, tutor.getApellidosTutor());
            ps.setInt(5, tutor.getTelefono());
            ps.setInt(6, tutor.getCarnetIdentidadTutor());
            return ps;
        }, keyHolder);
        Map<String, Object> keys = keyHolder.getKeys();
        if (keys != null) {
            tutor.setIdTutor(((Number) keys.get("id_tutor")).longValue());
        }
        return tutor;
    }

    @Override
    public Tutor findByIdTutor(int idTutor) {
        String sql = "SELECT * FROM tutor WHERE id_tutor = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Tutor.class), idTutor);
    }

    @Override
    public List<Tutor> findAllTutor() {
        String sql = "SELECT * FROM tutor";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Tutor.class));
    }

}
