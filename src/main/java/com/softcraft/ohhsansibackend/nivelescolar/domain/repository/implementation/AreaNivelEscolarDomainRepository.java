package com.softcraft.ohhsansibackend.nivelescolar.domain.repository.implementation;

import com.softcraft.ohhsansibackend.area.domain.models.AreaNivelEscolar;
import com.softcraft.ohhsansibackend.nivelescolar.dto.AreaCategoriaNivelDTO;
import com.softcraft.ohhsansibackend.nivelescolar.dto.AreaNivelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class AreaNivelEscolarDomainRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AreaNivelEscolarDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public AreaNivelEscolar save(AreaNivelEscolar areaNivelEscolar) {
        String sql = "INSERT INTO area_nivel_escolar (id_nivel, id_area) VALUES (?, ?) RETURNING id_area_nivel_escolar";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, areaNivelEscolar.getIdNivel());
            ps.setInt(2, areaNivelEscolar.getIdArea());
            return ps;
        }, keyHolder);

        int newId = keyHolder.getKey().intValue();
        areaNivelEscolar.setIdAreaNivelEscolar(newId);
        return areaNivelEscolar;
    }
    public List<AreaNivelDTO> findAreasByNivel(int idNivel) {
        String sql = "SELECT an.id_area, a.nombre_area, a.precio_area, a.nombre_corto_area, a.descripcion_area AS area, ne.codigo_nivel AS nivel " +
                "FROM area_nivel_escolar an " +
                "JOIN area a ON an.id_area = a.id_area " +
                "JOIN nivel_escolar ne ON an.id_nivel = ne.id_nivel " +
                "WHERE an.id_nivel = ?";

        return jdbcTemplate.query(sql, new Object[]{idNivel}, new RowMapper<AreaNivelDTO>() {
            @Override
            public AreaNivelDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                AreaNivelDTO dto = new AreaNivelDTO();
                dto.setIdArea(rs.getInt("id_area"));
                dto.setNombreArea(rs.getString("nombre_area"));
                dto.setPrecioArea(rs.getDouble("precio_area"));
                dto.setNombreCortoArea(rs.getString("nombre_corto_area"));
                dto.setDescripcionArea(rs.getString("area"));
                dto.setCodigoNivel(rs.getString("nivel"));
                return dto;
            }
        });
    }
    public List<AreaCategoriaNivelDTO> findAreasCategoriasByNivel(int idNivel) {
        String sql = "SELECT a.id_area, a.nombre_area, a.precio_area, a.nombre_corto_area, a.descripcion_area, " +
                "c.id_categoria, c.codigo_categoria, ne.id_nivel, ne.codigo_nivel, ne.nombre_nivel_escolar " +
                "FROM categorias c " +
                "JOIN nivel_escolar_categorias nc ON nc.id_categoria = c.id_categoria " +
                "JOIN area a ON a.id_area = c.id_area " +
                "JOIN nivel_escolar ne ON nc.id_nivel = ne.id_nivel " +
                "WHERE nc.id_nivel = ?";

        return jdbcTemplate.query(sql, new Object[]{idNivel}, new RowMapper<AreaCategoriaNivelDTO>() {
            @Override
            public AreaCategoriaNivelDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                AreaCategoriaNivelDTO dto = new AreaCategoriaNivelDTO();
                dto.setIdArea(rs.getInt("id_area"));
                dto.setNombreArea(rs.getString("nombre_area"));
                dto.setPrecioArea(rs.getDouble("precio_area"));
                dto.setNombreCortoArea(rs.getString("nombre_corto_area"));
                dto.setDescripcionArea(rs.getString("descripcion_area"));
                dto.setIdCategoria(rs.getInt("id_categoria"));
                dto.setCodigoCategoria(rs.getString("codigo_categoria"));
                dto.setIdNivel(rs.getInt("id_nivel"));
                dto.setCodigoNivel(rs.getString("codigo_nivel"));
                dto.setNombreNivelEscolar(rs.getString("nombre_nivel_escolar"));
                return dto;
            }
        });
    }




}