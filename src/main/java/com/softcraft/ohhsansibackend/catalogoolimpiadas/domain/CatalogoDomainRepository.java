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
            SELECT a.id_area, a.nombre_area, a.nombre_corto_area, a.descripcion_area, co.id_categoria, c.nombre_categoria, co.id_olimpiada, co.id_catalogo, o.precio_olimpiada
            FROM participante p, grado_categoria gc, catalogo_olimpiada co, area a, categorias c, olimpiada o
            WHERE ? = gc.id_grado
              AND gc.id_categoria = co.id_categoria
              AND a.id_area = co.id_area
              AND c.id_categoria = co.id_categoria
              AND o.id_olimpiada = co.id_olimpiada
     
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

}
