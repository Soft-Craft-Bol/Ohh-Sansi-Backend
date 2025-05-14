package com.softcraft.ohhsansibackend.departamento.domain.repository.implementation;

import com.softcraft.ohhsansibackend.departamento.domain.models.Departamento;
import com.softcraft.ohhsansibackend.departamento.domain.repository.abstraction.IDepartamentoDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DepartamentoDomainRepository implements IDepartamentoDomainRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DepartamentoDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Departamento> saveAll(List<Departamento> departamentos) {
        String sql = "INSERT INTO departamento (id_departamento, nombre_departamento, nombre_corto) " +
                "VALUES (?, ?, ?) RETURNING *";
        List<Departamento> result = new ArrayList<>();

        for (Departamento departamento : departamentos) {
            List<Departamento> saved = jdbcTemplate.query(
                    sql,
                    new Object[]{
                            departamento.getIdDepartamento(),
                            departamento.getNombreDepartamento(),
                            departamento.getNombreCorto()
                    },
                    new BeanPropertyRowMapper<>(Departamento.class)
            );
            result.add(saved.get(0));
        }

        return result;
    }


    @Override
    public Departamento getDepartamentoById(int id) {
        String sql = "SELECT * FROM selectDepartamentoById(?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Departamento.class));
    }

    @Override
    public List<Departamento> getAllDepartamentos() {
        String sql = "SELECT * FROM selectAllDepartamentos()";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Departamento.class));
    }
}
