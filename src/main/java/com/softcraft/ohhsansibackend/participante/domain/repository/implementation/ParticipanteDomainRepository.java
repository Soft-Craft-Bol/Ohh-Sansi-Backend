package com.softcraft.ohhsansibackend.participante.domain.repository.implementation;

import com.softcraft.ohhsansibackend.exception.ResourceNotFoundException;
import com.softcraft.ohhsansibackend.mail.dto.ParticipanteReminderDto;
import com.softcraft.ohhsansibackend.participante.domain.dto.ParticipanteAreasDTO;
import com.softcraft.ohhsansibackend.participante.domain.dto.ParticipanteResumenDTO;
import com.softcraft.ohhsansibackend.participante.domain.dto.ParticipanteTutorAreaDTO;
import com.softcraft.ohhsansibackend.participante.domain.models.Participante;
import com.softcraft.ohhsansibackend.participante.domain.repository.abstraction.IParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;

@Repository
public class ParticipanteDomainRepository implements IParticipanteRepository {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public ParticipanteDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Participante save(Participante participante) {
        String sql = "INSERT INTO participante (id_inscripcion, " +
                "id_departamento, " +
                "id_municipio, " +
                "id_colegio, " +
                "id_grado, " +
                "participante_hash, " +
                "nombre_participante, " +
                "apellido_paterno, " +
                "apellido_materno, " +
                "fecha_nacimiento, " +
                "carnet_identidad_participante, " +
                "complemento_ci_participante, " +
                "email_participante, " +
                "tutor_requerido) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, participante.getIdInscripcion());
            ps.setInt(2, participante.getIdDepartamento());
            ps.setInt(3, participante.getIdMunicipio());
            ps.setInt(4, participante.getIdColegio());
            ps.setObject(5, participante.getIdGrado(), java.sql.Types.INTEGER);
            ps.setString(6, participante.getParticipanteHash());
            ps.setString(7, participante.getNombreParticipante());
            ps.setString(8, participante.getApellidoPaterno());
            ps.setString(9, participante.getApellidoMaterno());
            ps.setDate(10, new java.sql.Date(participante.getFechaNacimiento().getTime()));
            ps.setLong(11, participante.getCarnetIdentidadParticipante());
            ps.setString(12, participante.getComplementoCiParticipante());
            ps.setString(13, participante.getEmailParticipante());
            ps.setBoolean(14, participante.isTutorRequerido());
            return ps;
        }, keyHolder);
        Map<String, Object> keys = keyHolder.getKeys();
        if (keys != null) {
            participante.setIdParticipante((Integer) keys.get("id_participante"));
        }
        return participante;
    }

    @Override
    public Participante findById(Long idParticipante) {
        String sql = "SELECT * FROM participante WHERE id_participante = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{idParticipante}, new BeanPropertyRowMapper<>(Participante.class));
    }

    @Override
    public List<Participante> findAll() {
        String sql = "SELECT * FROM participante";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Participante.class));
    }

    @Override
    public Participante findByEmail(String email) {
        String sql = "SELECT * FROM participante WHERE email_participante = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{email}, new BeanPropertyRowMapper<>(Participante.class));
    }

    @Override
    public Participante findByCarnetIdentidad(int carnetIdentidad) {
        String sql = "SELECT * FROM participante WHERE carnet_identidad_participante = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{carnetIdentidad}, new BeanPropertyRowMapper<>(Participante.class));
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException("Participante no encontrado, ci incorrecto, introduce uno valido o registra un participante");
        } catch (IncorrectResultSizeDataAccessException ex) {
            throw new IllegalStateException("Participante no entontrado, ci incorrecto");
        }
    }

    @Override
    public boolean update(Participante participante) {
        String sql = "UPDATE participante SET id_departamento = ?, id_municipio = ?, id_colegio = ?, participante_hash = ?, apellido_paterno = ?, apellido_materno = ?, nombre_participante = ?, fecha_nacimiento = ?, correo_electronico_participante = ?, carnet_identidad_participante = ?, id_nivel = ? WHERE id_inscripcion = ? AND id_participante = ?";
        int rowsAffected = jdbcTemplate.update(sql,
                participante.getIdDepartamento(),
                participante.getIdMunicipio(),
                participante.getIdColegio(),
                participante.getParticipanteHash(),
                participante.getApellidoPaterno(),
                participante.getApellidoMaterno(),
                participante.getNombreParticipante(),
                participante.getFechaNacimiento(),
                participante.getEmailParticipante(),
                participante.getCarnetIdentidadParticipante(),
                participante.getIdGrado(),
                participante.getIdInscripcion(),
                participante.getIdParticipante()
        );
        return rowsAffected > 0;
    }

    @Override
    public boolean deleteParticipant(Long id) {
        String sql = "DELETE FROM participante WHERE id_participante = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }

    public Participante findParticipanteByIdInscripcion(int idInscripcion) {
        String sql = "SELECT p.* FROM participante p, inscripcion i WHERE p.id_inscripcion = ? AND i.inscripcion_masiva = false AND p.id_inscripcion = i.id_inscripcion";
        return jdbcTemplate.queryForObject(sql, new Object[]{idInscripcion}, new BeanPropertyRowMapper<>(Participante.class));
    }

    public int countParticipantesEnCatalogoParticipante(int idParticipante){
        String sql = "SELECT COUNT(participante_catalogo.id_participante) FROM participante_catalogo WHERE id_participante = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{idParticipante}, Integer.class);
        return count != null ? count : 0;
    }

    public Optional<ParticipanteResumenDTO> findParticipanteResumenByCi(int carnetIdentidad) {
        String sql = """
        SELECT 
            pr.nombre_participante,
            pr.apellido_paterno,
            pr.carnet_identidad_participante,
            pr.complemento_ci_participante,
            gr.nombre_grado
        FROM participante pr
        JOIN grado gr ON gr.id_grado = pr.id_grado
        WHERE pr.carnet_identidad_participante = ?
    """;

        try {
            ParticipanteResumenDTO dto = jdbcTemplate.queryForObject(
                    sql,
                    new Object[]{carnetIdentidad},
                    (rs, rowNum) -> {
                        ParticipanteResumenDTO result = new ParticipanteResumenDTO();
                        result.setNombreParticipante(rs.getString("nombre_participante"));
                        result.setApellidoPaterno(rs.getString("apellido_paterno"));
                        result.setCarnetIdentidadParticipante(rs.getInt("carnet_identidad_participante"));
                        result.setComplementoCiParticipante(rs.getString("complemento_ci_participante"));
                        result.setNombreGrado(rs.getString("nombre_grado"));
                        return result;
                    }
            );
            return Optional.of(dto);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }


    @Override
    public Optional<ParticipanteAreasDTO> findAreasByCarnetIdentidad(int carnetIdentidad) {
        String sql = """
        SELECT 
            par.id_participante,
            CONCAT(par.nombre_participante, ' ', par.apellido_paterno, 
                   CASE WHEN par.apellido_materno IS NOT NULL THEN ' ' || par.apellido_materno ELSE '' END) AS nombre_completo,
            par.carnet_identidad_participante,
            gr.nombre_grado,
            ARRAY_AGG(ar.id_area) AS ids_areas,
            ARRAY_AGG(ar.nombre_area) AS nombres_areas
        FROM 
            participante par
        JOIN 
            grado gr ON gr.id_grado = par.id_grado
        JOIN 
            participante_catalogo pc ON par.id_participante = pc.id_participante
        JOIN 
            area ar ON ar.id_area = pc.id_area
        JOIN 
            catalogo_olimpiada co ON co.id_area = ar.id_area
        WHERE 
            par.carnet_identidad_participante = ?
        GROUP BY
            par.id_participante,
            par.nombre_participante,
            par.apellido_paterno,
            par.apellido_materno,
            par.carnet_identidad_participante,
            gr.nombre_grado
        """;

        try {
            ParticipanteAreasDTO result = jdbcTemplate.queryForObject(
                    sql,
                    (rs, rowNum) -> {
                        ParticipanteAreasDTO dto = new ParticipanteAreasDTO();
                        dto.setIdParticipante(rs.getInt("id_participante"));
                        dto.setNombreCompleto(rs.getString("nombre_completo"));
                        dto.setCarnetIdentidad(rs.getInt("carnet_identidad_participante"));
                        dto.setGrado(rs.getString("nombre_grado"));

                        // Convertir arrays SQL a listas Java
                        Array idsArray = rs.getArray("ids_areas");
                        dto.setIdsAreas(idsArray != null ? Arrays.asList((Integer[]) idsArray.getArray()) : Collections.emptyList());

                        Array nombresArray = rs.getArray("nombres_areas");
                        dto.setNombresAreas(nombresArray != null ? Arrays.asList((String[]) nombresArray.getArray()) : Collections.emptyList());

                        return dto;
                    },
                    carnetIdentidad
            );
            return Optional.ofNullable(result);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<ParticipanteTutorAreaDTO> findParticipanteAreasTutoresById(int carnetIdentidad) {
        String sql = """
        WITH tutores_participante AS (
            SELECT
                pt.id_participante_tutor,
                pt.id_tutor,
                pt.id_participante,
                t.nombres_tutor,
                t.carnet_identidad_tutor,
                t.apellidos_tutor,
                t.email_tutor,
                t.telefono
            FROM
                participante_tutor pt
            JOIN
                tutor t ON pt.id_tutor = t.id_tutor AND t.id_tipo_tutor = 1
            JOIN participante p on p.id_participante = pt.id_participante
            WHERE
                p.carnet_identidad_participante = ?
        )
        SELECT
            p.id_participante,
            CONCAT(p.nombre_participante, ' ', p.apellido_paterno,
                   COALESCE(' ' || p.apellido_materno, '')) AS nombre_completo,
            p.carnet_identidad_participante,
            p.id_inscripcion,
            g.nombre_grado,
            a.id_area,
            a.nombre_area,
            tp.id_tutor,
            tp.nombres_tutor,
            tp.carnet_identidad_tutor,
            tp.apellidos_tutor,
            tp.email_tutor,
            tp.telefono
        FROM
            participante p
        JOIN
            grado g ON g.id_grado = p.id_grado
        JOIN
            participante_catalogo pc ON p.id_participante = pc.id_participante
        JOIN
            area a ON a.id_area = pc.id_area
        LEFT JOIN
            tutor_area_participante tap ON tap.id_area = a.id_area
            AND EXISTS (
                SELECT 1 FROM tutores_participante tp
                WHERE tp.id_participante_tutor = tap.id_participante_tutor
            )
        LEFT JOIN
            tutores_participante tp ON tp.id_participante_tutor = tap.id_participante_tutor
        WHERE
            p.carnet_identidad_participante = ?
        ORDER BY
            a.nombre_area;
    """;

        try {
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, carnetIdentidad, carnetIdentidad);

            if (rows.isEmpty()) {
                return Optional.empty();
            }

            ParticipanteTutorAreaDTO dto = new ParticipanteTutorAreaDTO();
            List<Integer> idsAreas = new ArrayList<>();
            List<String> nombresAreas = new ArrayList<>();
            List<ParticipanteTutorAreaDTO.AreaTutorDTO> areasTutores = new ArrayList<>();

            boolean initialized = false;
            for (Map<String, Object> row : rows) {
                if (!initialized) {
                    dto.setIdParticipante((Integer) row.get("id_participante"));
                    dto.setIdInscripcion((Integer) row.get("id_inscripcion"));
                    dto.setNombreCompleto((String) row.get("nombre_completo"));

                    // Manejo seguro del carnet de identidad
                    Object ciObj = row.get("carnet_identidad_participante");
                    if (ciObj instanceof Number) {
                        dto.setCarnetIdentidad(((Number) ciObj).longValue());
                    } else if (ciObj != null) {
                        dto.setCarnetIdentidad(Long.parseLong(ciObj.toString()));
                    }

                    dto.setGrado((String) row.get("nombre_grado"));
                    initialized = true;
                }

                idsAreas.add((Integer) row.get("id_area"));
                nombresAreas.add((String) row.get("nombre_area"));

                ParticipanteTutorAreaDTO.AreaTutorDTO areaTutor = new ParticipanteTutorAreaDTO.AreaTutorDTO();
                areaTutor.setIdArea((Integer) row.get("id_area"));
                areaTutor.setNombreArea((String) row.get("nombre_area"));
                areaTutor.setCarnetIdentidadTutor(
                        row.get("carnet_identidad_tutor") != null ?
                                ((Number) row.get("carnet_identidad_tutor")).longValue() :
                                null
                );
                areaTutor.setIdTutor(row.get("id_tutor") != null ? (Integer) row.get("id_tutor") : null);
                areaTutor.setNombresTutor((String) row.get("nombres_tutor"));
                areaTutor.setApellidosTutor((String) row.get("apellidos_tutor"));
                areaTutor.setEmailTutor((String) row.get("email_tutor"));

                // Manejo seguro del teléfono
                Object telefonoObj = row.get("telefono");
                if (telefonoObj != null) {
                    areaTutor.setTelefono(telefonoObj.toString());
                } else {
                    areaTutor.setTelefono(null);
                }

                areasTutores.add(areaTutor);
            }

            dto.setIdsAreas(idsAreas);
            dto.setNombresAreas(nombresAreas);
            dto.setAreasTutores(areasTutores);

            return Optional.of(dto);

        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public List<ParticipanteReminderDto> findParticipantesSinPagoProximoVencimiento(int diasAnticipacion) {
        String sql = """
            SELECT
                p.email_participante,
                CONCAT(p.nombre_participante, ' ', p.apellido_paterno,
                       CASE WHEN p.apellido_materno IS NOT NULL THEN ' ' || p.apellido_materno ELSE '' END) AS nombre_completo,
                i.codigo_unico_inscripcion,
                po.tipo_periodo,
                po.fecha_fin,
                CAST(po.fecha_fin - CURRENT_DATE AS INTEGER) AS dias_restantes,
                o.nombre_olimpiada
            FROM participante p
                JOIN inscripcion i ON p.id_inscripcion = i.id_inscripcion
                JOIN participante_catalogo pc ON p.id_inscripcion = pc.id_inscripcion AND p.id_participante = pc.id_participante
                JOIN catalogo_olimpiada co ON pc.id_categoria = co.id_categoria
                    AND pc.id_area = co.id_area
                    AND pc.id_catalogo = co.id_catalogo
                    AND pc.id_olimpiada = co.id_olimpiada
                JOIN olimpiada o ON co.id_olimpiada = o.id_olimpiada
                JOIN periodos_olimpiada po ON o.id_olimpiada = po.id_olimpiada
                    AND po.id_estado = 1
                    AND po.fecha_fin >= CURRENT_DATE
                    AND po.fecha_fin <= CURRENT_DATE + (? * INTERVAL '1 day')
                LEFT JOIN orden_de_pago odp ON i.id_inscripcion = odp.id_inscripcion
                LEFT JOIN estado_orden_de_pago eop ON odp.id_estado = eop.id_estado
            WHERE (odp.id_orden_pago IS NULL OR eop.estado != 'PAGADO')
            ORDER BY po.fecha_fin ASC, p.email_participante
            """;

        return jdbcTemplate.query(sql, new Object[]{diasAnticipacion}, (rs, rowNum) ->
                new ParticipanteReminderDto(
                        rs.getString("email_participante"),
                        rs.getString("nombre_completo"),
                        rs.getString("codigo_unico_inscripcion"),
                        rs.getString("tipo_periodo"),
                        rs.getDate("fecha_fin"),
                        rs.getInt("dias_restantes")
                )
        );
    }

    public List<ParticipanteReminderDto> findParticipantesSinPagoPorTipoPeriodo(String tipoPeriodo) {
        String sql = """
        SELECT 
            p.email_participante,
            CONCAT(p.nombre_participante, ' ', p.apellido_paterno, 
                   CASE WHEN p.apellido_materno IS NOT NULL THEN ' ' || p.apellido_materno ELSE '' END) AS nombre_completo,
            i.codigo_unico_inscripcion,
            po.tipo_periodo,
            po.fecha_fin,
            CAST(po.fecha_fin - CURRENT_DATE AS INTEGER) AS dias_restantes,
            o.nombre_olimpiada
        FROM participante p
        JOIN inscripcion i ON p.id_inscripcion = i.id_inscripcion
        JOIN participante_catalogo pc ON p.id_inscripcion = pc.id_inscripcion AND p.id_participante = pc.id_participante
        JOIN catalogo_olimpiada co ON pc.id_categoria = co.id_categoria
                                  AND pc.id_area = co.id_area
                                  AND pc.id_catalogo = co.id_catalogo
                                  AND pc.id_olimpiada = co.id_olimpiada
        JOIN olimpiada o ON co.id_olimpiada = o.id_olimpiada
        JOIN periodos_olimpiada po ON o.id_olimpiada = po.id_olimpiada
        LEFT JOIN orden_de_pago odp ON i.id_inscripcion = odp.id_inscripcion
        LEFT JOIN estado_orden_de_pago eop ON odp.id_estado = eop.id_estado
        WHERE (odp.id_orden_pago IS NULL OR eop.estado != 'PAGADO')
        AND po.tipo_periodo = ?
        AND po.fecha_fin >= CURRENT_DATE
        AND po.id_estado = 1
        ORDER BY po.fecha_fin ASC, p.email_participante
        """;

        return jdbcTemplate.query(sql, new Object[]{tipoPeriodo}, (rs, rowNum) ->
                new ParticipanteReminderDto(
                        rs.getString("email_participante"),
                        rs.getString("nombre_completo"),
                        rs.getString("codigo_unico_inscripcion"),
                        rs.getString("tipo_periodo"),
                        rs.getDate("fecha_fin"),
                        rs.getInt("dias_restantes")
                )
        );
    }



}