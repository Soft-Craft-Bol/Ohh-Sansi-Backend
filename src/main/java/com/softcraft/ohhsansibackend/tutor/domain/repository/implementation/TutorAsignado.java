package com.softcraft.ohhsansibackend.tutor.domain.repository.implementation;

import com.softcraft.ohhsansibackend.tutor.domain.models.TutorAsigned;
import com.softcraft.ohhsansibackend.tutor.domain.repository.abstraction.ITutorAsignado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TutorAsignado implements ITutorAsignado {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public TutorAsignado(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<TutorAsigned> findAllTutors(String ci) {
        String sql = "SELECT correo_tut, nombre_tut, apellido_tut, telf, ci_tut, complemento " +
                "FROM get_tutor_legal_por_participante_ci(?)";
        return jdbcTemplate.query(sql, new Object[]{Integer.parseInt(ci)}, new TutorAsignedRowMapper());
    }

    private static class TutorAsignedRowMapper implements RowMapper<TutorAsigned> {
        @Override
        public TutorAsigned mapRow(ResultSet rs, int rowNum) throws SQLException {
            TutorAsigned tutor = new TutorAsigned();
            tutor.setCorreoTut(rs.getString("correo_tut"));
            tutor.setNombreTut(rs.getString("nombre_tut"));
            tutor.setApellidoTut(rs.getString("apellido_tut"));
            tutor.setTelf(rs.getInt("telf"));
            tutor.setCiTut(rs.getLong("ci_tut"));
            tutor.setComplemento(rs.getString("complemento"));
            return tutor;
        }
    }
}
