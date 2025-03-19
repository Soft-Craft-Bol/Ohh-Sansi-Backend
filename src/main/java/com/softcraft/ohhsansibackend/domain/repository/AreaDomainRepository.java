package com.softcraft.ohhsansibackend.domain.repository;

import com.softcraft.ohhsansibackend.domain.models.Area;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AreaDomainRepository {
    private final JdbcTemplate jdbcTemplate;

    public AreaDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Area area) {
//        String sql = "INSERT INTO AREA (ID_AREA, NOMBRE_AREA) VALUES (?, ?)";
        String sql = "select InsertArea('HUmanidades');";
        jdbcTemplate.update(sql, area.getNombreArea());
    }

    public void update(Area area) {
        String sql = "select UpdateArea(?, ?);";
        jdbcTemplate.update(sql, area.getNombreArea(), area.getIdArea());
    }

    public void delete(Long idArea) {  // Cambiado a Long
        String sql = "select DeleteArea(?);";
        jdbcTemplate.update(sql, idArea);
    }

    public Optional<Area> findById(Long idArea) { // Ahora devuelve Optional<Area>
        String sql = "select * from SelectAreaById(?);";
        List<Area> areas = jdbcTemplate.query(sql, new Object[]{idArea}, new BeanPropertyRowMapper<>(Area.class));
        return areas.stream().findFirst();  // Evita error si no encuentra nada
    }

    public List<Area> findAll() {
        String sql = "select * from SelectAllAreas();";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Area.class));
    }

}
