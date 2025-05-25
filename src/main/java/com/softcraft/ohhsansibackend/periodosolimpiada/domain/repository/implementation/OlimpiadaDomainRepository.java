package com.softcraft.ohhsansibackend.periodosolimpiada.domain.repository.implementation;

import com.softcraft.ohhsansibackend.periodosolimpiada.domain.models.Olimpiada;
import com.softcraft.ohhsansibackend.periodosolimpiada.domain.repository.abstraction.IOlimpiadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class OlimpiadaDomainRepository implements IOlimpiadaRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OlimpiadaDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Olimpiada saveOlimpiada(Olimpiada olimpiada) {
        String sql = "SELECT * FROM crear_olimpiada(?, ?, ?, ?, ?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{
                olimpiada.getAnio(),
                olimpiada.getNombreOlimpiada(),
                olimpiada.getPrecioOlimpiada(),
                olimpiada.getFechaInicio(),
                olimpiada.getFechaFin()
        }, new BeanPropertyRowMapper<>(Olimpiada.class));
    }

    @Override
    public Olimpiada updateOlimpiada(Olimpiada olimpiada) {
        String sql = "SELECT * FROM actualizar_olimpiada(?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{
                olimpiada.getIdOlimpiada(),
                olimpiada.getAnio(),
                olimpiada.getNombreOlimpiada(),
                olimpiada.getPrecioOlimpiada(),
                olimpiada.getFechaInicio(),
                olimpiada.getFechaFin()
        }, new BeanPropertyRowMapper<>(Olimpiada.class));
    }

    @Override
    public List<Olimpiada> getOlimpiadas() {
        String sql = "SELECT\n" +
                "            o.id_olimpiada,\n" +
                "            o.anio,\n" +
                "            o.nombre_olimpiada,\n" +
                "            e.nombre_estado,\n" +
                "            o.precio_olimpiada\n" +
                "        FROM\n" +
                "            olimpiada o\n" +
                "                JOIN\n" +
                "            estado_olimpiada e ON o.id_estado = e.id_estado;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Olimpiada.class));
    }

    @Override
    public Optional<Olimpiada> findById(Integer idOlimpiada) {
        String sql = "SELECT * FROM olimpiada WHERE id_olimpiada = ?";
        try {
            Olimpiada olimpiada = jdbcTemplate.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<>(Olimpiada.class),
                    idOlimpiada
            );
            return Optional.ofNullable(olimpiada);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
    public Olimpiada findOlimpiadaById(int idOlimpiada){
        String sql = "select * from olimpiada where id_olimpiada = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Olimpiada.class), idOlimpiada);
    }
}
