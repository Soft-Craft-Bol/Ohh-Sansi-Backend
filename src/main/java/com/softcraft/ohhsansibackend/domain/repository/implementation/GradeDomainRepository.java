package com.softcraft.ohhsansibackend.domain.repository.implementation;

import com.softcraft.ohhsansibackend.domain.models.Grade;
import com.softcraft.ohhsansibackend.domain.repository.abstraction.GradeRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GradeDomainRepository implements GradeRepository {
    private final JdbcTemplate jdbcTemplate;

    public GradeDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Grade grade) {
        String sql = "SELECT InsertGrade(?, ?)";
        jdbcTemplate.update(sql, grade.getIdGrade(), grade.getNameGrade());
    }

    @Override
    public void update(Grade grade) {
        String sql = "SELECT UpdateGrade(?, ?)";
        jdbcTemplate.update(sql, grade.getIdGrade(), grade.getNameGrade());
    }

    @Override
    public void delete(Long idGrade) {
        String sql = "SELECT DeleteGrade(?)";
        jdbcTemplate.update(sql, idGrade);
    }

    @Override
    public Optional<Grade> findById(Long idGrade) {
        String sql = "SELECT * FROM SelectGradeById(?)";
        List<Grade> grades = jdbcTemplate.query(sql, new Object[]{idGrade}, new BeanPropertyRowMapper<>(Grade.class));
        return grades.stream().findFirst();
    }
    @Override
    public List<Grade> findAll() {
        String sql = "SELECT * FROM SelectAllGrades()";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Grade.class));
    }
}
