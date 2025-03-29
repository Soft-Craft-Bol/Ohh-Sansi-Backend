package com.softcraft.ohhsansibackend.area.domain.repository.implementation;

import com.softcraft.ohhsansibackend.area.domain.models.Area;
import com.softcraft.ohhsansibackend.area.domain.repository.abstraction.IAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
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
        String sql = "SELECT * FROM insertarea(?, ?, ?, ?)";
        List<Area> result = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Area.class),
                area.getNombreArea(),
                area.getPrecioArea(),
                area.getDescripcionArea(),
                area.getAreaStatus()
        );

        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public boolean update(Area area) {
        String sql = "SELECT updatearea(?, ?, ?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, area.getIdArea(), area.getNombreArea(), area.getPrecioArea(), area.getDescripcionArea(), area.getAreaStatus());
        return rowsAffected > 0;
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
        return jdbcTemplate.queryForObject(sql, new Object[]{idArea}, new
                BeanPropertyRowMapper<>(Area.class));
    }

    @Override
    public List<Area> findAll() {
        String sql = "SELECT * FROM SelectAllAreas()";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Area.class));
    }

    @Override
    public boolean updatePrecio(int idArea, BigDecimal precioArea){
        String sql = "SELECT UpdatePrecioArea(?, ?)";
        Boolean rowsAffected = jdbcTemplate.queryForObject(sql, Boolean.class, idArea, precioArea);
        return rowsAffected != null && rowsAffected;
    }

}
