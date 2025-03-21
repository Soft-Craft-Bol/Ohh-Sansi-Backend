package com.softcraft.ohhsansibackend.domain.repository.implementation;

import com.softcraft.ohhsansibackend.domain.models.Tutor;
import com.softcraft.ohhsansibackend.domain.repository.abstraction.ITutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TutorDomainRepository implements ITutorRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Tutor save(Tutor tutor) {
        String sql = "INSERT INTO tutor (id_tipo_tutor, email_tutor, nombres_tutor, apellidos_tutor, telefono, carnet_identidad_tutor) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, tutor.getIdTipoTutor(), tutor.getEmailTutor(), tutor.getNombresTutor(), tutor.getApellidosTutor(), tutor.getTelefono(), tutor.getCarnetIdentidadTutor());
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
