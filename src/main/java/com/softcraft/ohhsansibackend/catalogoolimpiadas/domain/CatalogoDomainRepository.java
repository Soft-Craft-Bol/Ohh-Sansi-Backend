package com.softcraft.ohhsansibackend.catalogoolimpiadas.domain;

import com.softcraft.ohhsansibackend.area.domain.models.Area;
import com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.model.ParticipanteCatalogo;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class CatalogoDomainRepository {
    private final JdbcTemplate jdbcTemplate;

    public CatalogoDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> getCatalogoByGrado(int grado) {
        String sql = """
            SELECT DISTINCT 
                a.id_area, a.nombre_area, a.nombre_corto_area, a.descripcion_area, 
                co.id_categoria, c.nombre_categoria, co.id_olimpiada, co.id_catalogo, 
                o.precio_olimpiada
            FROM grado_categoria gc
            JOIN catalogo_olimpiada co ON gc.id_categoria = co.id_categoria
            JOIN area a ON co.id_area = a.id_area
            JOIN categorias c ON co.id_categoria = c.id_categoria
            JOIN olimpiada o ON co.id_olimpiada = o.id_olimpiada
            WHERE gc.id_grado = ?
            AND o.id_estado IN (
                SELECT id_estado FROM estado_olimpiada 
                WHERE nombre_estado IN ('PRE_INSCRIPCION', 'INSCRIPCION', 'EN_CURSO')
            )
            ORDER BY a.nombre_area
        """;
        return jdbcTemplate.queryForList(sql, grado);
    }

    public ParticipanteCatalogo insertParticipanteCatalogo(ParticipanteCatalogo participanteCatalogo) {
        // Validar estado de la olimpiada
        String estado = getEstadoOlimpiada(participanteCatalogo.getIdOlimpiada())
                .orElseThrow(() -> new IllegalStateException("La olimpiada especificada no existe"));

        // Validar período de inscripción
        if (estado.equals("EN_CURSO") && !isPeriodoInscripcionActivo(participanteCatalogo.getIdOlimpiada())) {
            throw new IllegalStateException("No se pueden registrar participantes fuera del período de inscripción");
        } else if (!List.of("PRE_INSCRIPCION", "INSCRIPCION").contains(estado)) {
            throw new IllegalStateException("No se pueden registrar participantes en el estado actual de la olimpiada: " + estado);
        }

        // Validar áreas máximas
        validateRegisteredAreas(participanteCatalogo.getIdParticipante());

        // Insertar registro
        String sql = """
            INSERT INTO participante_catalogo (
                id_categoria, id_area, id_catalogo, id_olimpiada, 
                id_inscripcion, id_participante
            ) VALUES (?, ?, ?, ?, ?, ?)
            RETURNING id_participante_catalogo
        """;

        try {
            Integer id = jdbcTemplate.queryForObject(sql, Integer.class,
                    participanteCatalogo.getIdCategoria(),
                    participanteCatalogo.getIdArea(),
                    participanteCatalogo.getIdCatalogo(),
                    participanteCatalogo.getIdOlimpiada(),
                    participanteCatalogo.getIdInscripcion(),
                    participanteCatalogo.getIdParticipante());

            participanteCatalogo.setIdParticipanteCatalogo(id);
            return participanteCatalogo;
        } catch (DuplicateKeyException e) {
            throw new IllegalStateException("El participante ya está registrado en esta área y categoría", e);
        }
    }

    private Optional<String> getEstadoOlimpiada(int idOlimpiada) {
        String sql = """
            SELECT e.nombre_estado 
            FROM olimpiada o
            JOIN estado_olimpiada e ON o.id_estado = e.id_estado
            WHERE o.id_olimpiada = ?
        """;
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(sql, String.class, idOlimpiada));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<Area> getAreaByCiParticipante(int ciParticipante) {
        String sql = """
            SELECT DISTINCT a.*
            FROM participante p
            JOIN participante_catalogo pc ON p.id_participante = pc.id_participante
            JOIN catalogo_olimpiada co ON pc.id_categoria = co.id_categoria
                AND pc.id_olimpiada = co.id_olimpiada
                AND pc.id_catalogo = co.id_catalogo
                AND pc.id_area = co.id_area
            JOIN area a ON co.id_area = a.id_area
            JOIN categorias c ON co.id_categoria = c.id_categoria
            WHERE p.carnet_identidad_participante = ?
        """;
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Area area = new Area();
            area.setIdArea(rs.getInt("id_area"));
            area.setNombreArea(rs.getString("nombre_area"));
            area.setNombreCortoArea(rs.getString("nombre_corto_area"));
            area.setDescripcionArea(rs.getString("descripcion_area"));
            return area;
        }, ciParticipante);
    }

    public boolean existsParticipanteInCatalogo(int ciParticipante) {
        String sql = """
            SELECT EXISTS (
                SELECT 1 FROM participante p
                JOIN participante_catalogo pc ON p.id_participante = pc.id_participante
                WHERE p.carnet_identidad_participante = ?
            )
        """;
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, Boolean.class, ciParticipante));
    }

    public List<Integer> getRegisteredAreasByParticipante(int idParticipante) {
        String sql = "SELECT id_area FROM participante_catalogo WHERE id_participante = ?";
        return jdbcTemplate.queryForList(sql, Integer.class, idParticipante);
    }

    public void validateRegisteredAreas(int idParticipante) {
        String sql = "SELECT COUNT(id_area) FROM participante_catalogo WHERE id_participante = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, idParticipante);
        if (count != null && count >= 2) {
            throw new IllegalArgumentException("El participante ya tiene 2 áreas registradas");
        }
    }

    public boolean isPeriodoInscripcionActivo(int idOlimpiada) {
        String sql = """
            SELECT EXISTS (
                SELECT 1 FROM periodos_olimpiada po
                WHERE po.id_olimpiada = ?
                AND po.tipo_periodo IN ('PRE_INSCRIPCION', 'INSCRIPCION')
                AND CURRENT_TIMESTAMP BETWEEN po.fecha_inicio AND po.fecha_fin
            )
        """;
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, Boolean.class, idOlimpiada));
    }

    public Optional<Map<String, Object>> getOlimpiadaActiva() {
        String sql = """
            SELECT o.id_olimpiada, e.nombre_estado 
            FROM olimpiada o
            JOIN estado_olimpiada e ON o.id_estado = e.id_estado
            WHERE e.nombre_estado IN ('PRE_INSCRIPCION', 'INSCRIPCION', 'EN_CURSO')
            ORDER BY o.id_olimpiada DESC
            LIMIT 1
        """;
        try {
            return Optional.of(jdbcTemplate.queryForMap(sql));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<Map<String, Object>> getRegisterAreaParticipante(int idParticipante, int idGrado) {
        Map<String, Object> olimpiadaInfo = getOlimpiadaActiva()
                .orElseThrow(() -> new IllegalStateException("No hay olimpiadas en período de inscripción o en curso"));

        Integer idOlimpiada = (Integer) olimpiadaInfo.get("id_olimpiada");
        String estadoOlimpiada = (String) olimpiadaInfo.get("nombre_estado");

        if ("EN_CURSO".equals(estadoOlimpiada)){
            if (!isPeriodoInscripcionActivo(idOlimpiada)) {
                throw new IllegalStateException("No está habilitado el período de inscripciones para esta olimpiada");
            }
        }

        String sql = """
            SELECT
                a.id_area, a.nombre_area, a.nombre_corto_area, a.descripcion_area,
                co.id_categoria, c.nombre_categoria, co.id_olimpiada, co.id_catalogo,
                o.precio_olimpiada, o.nombre_olimpiada,
                EXISTS (
                    SELECT 1 FROM participante_catalogo pc
                    WHERE pc.id_categoria = co.id_categoria
                    AND pc.id_area = co.id_area
                    AND pc.id_catalogo = co.id_catalogo
                    AND pc.id_olimpiada = co.id_olimpiada
                    AND pc.id_participante = ?
                ) AS asignada,
                e.nombre_estado AS estado_olimpiada
            FROM grado_categoria gc
            JOIN catalogo_olimpiada co ON gc.id_categoria = co.id_categoria
            JOIN area a ON co.id_area = a.id_area
            JOIN categorias c ON co.id_categoria = c.id_categoria
            JOIN olimpiada o ON co.id_olimpiada = o.id_olimpiada
            JOIN estado_olimpiada e ON o.id_estado = e.id_estado
            WHERE gc.id_grado = ?
            AND co.id_olimpiada = ?
            ORDER BY a.nombre_area
        """;

        return jdbcTemplate.queryForList(sql, idParticipante, idGrado, idOlimpiada);
    }
}
