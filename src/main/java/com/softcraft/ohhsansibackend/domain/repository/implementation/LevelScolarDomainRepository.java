package com.softcraft.ohhsansibackend.domain.repository.implementation;

import com.softcraft.ohhsansibackend.domain.models.LevelScolar;
import com.softcraft.ohhsansibackend.domain.repository.abstraction.ILevelScolarRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LevelScolarDomainRepository implements ILevelScolarRepository {
    private final JdbcTemplate jdbcTemplate;

    public LevelScolarDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(LevelScolar levelScolar) {
        String sql = "SELECT InsertLevelScolar(?)";
        jdbcTemplate.update(sql, levelScolar.getNameLevelScolar());
    }

    @Override
    public void update(LevelScolar levelScolar) {
        String sql = "SELECT UpdateLevelScolar(?)";
        jdbcTemplate.update(sql, levelScolar.getNameLevelScolar());
    }

    @Override
    public void delete(Long idLevel) {
        String sql = "SELECT DeleteLevelScolar(?)";
        jdbcTemplate.update(sql, idLevel);
    }

    @Override
    public Optional<LevelScolar> findById(Long idLevel) {
        String sql = "SELECT * FROM SelectLevelScolarById(?)";
        List<LevelScolar> levelScolars = jdbcTemplate.query(sql, new Object[]{idLevel}, new BeanPropertyRowMapper<>(LevelScolar.class));
        return levelScolars.stream().findFirst();
    }
    @Override
    public List<LevelScolar> findAll() {
        String sql = "SELECT * FROM SelectAllLevelScolars()";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(LevelScolar.class));
    }
}
