package com.softcraft.ohhsansibackend.domain.repository.implementation;

import com.softcraft.ohhsansibackend.domain.models.Area;
import com.softcraft.ohhsansibackend.domain.repository.abstraction.AreaRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class AreaDomainRepository implements AreaRepository {
    private final JdbcTemplate jdbcTemplate;

    public AreaDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Area area) {
        String sql = "SELECT InsertArea(?)";
        Boolean result = jdbcTemplate.queryForObject(sql, Boolean.class, area.getNombreArea());
        if (result== null || !result) {
            throw new RuntimeException("Not able to save area");
        }
    }

    @Override
    public void update(Area area) {
        String sql = "SELECT UpdateArea(?, ?)";
        Boolean result = jdbcTemplate.queryForObject(sql, Boolean.class, area.getNombreArea());
        if (result== null || !result) {
            throw new RuntimeException("Not able to update area");
        }
    }

    @Override
    public void delete(Long idArea) {
        String sql = "SELECT DeleteArea(?)";
        Boolean result = jdbcTemplate.queryForObject(sql, Boolean.class, idArea);
        if (result== null || !result) {
            throw new RuntimeException("Not able to delete area");
        }
    }

    @Override
    public Optional<Area> findById(Long idArea) {
        String sql = "SELECT * FROM SelectAreaById(CAST(? AS INTEGER))";
        List<Area> areas = jdbcTemplate.query(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, idArea);
            return ps;
        }, new BeanPropertyRowMapper<>(Area.class));

        return areas.stream().findFirst();
    }

    @Override
    public List<Area> findAll() {
        String sql = "SELECT * FROM SelectAllAreas()";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Area.class));
    }

}
