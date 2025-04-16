package com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.repository.implementation;

import com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.DTO.CatalogoOlimpiadaDTO;
import com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.model.CatalogoOlimpiada;
import com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.repository.abstraction.ICatalogoOlimpiadaRepository;
import com.softcraft.ohhsansibackend.grado.infraestructure.request.GradoCategoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.List;

@Repository
public class CatalogoOlimpiadaDomainRepository implements ICatalogoOlimpiadaRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CatalogoOlimpiadaDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public CatalogoOlimpiada save(CatalogoOlimpiada catalogoOlimpiada) {
        String sql = "INSERT INTO catalogo_olimpiada (id_categoria, id_area, id_olimpiada) VALUES (?, ?, ?) RETURNING id_catalogo";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[] {"id_catalogo"});
            ps.setInt(1, catalogoOlimpiada.getIdArea());
            ps.setInt(2, catalogoOlimpiada.getIdCategoria());
            ps.setInt(3, catalogoOlimpiada.getIdOlimpiada());
            return ps;
        }, keyHolder);

        int newId = keyHolder.getKey().intValue();
        catalogoOlimpiada.setIdCatalogo(newId);
        return catalogoOlimpiada;
    }


    @Override
    public List<CatalogoOlimpiadaDTO> findAll() {
        String sql = "SELECT * FROM obtener_catalogo_por_periodo()";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            CatalogoOlimpiadaDTO dto = new CatalogoOlimpiadaDTO();
            dto.setNombreOlimpiada(rs.getString("nombre_olimpiada"));
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
}
