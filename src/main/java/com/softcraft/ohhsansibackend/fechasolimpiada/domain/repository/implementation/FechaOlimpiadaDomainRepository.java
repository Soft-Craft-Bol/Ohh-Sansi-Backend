package com.softcraft.ohhsansibackend.fechasolimpiada.domain.repository.implementation;

import com.softcraft.ohhsansibackend.fechasolimpiada.domain.models.FechaOlimpiada;
import com.softcraft.ohhsansibackend.fechasolimpiada.domain.repository.abstraction.IFechaOlimpiadaRepository;
import com.softcraft.ohhsansibackend.fechasolimpiada.infraestructure.dto.EventoDTO;
import com.softcraft.ohhsansibackend.fechasolimpiada.infraestructure.dto.OlimpiadaEventosDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FechaOlimpiadaDomainRepository implements IFechaOlimpiadaRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FechaOlimpiadaDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public FechaOlimpiada upsertFechaOlimpiada(FechaOlimpiada fechaOlimpiada) {
        String sql = "SELECT * FROM upsertFechasOlimpiadas(?, ?, ?, ?, ?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{
                fechaOlimpiada.getIdOlimpiada(),
                fechaOlimpiada.getNombreEvento(),
                fechaOlimpiada.getFechaInicio(),
                fechaOlimpiada.getFechaFin(),
                fechaOlimpiada.getEsPublica()
        }, new BeanPropertyRowMapper<>(FechaOlimpiada.class));
    }

    @Override
    public boolean deleteFechaOlimpiada(int id) {
        String sql = "SELECT deleteFechaOlimpiada(?)";
        Boolean response = jdbcTemplate.queryForObject(sql, Boolean.class, id);
        return response != null && response;
    }

    @Override
    public List<FechaOlimpiada> getFechaOlimpiada() {
        String sql = "SELECT * FROM selectAllFechasOlimpiadas()";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(FechaOlimpiada.class));
    }

    @Override
    public FechaOlimpiada getFechaOlimpiada(int id) {
        String sql = "SELECT * FROM selectFechaOlimpiadaById(?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{id},
                new BeanPropertyRowMapper<>(FechaOlimpiada.class));
    }

    @Override
    public FechaOlimpiada getFechaOlimpiadaPublic() {
        String sql = "SELECT * FROM selectFechaOlimpiadaPublic()";
        return jdbcTemplate.queryForObject(sql,
                new BeanPropertyRowMapper<>(FechaOlimpiada.class));
    }

    @Override
    public List<OlimpiadaEventosDTO> findAllOlimpiadasEventos() {
        String sql = "SELECT * FROM selectAllFechasOlimpiadas()";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        Map<String, OlimpiadaEventosDTO> olimpiadasMap = new LinkedHashMap<>();

        for (Map<String, Object> row : rows) {
            int idOlimpiada = (Integer) row.get("id_olimpiada");
            String nombreOlimpiada = (String) row.get("nombre_olimpiada");
            boolean estadoOlimpiada = (Boolean) row.get("estado_olimpiada");

            int anio = Integer.parseInt(nombreOlimpiada.replaceAll("\\D+", ""));

            EventoDTO evento = new EventoDTO();
            evento.setIdFechaOlimpiada((Integer) row.get("id_fecha_olimpiada"));
            evento.setNombreEvento((String) row.get("nombre_evento"));
            Date fechaInicioSql = (Date) row.get("fecha_inicio");
            evento.setFechaInicio(fechaInicioSql != null ? fechaInicioSql.toLocalDate() : null);

            Date fechaFinSql = (Date) row.get("fecha_fin");
            evento.setFechaFin(fechaFinSql != null ? fechaFinSql.toLocalDate() : null);

            evento.setEsPublica((Boolean) row.get("es_publica"));

            String key = anio + "-" + estadoOlimpiada;

            if (!olimpiadasMap.containsKey(key)) {
                olimpiadasMap.put(key, new OlimpiadaEventosDTO(idOlimpiada, anio, nombreOlimpiada, estadoOlimpiada, new ArrayList<>()));
            }

            olimpiadasMap.get(key).getEventos().add(evento);
        }

        return new ArrayList<>(olimpiadasMap.values());
    }
}
