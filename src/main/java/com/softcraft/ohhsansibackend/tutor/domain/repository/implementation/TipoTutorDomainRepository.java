package com.softcraft.ohhsansibackend.tutor.domain.repository.implementation;

import com.softcraft.ohhsansibackend.tutor.domain.models.TipoTutor;
import com.softcraft.ohhsansibackend.tutor.domain.repository.abstraction.ITipoTutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TipoTutorDomainRepository implements ITipoTutorRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public TipoTutor save(TipoTutor tipoTutor) {
        String sql = "INSERT INTO tipo_tutor (nombre_tipo_tutor) VALUES (?)";
        jdbcTemplate.update(sql, tipoTutor.getNombreTipoTutor());
        return tipoTutor;
    }

    @Override
    public TipoTutor update(TipoTutor tipoTutor) {
        String sql = "UPDATE tipo_tutor SET nombre_tipo_tutor = ? WHERE id_tipo_tutor = ?";
        jdbcTemplate.update(sql, tipoTutor.getNombreTipoTutor(), tipoTutor.getIdTipoTutor());
        return tipoTutor;
    }

    @Override
    public void delete(int idTipoTutor) {
        String sql = "DELETE FROM tipo_tutor WHERE id_tipo_tutor = ?";
        jdbcTemplate.update(sql, idTipoTutor);
    }

    @Override
    public TipoTutor findByIdTipoTutor(int idTipoTutor) {
        String sql = "SELECT * FROM tipo_tutor WHERE id_tipo_tutor = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(TipoTutor.class), idTipoTutor);
    }

    @Override
    public List<TipoTutor> findAllTipoTutor() {
        String sql = "SELECT * FROM tipo_tutor";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TipoTutor.class));
    }
}
