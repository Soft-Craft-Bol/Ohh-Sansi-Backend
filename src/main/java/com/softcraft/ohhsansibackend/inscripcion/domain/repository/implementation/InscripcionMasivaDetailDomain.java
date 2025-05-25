package com.softcraft.ohhsansibackend.inscripcion.domain.repository.implementation;

import com.softcraft.ohhsansibackend.inscripcion.domain.models.InscripcionMasivaDetail;
import com.softcraft.ohhsansibackend.inscripcion.domain.repository.abstraction.IInscripcionMasivaDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class InscripcionMasivaDetailDomain implements IInscripcionMasivaDetail {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public InscripcionMasivaDetailDomain(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public InscripcionMasivaDetail getAllDetails(String codUnico) {
        String sql = "SELECT inscripcion_id, nombre_tutor, apellido_tutor,email,ci_tutor,cantidad_areas, cantidad_participantes " +
                "FROM obtener_info_tutor_con_areas(?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{codUnico}, new InscripcionAsignedRowMapper());
    }

    private static class InscripcionAsignedRowMapper implements RowMapper<InscripcionMasivaDetail> {
        @Override
        public InscripcionMasivaDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
            InscripcionMasivaDetail inscription = new InscripcionMasivaDetail();
            inscription.setIdInscripcion(rs.getInt("inscripcion_id"));
            inscription.setCorreoTut(rs.getString("email"));
            inscription.setNombreTut(rs.getString("nombre_tutor"));
            inscription.setApellidoTut(rs.getString("apellido_tutor"));
            inscription.setCiTut(rs.getLong("ci_tutor"));
            inscription.setCantAreas(rs.getInt("cantidad_areas"));
            inscription.setCantPaticipantes(rs.getInt("cantidad_participantes"));
            return inscription;
        }
    }
}
