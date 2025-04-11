package com.softcraft.ohhsansibackend.grado.domain.repository.implementation;

import com.softcraft.ohhsansibackend.grado.domain.models.GradoCategoria;
import com.softcraft.ohhsansibackend.grado.domain.repository.abstraction.IGradoCategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class GradoCategoriaDomainRepository implements IGradoCategoriaRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GradoCategoriaDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public GradoCategoria save(GradoCategoria gradoCategoria) {
        String sql = "INSERT INTO grado_categoria (id_grado, id_categoria) VALUES (?, ?) RETURNING id_grado_categoria";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[] {"id_grado_categoria"});
            ps.setInt(1, gradoCategoria.getIdGrado());
            ps.setInt(3, gradoCategoria.getIdCategoria());
            return ps;
        }, keyHolder);

        int newId = keyHolder.getKey().intValue();
        gradoCategoria.setIdGradoCategoria(newId);
        return gradoCategoria;
    }

    @Override
    public boolean update(GradoCategoria gradoCategoria) {
        String sql = "SELECT UpdateGradeCategoria(?, ?, ?)";
        Boolean rowsAffected = jdbcTemplate.queryForObject(sql, Boolean.class, gradoCategoria.getIdGradoCategoria(), gradoCategoria.getIdGrado(), gradoCategoria.getIdCategoria());
        return rowsAffected;
    }

    @Override
    public boolean delete(int idGradoCategoria) {
        String sql = "SELECT DeleteGradesCategorias(?)";
        Boolean rowsAffected = jdbcTemplate.queryForObject(sql, Boolean.class, idGradoCategoria);
        return rowsAffected;
    }

    @Override
    public GradoCategoria findById(int idGradoCategoria) {
        String sql = "SELECT * FROM SelectGradesCategoriasById(?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{idGradoCategoria}, new
                BeanPropertyRowMapper<>(GradoCategoria.class));
    }

    @Override
    public List<GradoCategoria> findAll() {
        String sql = "SELECT * FROM SelectAllGradesCategorias()";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(GradoCategoria.class));
    }

}