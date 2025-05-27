package com.softcraft.ohhsansibackend.periodosolimpiada.domain.repository.implementation;

import com.softcraft.ohhsansibackend.periodosolimpiada.domain.models.PeriodoOlimpiada;
import com.softcraft.ohhsansibackend.periodosolimpiada.domain.repository.abstraction.IPeriodoOlimpiadaRepository;
import com.softcraft.ohhsansibackend.periodosolimpiada.infraestructure.dto.EventoDTO;
import com.softcraft.ohhsansibackend.periodosolimpiada.infraestructure.dto.OlimpiadaEventosDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PeriodoOlimpiadaDomainRepository implements IPeriodoOlimpiadaRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PeriodoOlimpiadaDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public PeriodoOlimpiada insertPeriodoOlimpiada(PeriodoOlimpiada periodoOlimpiada) {
        String sql = "SELECT * FROM crear_periodo(?, ?, ?, ?, ?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{
                periodoOlimpiada.getIdOlimpiada(),
                periodoOlimpiada.getTipoPeriodo(),
                periodoOlimpiada.getFechaInicio(),
                periodoOlimpiada.getFechaFin(),
                periodoOlimpiada.getNombrePeriodo()
        }, (rs, rowNum) -> {
            PeriodoOlimpiada po = new PeriodoOlimpiada();
            po.setIdPeriodo(rs.getInt("id_periodo"));
            po.setIdOlimpiada(rs.getInt("id_olimpiada"));
            po.setTipoPeriodo(rs.getString("tipo_periodo"));
            po.setNombrePeriodo(rs.getString("nombre_periodo"));
            po.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
            po.setFechaFin(rs.getDate("fecha_fin").toLocalDate());
            return po;
        });
    }

    @Override
    public List<OlimpiadaEventosDTO> findAllOlimpiadasEventos() {
        String sql = "SELECT * FROM select_all_periodos_olimpiadas()";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        Map<String, OlimpiadaEventosDTO> olimpiadasMap = new LinkedHashMap<>();

        for (Map<String, Object> row : rows) {
            int idOlimpiada = (Integer) row.get("id_olimpiada");
            String nombreOlimpiada = (String) row.get("nombre_olimpiada");
            String estadoActual = (String) row.get("estado_actual");
            int anio = extractYearFromOlympiadName(nombreOlimpiada);

            EventoDTO evento = createEventoDTOFromRow(row);

            String key = idOlimpiada + "-" + nombreOlimpiada;

            olimpiadasMap.computeIfAbsent(key, k -> new OlimpiadaEventosDTO(
                    idOlimpiada,
                    anio,
                    nombreOlimpiada,
                    estadoActual,
                    new ArrayList<>()
            )).getEventos().add(evento);
        }

        return new ArrayList<>(olimpiadasMap.values());
    }

    private int extractYearFromOlympiadName(String nombreOlimpiada) {
        try {
            return Integer.parseInt(nombreOlimpiada.replaceAll("\\D+", ""));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private EventoDTO createEventoDTOFromRow(Map<String, Object> row) {
        EventoDTO evento = new EventoDTO();
        evento.setIdOlimpiada((Integer) row.get("id_olimpiada"));
        evento.setIdPeriodo((Integer) row.get("id_periodo"));
        evento.setNombrePeriodo((String) row.get("nombre_periodo"));
        evento.setTipoPeriodo((String) row.get("tipo_periodo"));

        evento.setFechaInicio((Date)row.get("fecha_inicio"));
        evento.setFechaFin((Date)(row.get("fecha_fin")));

        evento.setEstadoActual((String) row.get("estado_actual"));

        return evento;
    }

    public PeriodoOlimpiada encontrarPeriodoInscripcionActual() {
        String sql = """
                select distinct po.*
                from olimpiada o, estado_olimpiada eo, periodos_olimpiada po
                where po.id_estado = eo.id_estado
                  and eo.nombre_estado = 'EN INSCRIPCION'
                  and CURRENT_DATE between DATE(po.fecha_inicio) and DATE(po.fecha_fin);
            """;
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(PeriodoOlimpiada.class));
    }


    public PeriodoOlimpiada actualizarPeriodo(int idPeriodo, int idOlimpiada,
                                              LocalDate fechaInicio, LocalDate fechaFin,
                                              String nombrePeriodo) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM update_periodo_olimpiada(?, ?, ?, ?, ?)",
                    new Object[]{idPeriodo, idOlimpiada, fechaInicio, fechaFin, nombrePeriodo},
                    (rs, rowNum) -> {
                        PeriodoOlimpiada p = new PeriodoOlimpiada();
                        p.setIdPeriodo(rs.getInt("id_periodo"));
                        p.setIdOlimpiada(rs.getInt("id_olimpiada"));
                        p.setNombrePeriodo(rs.getString("nombre_periodo"));
                        p.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
                        p.setFechaFin(rs.getDate("fecha_fin").toLocalDate());
                        p.setTipoPeriodo(rs.getString("tipo_periodo"));
                        p.setIdEstado(rs.getInt("id_estado"));
                        return p;
                    }
            );
        } catch (DataAccessException e) {
            throw new RuntimeException("Error al actualizar período: " + e.getMostSpecificCause().getMessage());
        }
    }

    public PeriodoOlimpiada cerrarPeriodo(int idPeriodo, int idOlimpiada, String motivo) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM cerrar_periodo_manual(?, ?, ?)",
                    new Object[]{idPeriodo, idOlimpiada, motivo},
                    (rs, rowNum) -> {
                        PeriodoOlimpiada p = new PeriodoOlimpiada();
                        p.setIdPeriodo(rs.getInt("id_periodo"));
                        p.setIdOlimpiada(rs.getInt("id_olimpiada"));
                        p.setIdEstado(rs.getInt("id_estado"));
                        return p;
                    }
            );
        } catch (DataAccessException e) {
            throw new RuntimeException("Error al cerrar período: " + e.getMostSpecificCause().getMessage());
        }
    }

    public void verificarYActualizarEstados() {
        jdbcTemplate.update("UPDATE periodos_olimpiada SET id_estado = CASE " +
                "WHEN CURRENT_DATE < fecha_inicio THEN 1 " +
                "WHEN CURRENT_DATE BETWEEN fecha_inicio AND fecha_fin THEN 2 " +
                "ELSE 4 END " +
                "WHERE id_estado NOT IN (3, 5)");

        jdbcTemplate.update("SELECT actualizar_estado_olimpiada_por_periodos(id_olimpiada) FROM olimpiada");
    }
}
