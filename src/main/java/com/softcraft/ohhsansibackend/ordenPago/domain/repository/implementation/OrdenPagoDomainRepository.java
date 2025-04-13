package com.softcraft.ohhsansibackend.ordenPago.domain.repository.implementation;
import com.softcraft.ohhsansibackend.ordenPago.domain.models.OrdenDePago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class OrdenPagoDomainRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OrdenPagoDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public OrdenDePago save(OrdenDePago ordenDePago) {
        String sql = "INSERT INTO orden_de_pago (id_inscripcion, id_metodo_pago, id_estado, fecha_emision_orden_pago, " +
                "fecha_vencimiento, monto_total_pago, cod_orden_pago, emisor,precio_literal, responsable_pago, cantidad, " +
                "concepto, precio_unitario) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id_orden_pago";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, ordenDePago.getIdInscripcion());
            ps.setInt(2, ordenDePago.getIdMetodoPago());
            ps.setInt(3, ordenDePago.getIdEstado());
            ps.setDate(4, ordenDePago.getFechaEmisionOrdenPago());
            ps.setDate(5, ordenDePago.getFechaVencimiento());
            ps.setBigDecimal(6, ordenDePago.getMontoTotalPago());
            ps.setString(7, ordenDePago.getCodOrdenPago());
            ps.setString(8, ordenDePago.getEmisor());
            ps.setString(9, ordenDePago.getPrecioLiteral());
            ps.setString(10, ordenDePago.getResponsablePago());
            ps.setInt(11, ordenDePago.getCantidad());
            ps.setString(12, ordenDePago.getConcepto());
            ps.setBigDecimal(13, ordenDePago.getPrecio_unitario());
            return ps;
        }, keyHolder);

        ordenDePago.setIdOrdenPago(keyHolder.getKey().intValue());
        return ordenDePago;
    }


    public List<OrdenDePago> findAllByIdInscripcion(int idInscripcion) {
        String sql = "SELECT * FROM orden_de_pago WHERE id_inscripcion = ?";
        return jdbcTemplate.query(sql, new Object[]{idInscripcion}, new RowMapper<OrdenDePago>() {
            @Override
            public OrdenDePago mapRow(ResultSet rs, int rowNum) throws SQLException {
                OrdenDePago ordenDePago = new OrdenDePago();
                ordenDePago.setIdOrdenPago(rs.getInt("id_orden_pago"));
                ordenDePago.setIdInscripcion(rs.getInt("id_inscripcion"));
                ordenDePago.setIdMetodoPago(rs.getInt("id_metodo_pago"));
                ordenDePago.setIdEstado(rs.getInt("id_estado"));
                ordenDePago.setFechaEmisionOrdenPago(rs.getDate("fecha_emision_orden_pago"));
                ordenDePago.setFechaVencimiento(rs.getDate("fecha_vencimiento"));
                ordenDePago.setMontoTotalPago(rs.getBigDecimal("monto_total_pago"));
                ordenDePago.setCodOrdenPago(rs.getString("cod_orden_pago"));
                ordenDePago.setEmisor(rs.getString("emisor"));
                ordenDePago.setEmisor(rs.getString("precio_literal"));
                ordenDePago.setResponsablePago(rs.getString("responsable_pago"));
                ordenDePago.setCantidad(rs.getInt("cantidad"));
                ordenDePago.setConcepto(rs.getString("concepto"));
                ordenDePago.setPrecio_unitario(rs.getBigDecimal("precio_unitario"));
                return ordenDePago;
            }
        });
    }
    public OrdenDePago findOrdenPagoByID(int idInscripcion){
        String sql = "SELECT * FROM orden_de_pago WHERE id_inscripcion = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{idInscripcion}, new RowMapper<OrdenDePago>() {
            @Override
            public OrdenDePago mapRow(ResultSet rs, int rowNum) throws SQLException {
                OrdenDePago ordenDePago = new OrdenDePago();
                ordenDePago.setIdOrdenPago(rs.getInt("id_orden_pago"));
                ordenDePago.setIdInscripcion(rs.getInt("id_inscripcion"));
                ordenDePago.setIdMetodoPago(rs.getInt("id_metodo_pago"));
                ordenDePago.setIdEstado(rs.getInt("id_estado"));
                ordenDePago.setFechaEmisionOrdenPago(rs.getDate("fecha_emision_orden_pago"));
                ordenDePago.setFechaVencimiento(rs.getDate("fecha_vencimiento"));
                ordenDePago.setMontoTotalPago(rs.getBigDecimal("monto_total_pago"));
                ordenDePago.setCodOrdenPago(rs.getString("cod_orden_pago"));
                ordenDePago.setEmisor(rs.getString("emisor"));
                ordenDePago.setPrecioLiteral(rs.getString("precio_literal"));
                ordenDePago.setResponsablePago(rs.getString("responsable_pago"));
                ordenDePago.setCantidad(rs.getInt("cantidad"));
                ordenDePago.setConcepto(rs.getString("concepto"));
                ordenDePago.setPrecio_unitario(rs.getBigDecimal("precio_unitario"));
                return ordenDePago;
            }
        });
    }
    public boolean verificarExistenciaDeInscripcionEnOrdenPago(int idInscripcion){
        String sql = """
                        select exists(
                        select 1
                        from orden_de_pago
                        where id_inscripcion = ?);
                    """;
        Boolean exists = jdbcTemplate.queryForObject(sql, new Object[]{idInscripcion}, Boolean.class);
        return (exists != null) ? exists : false;
    }
}