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
        String sql = "INSERT INTO AREA (ID_AREA, NOMBRE_AREA) VALUES (?, ?)";
        jdbcTemplate.update(sql, area.getIdArea(), area.getNombreArea());
    }

    public void update(Area area) {
        String sql = "UPDATE AREA SET NOMBRE_AREA = ? WHERE ID_AREA = ?";
        jdbcTemplate.update(sql, area.getNombreArea(), area.getIdArea());
    }

    public void delete(Long idArea) {  // Cambiado a Long
        String sql = "DELETE FROM AREA WHERE ID_AREA = ?";
        jdbcTemplate.update(sql, idArea);
    }

    public Optional<Area> findById(Long idArea) { // Ahora devuelve Optional<Area>
        String sql = "SELECT * FROM AREA WHERE ID_AREA = ?";
        List<Area> areas = jdbcTemplate.query(sql, new Object[]{idArea}, new BeanPropertyRowMapper<>(Area.class));
        return areas.stream().findFirst();  // Evita error si no encuentra nada
    }

    public List<Area> findAll() {
        String sql = "SELECT * FROM AREA";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Area.class));
    }

    public List<Area> findByNombreArea(String nombreArea) {
        String sql = "SELECT * FROM AREA WHERE NOMBRE_AREA = ?";
        return jdbcTemplate.query(sql, new Object[]{nombreArea}, new BeanPropertyRowMapper<>(Area.class));
    }
}
