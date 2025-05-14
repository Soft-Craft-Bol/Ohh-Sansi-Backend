package com.softcraft.ohhsansibackend.catalogoolimpiadas.domain;

import com.softcraft.ohhsansibackend.area.domain.models.Area;
import com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.model.ParticipanteCatalogo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Repository
public class CatalogoDomainRepository {
    private final JdbcTemplate jdbcTemplate;

    public CatalogoDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> getCatalogoByGrado(int grado) {
        String sql = """
            select distinct a.id_area, a.nombre_area, a.nombre_corto_area, a.descripcion_area, co.id_categoria, c.nombre_categoria, co.id_olimpiada, co.id_catalogo, o.precio_olimpiada
            from participante p, grado_categoria gc, catalogo_olimpiada co, area a, categorias c, olimpiada o
            where ? = gc.id_grado
            and gc.id_categoria = co.id_categoria
            and a.id_area = co.id_area
            and c.id_categoria = co.id_categoria
            and o.id_olimpiada = co.id_olimpiada
            and o.estado_olimpiada = true
        """;
        return jdbcTemplate.queryForList(sql, grado);
    }


    public ParticipanteCatalogo insertParticipanteCatalogo(ParticipanteCatalogo participanteCatalogo) {
        String sql = """
            INSERT INTO participante_catalogo (id_categoria, id_area, id_catalogo, id_olimpiada, id_inscripcion, id_participante)
            VALUES (?, ?, ?, ?, ?, ?)
        """;
        jdbcTemplate.update(sql,
                participanteCatalogo.getIdCategoria(),
                participanteCatalogo.getIdArea(),
                participanteCatalogo.getIdCatalogo(),
                participanteCatalogo.getIdOlimpiada(),
                participanteCatalogo.getIdInscripcion(),
                participanteCatalogo.getIdParticipante()
        );
        return participanteCatalogo;
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
            WHERE p.carnet_identidad_participante = ?;
            """;

        return jdbcTemplate.query(sql, new Object[]{ciParticipante}, (rs, rowNum) -> {
            Area area = new Area();
            area.setIdArea(rs.getInt("id_area"));
            area.setNombreArea(rs.getString("nombre_area"));
            area.setNombreCortoArea(rs.getString("nombre_corto_area"));
            area.setDescripcionArea(rs.getString("descripcion_area"));
            return area;
        });
    }
    public boolean existsParticipanteInCatalogo(int ciParticipante) {
        String sql = """
            select exists (
                select 1
                from participante p
                         join participante_catalogo pc on p.id_participante = pc.id_participante
                where p.carnet_identidad_participante = ?
            );
        """;
        Boolean exists = jdbcTemplate.queryForObject(sql, new Object[]{ciParticipante}, Boolean.class);
        return (exists != null) ? exists : false;
    }
    public List<Integer> getRegisteredAreasByParticipante(int idParticipante) {
        String sql = """
        SELECT pc.id_area
        FROM participante_catalogo pc
        WHERE pc.id_participante = ?
    """;
        return jdbcTemplate.queryForList(sql, new Object[]{idParticipante}, Integer.class);
    }
    public void validateRegisteredAreas(int idParticipante) {
        String sql = """
        SELECT COUNT(pc.id_area)
        FROM participante_catalogo pc
        WHERE pc.id_participante = ?
    """;
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{idParticipante}, Integer.class);
        if (count != null && count >= 2) {
            throw new IllegalArgumentException("El participante ya tiene 2 áreas registradas.");
        }
    }

    public List<Map<String, Object>> getRegisterAreaParticipante(int idParticipante, int idGrado) {
        String sql = """
            SELECT
                a.id_area,
                a.nombre_area,
                a.nombre_corto_area,
                a.descripcion_area,
                co.id_categoria,
                c.nombre_categoria,
                co.id_olimpiada,
                co.id_catalogo,
                o.precio_olimpiada,
                p.id_participante,
                p.nombre_participante || ' ' || p.apellido_paterno AS nombre_completo,
                EXISTS (
                    SELECT 1
                    FROM participante_catalogo pc
                    WHERE co.id_categoria = pc.id_categoria
                    AND co.id_area = pc.id_area
                    AND co.id_catalogo = pc.id_catalogo
                    AND co.id_olimpiada = pc.id_olimpiada
                    AND pc.id_participante = p.id_participante
                ) AS asignada
            FROM
                grado_categoria gc
            JOIN
                catalogo_olimpiada co ON gc.id_categoria = co.id_categoria
            JOIN
                area a ON co.id_area = a.id_area
            JOIN
                categorias c ON co.id_categoria = c.id_categoria
            JOIN
                olimpiada o ON co.id_olimpiada = o.id_olimpiada
            CROSS JOIN
                participante p
            WHERE
                gc.id_grado = ?
                AND o.estado_olimpiada = true
                AND p.id_participante = ?
            ORDER BY
                a.nombre_area;
            """;

        return jdbcTemplate.queryForList(sql, idGrado, idParticipante);
    }

}
