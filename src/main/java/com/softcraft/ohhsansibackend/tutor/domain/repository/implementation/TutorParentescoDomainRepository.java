package com.softcraft.ohhsansibackend.tutor.domain.repository.implementation;

import com.softcraft.ohhsansibackend.tutor.domain.models.TutorParentesco;
import com.softcraft.ohhsansibackend.tutor.domain.repository.abstraction.ITutorParentesco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class TutorParentescoDomainRepository implements ITutorParentesco {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public TutorParentesco save(TutorParentesco tutorParentesco) {
        String sql = "INSERT INTO tutor_parentesco (parentesco) VALUES (?)";
        jdbcTemplate.update(sql, tutorParentesco.getParentesco());
        return tutorParentesco;
    }

    @Override
    public TutorParentesco update(TutorParentesco tutorParentesco) {
        String sql = "UPDATE tutor_parentesco SET parentesco = ? WHERE id_tutor_parentesco = ?";
        jdbcTemplate.update(sql, tutorParentesco.getParentesco(), tutorParentesco.getIdTutorParentesco());
        return tutorParentesco;
    }

    @Override
    public void delete(int idTutorParentesco) {
        String sql = "DELETE FROM tutor_parentesco WHERE id_tutor_parentesco = ?";
        jdbcTemplate.update(sql, idTutorParentesco);
    }

    @Override
    public TutorParentesco findByIdTutorParentesco(int idTutorParentesco) {
        String sql = "SELECT * FROM tutor_parentesco WHERE id_tutor_parentesco = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(TutorParentesco.class), idTutorParentesco);
    }

    @Override
    public List<TutorParentesco> findAllParentescos() {
        String sql = "SELECT * FROM tutor_parentesco";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TutorParentesco.class));
    }
}
