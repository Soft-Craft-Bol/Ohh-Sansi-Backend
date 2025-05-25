package com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.repository.implementation;

import com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.DTO.CatalogoOlimpiadaDTO;
import com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.model.CatalogoOlimpiada;
import com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.repository.abstraction.ICatalogoOlimpiadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Array;
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
        String sql = "SELECT * FROM insertcatalogo(?, ?, ?)";
      return jdbcTemplate .queryForObject(sql, new Object[]{
                catalogoOlimpiada.getIdArea(),
                catalogoOlimpiada.getIdCategoria(),
                catalogoOlimpiada.getIdOlimpiada()
        }, new BeanPropertyRowMapper<>(CatalogoOlimpiada.class));
    }


    @Override
    public List<CatalogoOlimpiadaDTO> findAll() {
        String sql = "SELECT * FROM obtener_catalogo_por_periodo()";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            CatalogoOlimpiadaDTO dto = new CatalogoOlimpiadaDTO();
            dto.setIdOlimpiada(rs.getInt("id_olimpiada"));
            dto.setNombreOlimpiada(rs.getString("nombre_olimpiada"));
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
}
