package com.softcraft.ohhsansibackend.catalogoolimpiadas.domain;

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
            SELECT a.id_area, a.nombre_area, a.nombre_corto_area, a.descripcion_area, co.id_categoria, co.id_olimpiada, co.id_catalogo
            FROM participante p, grado_categoria gc, catalogo_olimpiada co, area a
            WHERE ? = gc.id_grado
              AND gc.id_categoria = co.id_categoria
              AND a.id_area = co.id_area
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


}
