package com.softcraft.ohhsansibackend.tutor.domain.repository.implementation;

import com.softcraft.ohhsansibackend.tutor.domain.models.TutorArea;
import com.softcraft.ohhsansibackend.tutor.domain.repository.abstraction.TutorAreaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TutorAreaRepositoryImpl implements TutorAreaRepository {

    private final JdbcTemplate jdbcTemplate;

    public TutorAreaRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public TutorArea save(TutorArea tutorArea) {
        String sql = "INSERT INTO tutor_area (id_area, id_tutor) VALUES (?, ?) RETURNING id_tutor_area";
        int idTutorArea = jdbcTemplate.queryForObject(sql, new Object[]{
                tutorArea.getIdArea(),
                tutorArea.getIdTutor()
        }, Integer.class);
        tutorArea.setIdTutorArea(idTutorArea);
        return tutorArea;
    }
}