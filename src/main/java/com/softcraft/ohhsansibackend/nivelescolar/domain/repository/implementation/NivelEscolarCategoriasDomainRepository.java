package com.softcraft.ohhsansibackend.nivelescolar.domain.repository.implementation;

import com.softcraft.ohhsansibackend.nivelescolar.domain.models.NivelEscolarCategorias;
import com.softcraft.ohhsansibackend.nivelescolar.domain.repository.abstraction.INivelEscolarCategoriasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class NivelEscolarCategoriasDomainRepository implements INivelEscolarCategoriasRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public NivelEscolarCategoriasDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public NivelEscolarCategorias save(NivelEscolarCategorias nivelEscolarCategorias) {
        String sql = "INSERT INTO nivel_escolar_categorias (id_nivel, id_area, id_categoria) VALUES (?, ?, ?) RETURNING id_nivel_escolar_categorias";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[] {"id_nivel_escolar_categorias"});
            ps.setInt(1, nivelEscolarCategorias.getIdNivel());
            ps.setInt(2, nivelEscolarCategorias.getIdArea());
            ps.setInt(3, nivelEscolarCategorias.getIdCategoria());
            return ps;
        }, keyHolder);

        int newId = keyHolder.getKey().intValue();
        nivelEscolarCategorias.setIdNivelEscolarCategorias(newId);
        return nivelEscolarCategorias;
    }
    public NivelEscolarCategorias saveNivelEscolarCategorias(NivelEscolarCategorias nivelEscolarCategorias) {
        String sql = "INSERT INTO nivel_escolar_categoria (id_nivel, id_area, id_categoria) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, nivelEscolarCategorias.getIdNivel(), nivelEscolarCategorias.getIdArea(), nivelEscolarCategorias.getIdCategoria());
        return nivelEscolarCategorias;
    }

    @Override
    public boolean update(NivelEscolarCategorias nivelEscolarCategorias) {
        String sql = "SELECT UpdateNivelEscolarCategorias(?, ?, ?)";
        Boolean rowsAffected = jdbcTemplate.queryForObject(sql, Boolean.class, nivelEscolarCategorias.getIdNivelEscolarCategorias(), nivelEscolarCategorias.getIdNivel(), nivelEscolarCategorias.getIdCategoria());
        return rowsAffected;
    }

    @Override
    public boolean delete(int idNivelEscolarCategorias) {
        String sql = "SELECT DeleteNivelEscolarCategorias(?)";
        Boolean rowsAffected = jdbcTemplate.queryForObject(sql, Boolean.class, idNivelEscolarCategorias);
        return rowsAffected;
    }

    @Override
    public NivelEscolarCategorias findById(int idNivelEscolarCategorias) {
        String sql = "SELECT * FROM SelectNivelEscolarCategoriasById(?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{idNivelEscolarCategorias}, new
                BeanPropertyRowMapper<>(NivelEscolarCategorias.class));
    }

    @Override
    public List<NivelEscolarCategorias> findAll() {
        String sql = "SELECT * FROM SelectAllNivelEscolarCategorias()";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(NivelEscolarCategorias.class));
    }

}