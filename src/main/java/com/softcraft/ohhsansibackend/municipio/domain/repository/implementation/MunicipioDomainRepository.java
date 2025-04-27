package com.softcraft.ohhsansibackend.municipio.domain.repository.implementation;

import com.softcraft.ohhsansibackend.municipio.domain.models.Municipio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MunicipioDomainRepository implements com.softcraft.ohhsansibackend.municipio.domain.repository.abstraction.IMunicipioDomainRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MunicipioDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Municipio> saveAll(List<Municipio> municipios) {
        String sql = "INSERT INTO municipio (id_municipio, nombre_municipio, id_departamento, numero_colegios) " +
                "VALUES (?, ?, ?, ?) RETURNING *";

        List<Municipio> result = new ArrayList<>();

        for (Municipio municipio : municipios) {
            List<Municipio> saved = jdbcTemplate.query(
                    sql,
                    new Object[]{
                            municipio.getIdMunicipio(),
                            municipio.getNombreMunicipio(),
                            municipio.getIdDepartamento(),
                            municipio.getNumeroColegios()
                    },
                    new BeanPropertyRowMapper<>(Municipio.class)
            );
            result.add(saved.get(0));
        }

        return result;
    }


    @Override
    public Municipio getMunicipioById(int id) {
        String sql = "SELECT * FROM selectMunicipioById(?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Municipio.class));
    }

    @Override
    public List<Municipio> getAllMunicipios() {
        String sql = "SELECT * FROM municipio";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Municipio.class));
    }

    @Override
    public List<Municipio> getMunicipiosByDepartamento(int idDepartamento) {
        String sql = "SELECT * FROM selectMunicipioByDepartamento(?)";
        return jdbcTemplate.query(sql, new Object[]{idDepartamento}, new BeanPropertyRowMapper<>(Municipio.class));
    }
}
