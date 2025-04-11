package com.softcraft.ohhsansibackend.colegio.domain.repository.implementation;

import com.softcraft.ohhsansibackend.colegio.domain.models.Colegio;
import com.softcraft.ohhsansibackend.colegio.domain.repository.abstraction.IColegioDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ColegioDomainRepository implements IColegioDomainRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ColegioDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Colegio> saveAll(List<Colegio> colegios) {
        String sql = "INSERT INTO colegio (id_colegio, nombre_colegio, direccion, coordenadas, cantidad_estudiantes_colegio, id_municipio, id_departamento) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING *";

        List<Colegio> result = new ArrayList<>();

        for (Colegio colegio : colegios) {
            List<Colegio> saved = jdbcTemplate.query(
                    sql,
                    new Object[]{
                            colegio.getIdColegio(),
                            colegio.getNombreColegio(),
                            colegio.getDireccion(),
                            colegio.getCoordenadas(),
                            colegio.getCantidadEstudiantesColegio(),
                            colegio.getIdMunicipio(),
                            colegio.getIdDepartamento()
                    },
                    new BeanPropertyRowMapper<>(Colegio.class)
            );
            result.add(saved.get(0));
        }

        return result;
    }

    @Override
    public List<Colegio> getColegios() {
        String sql = "SELECT * FROM selectAllColegios()";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Colegio.class));
    }
    @Override
    public List<Colegio> getColegiosByMunicipio(int idMunicipio) {
        String sql = "SELECT * FROM selectColegioByMunicipio(?)";
        return jdbcTemplate.query(sql, new Object[]{idMunicipio}, new BeanPropertyRowMapper<>(Colegio.class));
    }
}
