package com.softcraft.ohhsansibackend.inscripcion.domain.repository.implementation;

import com.softcraft.ohhsansibackend.exception.ResourceNotFoundException;
import com.softcraft.ohhsansibackend.inscripcion.domain.models.Inscripcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;


@Repository
public class InscripcionDomainRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public InscripcionDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Inscripcion saveInscripcion(Inscripcion inscripcion) {
        String sql = "INSERT INTO inscripcion (fecha_inscripcion, hora_inscripcion, codigo_unico_inscripcion) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, inscripcion.getFechaInscripcion());
            ps.setTime(2, inscripcion.getHoraInscripcion());
            ps.setString(3, inscripcion.getCodigoUnicoInscripcion());
            return ps;
        }, keyHolder);
        Map<String, Object> keys = keyHolder.getKeys();
        if (keys != null) {
            inscripcion.setIdInscripcion((Integer) keys.get("id_inscripcion"));
        }
        return inscripcion;
    }

    public int updateCodigoUnicoInscripcion(Inscripcion inscripcion) {
        String sql = "UPDATE inscripcion SET codigo_unico_inscripcion = ? WHERE id_inscripcion = ?";
        return jdbcTemplate.update(sql, inscripcion.getCodigoUnicoInscripcion(), inscripcion.getIdInscripcion());
    }


    public Inscripcion findByIdInscripcion(int idInscripcion) {
        String sql = "SELECT * FROM inscripcion WHERE id_inscripcion = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{idInscripcion},
                    new BeanPropertyRowMapper<>(Inscripcion.class));
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Inscripcion con ID " + idInscripcion + " no encontrada");
        }
    }


    public List <Inscripcion>findAllInscripcion() {
        String sql = "SELECT * FROM inscripcion";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Inscripcion.class));
    }

    public Map<String, Object> getDetalleInscripcion(String codigoUnico) {
        String sql = "SELECT * FROM obtener_detalle_inscripcion(?)";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{codigoUnico}, new ColumnMapRowMapper());
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Inscripción no encontrada");
        }
    }

    public String findCodigoUnicoByIdInscripcion(int idInscripcion) {
        String sql = "SELECT codigo_unico_inscripcion FROM inscripcion WHERE id_inscripcion = ?";
        try {
            return jdbcTemplate.queryForObject(sql, String.class, idInscripcion);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    public Long findIdByCodigoUnico(String codigoUnicoInscripcion) {
        String sql = "SELECT id_inscripcion FROM inscripcion WHERE codigo_unico_inscripcion = ?";
        try {
            return jdbcTemplate.queryForObject(sql, Long.class, codigoUnicoInscripcion);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    public List<Map<String, Object>> findInscripcionById(int idInscripcion) {
        String sql = "SELECT * FROM inscripcion WHERE id_inscripcion = ?";
        return jdbcTemplate.queryForList(sql, idInscripcion);
    }
    //participante
    public List<Map<String, Object>> findParticipantesByInscripcionId(int idInscripcion) {
        String sql = "SELECT * FROM participante WHERE id_inscripcion = ?";
        return jdbcTemplate.queryForList(sql, idInscripcion);
    }

    public List<Map<String, Object>> findInscripcionAreasByInscripcionId(int idInscripcion) {
        String sql = "SELECT * FROM inscripcion_area WHERE id_inscripcion = ?";
        return jdbcTemplate.queryForList(sql, idInscripcion);
    }

    public List<Map<String, Object>> findAreasByInscripcionId(int idInscripcion) {
        String sql = "SELECT a.nombre_area, a.nombre_corto_area, a.descripcion_area " +
                "FROM participante_catalogo pc, area a " +
                "WHERE pc.id_inscripcion = ? AND pc.id_area = a.id_area";
        return jdbcTemplate.queryForList(sql, idInscripcion);
    }

    public List<Map<String, Object>> findTutoresByInscripcionId(int idInscripcion) {
        String sql = "SELECT t.email_tutor, t.nombres_tutor, t.apellidos_tutor, t.telefono, " +
                "t.carnet_identidad_tutor, tt.nombre_tipo_tutor " +
                "FROM tutor t, participante_tutor pt, participante p, tipo_tutor tt " +
                "WHERE t.id_tutor = pt.id_tutor AND p.id_participante = pt.id_participante " +
                "AND pt.id_inscripcion = ? AND tt.id_tipo_tutor = t.id_tipo_tutor";
        return jdbcTemplate.queryForList(sql, idInscripcion);
    }

    public List<Map<String, Object>> findOlimapiada() {
        String sql =
                """
                    select o.*
                    from olimpiada o, estado_olimpiada eo
                    where o.id_estado = eo.id_estado and eo.nombre_estado='INSCRIPCION';
                """
                ;
        return jdbcTemplate.queryForList(sql);
    }
    public boolean deleteInscripcionById(int idInscripcion) {
        String sql = "DELETE FROM inscripcion WHERE id_inscripcion = ?";
        int rowsAffected = jdbcTemplate.update(sql, idInscripcion);
        return rowsAffected > 0;
    }

    public List<Map<String, Object>> getReporteInscripcionByArea(Integer idArea, int idOlimpiada) {
        String sql = """
                SELECT DISTINCT
                    p.apellido_paterno,
                    p.apellido_materno,
                    p.nombre_participante,
                    p.id_inscripcion,
                    c.nombre_colegio,
                    m.nombre_municipio,
                    d.nombre_departamento,
                    g.nombre_grado,
                    a.nombre_area  
                FROM orden_de_pago op
                         JOIN estado_orden_de_pago eop ON eop.id_estado = op.id_estado
                         JOIN inscripcion i ON op.id_inscripcion = i.id_inscripcion
                         JOIN participante p ON p.id_inscripcion = i.id_inscripcion
                         JOIN participante_catalogo pc ON p.id_participante = pc.id_participante
                         JOIN catalogo_olimpiada co ON pc.id_catalogo = co.id_catalogo
                         JOIN olimpiada o ON co.id_olimpiada = o.id_olimpiada 
                         JOIN area a ON co.id_area = a.id_area 
                         JOIN colegio c ON p.id_colegio = c.id_colegio
                         JOIN municipio m ON c.id_municipio = m.id_municipio
                         JOIN departamento d ON m.id_departamento = d.id_departamento
                         JOIN grado g ON g.id_grado = p.id_grado
                WHERE eop.estado = 'PAGADO'
                  AND o.id_olimpiada = ?
                  {0}
                ORDER BY g.nombre_grado, a.nombre_area, p.apellido_paterno;
            """;

        String areaFilter = (idArea != null) ? "AND a.id_area = ?" : "";
        sql = sql.replace("{0}", areaFilter);

        return (idArea != null)
                ? jdbcTemplate.queryForList(sql, idOlimpiada, idArea)
                : jdbcTemplate.queryForList(sql, idOlimpiada);
    }
}
