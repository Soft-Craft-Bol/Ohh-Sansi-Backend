package com.softcraft.ohhsansibackend.area.domain.repository.implementation;

import com.softcraft.ohhsansibackend.area.domain.models.Area;
import com.softcraft.ohhsansibackend.area.domain.repository.abstraction.IAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AreaDomainRepository implements IAreaRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AreaDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Area save(Area area) {
        String sql = "SELECT insertarea(?, ? , ?)";
        return jdbcTemplate.queryForObject(sql,
                new Object[] { area.getNombreArea(), area.getNombreCortoArea(), area.getDescripcionArea()
                }, new BeanPropertyRowMapper<>(Area.class));
    }

    @Override
    public List<Area> findAll() {
        String sql = "SELECT * FROM SelectAllAreas()";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Area.class));
    }

    @Override
    public boolean update(Area area) {
        String sql = "SELECT UpdateArea(?, ?, ?, ?)";
        Boolean rowsAffected = jdbcTemplate.queryForObject(sql, Boolean.class, area.getIdArea(), area.getNombreArea(),
                area.getNombreCortoArea(), area.getDescripcionArea());
        return rowsAffected != null && rowsAffected;
    }

    @Override
    public boolean delete(int idArea) {
        String sql = "SELECT deleteArea(?)";
        Integer result = jdbcTemplate.queryForObject(sql, Integer.class, idArea);
        return result != null && result > 0;
    }

    @Override
    public Area findById(int idArea) {
        String sql = "SELECT * FROM SelectAreaById(?)";
        return jdbcTemplate.queryForObject(sql, new Object[] { idArea }, new BeanPropertyRowMapper<>(Area.class));
    }

}
