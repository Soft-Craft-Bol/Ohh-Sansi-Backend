package com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.repository.implementation;

import com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.DTO.CatalogoOlimpiadaDTO;
import com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.model.CatalogoOlimpiada;
import com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.repository.abstraction.ICatalogoOlimpiadaRepository;
import com.softcraft.ohhsansibackend.participante.infraestructure.request.AreaCatalogoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class CatalogoOlimpiadaDomainRepository implements ICatalogoOlimpiadaRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CatalogoOlimpiadaDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public CatalogoOlimpiada save(CatalogoOlimpiada catalogoOlimpiada) {
        String sql = "SELECT * FROM upsertcatalogo(?, ?, ?, ?)";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{
                    catalogoOlimpiada.getIdCatalogo(),
                    catalogoOlimpiada.getIdArea(),
                    catalogoOlimpiada.getIdCategoria(),
                    catalogoOlimpiada.getIdOlimpiada()
            }, new BeanPropertyRowMapper<>(CatalogoOlimpiada.class));
        } catch (DataAccessException e) {
            String errorMessage = e.getMostSpecificCause().getMessage();
            throw new DataIntegrityViolationException(errorMessage, e);
        }
    }

    @Override
    public List<CatalogoOlimpiadaDTO> getCatalogoOlimpiadaById(Integer idCatalogoOlimpiada) {
        String sql = "SELECT co.id_catalogo, a.id_area, a.nombre_area, a.descripcion_area, " +
                "g.nombre_grado, o.nombre_olimpiada, o.id_olimpiada, c.nombre_categoria " +
                "FROM catalogo_olimpiada co " +
                "JOIN olimpiada o ON co.id_olimpiada = o.id_olimpiada " +
                "JOIN area a ON co.id_area = a.id_area " +
                "JOIN categorias c ON co.id_categoria = c.id_categoria " +
                "JOIN grado_categoria gc ON co.id_categoria = gc.id_categoria " +
                "JOIN grado g ON gc.id_grado = g.id_grado " +
                "WHERE co.id_olimpiada = ? " +
                "ORDER BY co.id_catalogo";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, idCatalogoOlimpiada);

        Map<Integer, List<Map<String, Object>>> grouped = rows.stream()
                .collect(Collectors.groupingBy(row -> (Integer)row.get("id_catalogo")));

        return grouped.values().stream().map(group -> {
            Map<String, Object> firstRow = group.get(0);

            CatalogoOlimpiadaDTO dto = new CatalogoOlimpiadaDTO();
            dto.setIdCatalogo((Integer)firstRow.get("id_catalogo"));
            dto.setIdArea((Integer)firstRow.get("id_area"));
            dto.setNombreArea((String)firstRow.get("nombre_area"));
            dto.setDescripcionArea((String)firstRow.get("descripcion_area"));
            dto.setNombreOlimpiada((String)firstRow.get("nombre_olimpiada"));
            dto.setIdOlimpiada((Integer)firstRow.get("id_olimpiada"));
            dto.setNombreCategoria((String)firstRow.get("nombre_categoria"));

            List<String> grados = group.stream()
                    .map(row -> (String)row.get("nombre_grado"))
                    .collect(Collectors.toList());
            dto.setGrados(grados);

            return dto;
        }).collect(Collectors.toList());
    }


    @Override
    public List<CatalogoOlimpiadaDTO> findAll() {
        String sql = "SELECT * FROM obtener_catalogo_por_periodo()";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            CatalogoOlimpiadaDTO dto = new CatalogoOlimpiadaDTO();
            dto.setIdOlimpiada(rs.getInt("id_olimpiada"));
            dto.setNombreOlimpiada(rs.getString("nombre_olimpiada"));
            dto.setIdCatalogo(rs.getInt("id_catalogo"));
            dto.setIdArea(rs.getInt("id_area"));
            dto.setNombreArea(rs.getString("nombre_area"));
            dto.setNombreCategoria(rs.getString("nombre_categoria"));

            Array gradosArray = rs.getArray("grados");
            if (gradosArray != null) {
                String [] grados  = (String[]) gradosArray.getArray();
                dto.setGrados(Arrays.asList(grados));
            }

            return dto;
        });
    }

    public List<AreaCatalogoDTO> getAreaCatalogoByIdAreaAndIdOlimpiada(int idArea,int idArea2, int idOlimpiada, int idGrado) {
        String sql =
                """
                        select distinct co.*
                        from catalogo_olimpiada co, grado_categoria gc
                            where (id_area=? or id_area=?)
                            and id_olimpiada=?
                            and gc.id_categoria=co.id_categoria
                            and gc.id_grado = ?;
                """;
        return jdbcTemplate.query(
                sql,
                new Object[]{
                    idArea,
                    idArea2,
                    idOlimpiada,
                    idGrado
                },
                new BeanPropertyRowMapper<>(
                            AreaCatalogoDTO.class
                        )
        );
    }
}
