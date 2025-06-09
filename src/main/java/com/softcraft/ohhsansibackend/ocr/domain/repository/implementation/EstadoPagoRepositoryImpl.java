package com.softcraft.ohhsansibackend.ocr.domain.repository.implementation;

import com.softcraft.ohhsansibackend.ocr.domain.repository.abstraction.EstadoPagoRepository;
import com.softcraft.ohhsansibackend.ocr.domain.dto.EstadoPagoDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.dao.DataAccessException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class EstadoPagoRepositoryImpl implements EstadoPagoRepository {

    private final JdbcTemplate jdbcTemplate;

    public EstadoPagoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class EstadoPagoRowMapper implements RowMapper<EstadoPagoDto> {
        @Override
        public EstadoPagoDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new EstadoPagoDto(
                    rs.getInt("id_inscripcion"),
                    rs.getInt("id_participante"),
                    rs.getString("nombre_participante"),
                    rs.getString("apellido_paterno"),
                    rs.getString("apellido_materno"),
                    rs.getString("carnet_identidad_participante"),
                    rs.getBigDecimal("monto_total_pago"),
                    rs.getString("responsable_pago"),
                    rs.getString("estado_pago"),
                    rs.getString("mensaje")
            );
        }
    }

    @Override
    public Optional<EstadoPagoDto> obtenerEstadoPago(String codigoUnicoInscripcion) {
        try {
            String sql = "SELECT * FROM obtener_estado_pago_inscripcion_v2(?)";

            EstadoPagoDto resultado = jdbcTemplate.queryForObject(
                    sql,
                    new Object[]{codigoUnicoInscripcion},
                    new EstadoPagoRowMapper()
            );

            return Optional.ofNullable(resultado);

        } catch (DataAccessException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}