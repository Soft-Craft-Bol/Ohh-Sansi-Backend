package com.softcraft.ohhsansibackend.domain.repository;

import com.softcraft.ohhsansibackend.domain.models.TipeSubjectCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TipeSubjectCategoryDomainRepository implements TipeSubjectCategoryRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TipeSubjectCategoryDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(TipeSubjectCategory tipeSubjectCategory) {
        String sql = "SELECT InsertTipeSubjectCategory(?)";
        jdbcTemplate.update(sql, tipeSubjectCategory.getNameSubject());
    }

    @Override
    public void update(TipeSubjectCategory tipeSubjectCategory) {
        String sql = "SELECT UpdateTipeSubjectCategory(?, ?)";
        jdbcTemplate.update(sql, tipeSubjectCategory.getIdTipeSubjectCategory(), tipeSubjectCategory.getNameSubject());
    }

    @Override
    public void delete(Long idTipeSubjectCategory) {
        String sql = "SELECT DeleteTipeSubjectCategory(?)";
        jdbcTemplate.update(sql, idTipeSubjectCategory);
    }

    @Override
    public Optional<TipeSubjectCategory> findById(Long idTipeSubjectCategory) {
        String sql = "SELECT * FROM SelectTipeSubjectCategoryById(?)";
        List<TipeSubjectCategory> types = jdbcTemplate.query(sql, new Object[]{idTipeSubjectCategory},
                new BeanPropertyRowMapper<>(TipeSubjectCategory.class));
        return types.stream().findFirst();
    }

    @Override
    public List<TipeSubjectCategory> findAll() {
        String sql = "SELECT * FROM SelectAllTipeSubjectCategory()";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TipeSubjectCategory.class));
    }

}
