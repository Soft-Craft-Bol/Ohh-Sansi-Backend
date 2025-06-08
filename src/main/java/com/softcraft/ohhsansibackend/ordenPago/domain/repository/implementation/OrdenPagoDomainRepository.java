package com.softcraft.ohhsansibackend.ordenPago.domain.repository.implementation;
import com.softcraft.ohhsansibackend.ordenPago.domain.models.EstadoOrdenDePago;
import com.softcraft.ohhsansibackend.ordenPago.domain.models.OrdenDePago;
import com.softcraft.ohhsansibackend.ordenPago.domain.repository.model.OrdenPagoEstadoEnum;
import com.softcraft.ohhsansibackend.participante.domain.models.Participante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
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


    public OrdenDePago findOrdenPagoByIDOrdenPago(int idOrdenPago){
        String sql = "SELECT * FROM orden_de_pago WHERE id_orden_pago = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{idOrdenPago}, new RowMapper<OrdenDePago>() {
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

    public Integer verificarParticipanteDeMasivo(int ciParticipante) {
        String sql = """
        SELECT id_inscripcion_excel FROM excel_association
        WHERE ci_participante = ?
    """;

        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, ciParticipante);
        } catch (EmptyResultDataAccessException e) {
            return null; // No se encontró asociación
        }
    }

    public Integer verificarCarnetDeMasivo(int ciParticipante) {
        String sql = """
        SELECT id_excel FROM excel_association
        WHERE ci_participante = ?
    """;

        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, ciParticipante);
        } catch (EmptyResultDataAccessException e) {
            return null; // No se encontró asociación
        }
    }

    public List<OrdenDePago> findOrdenesNoVencidasEnRango(Date fechaInicio, Date fechaFin) {
        String sql = """
                SELECT *
                FROM orden_de_pago
                WHERE fecha_vencimiento NOT BETWEEN ? AND ?
                """;

        return jdbcTemplate.query(sql, new Object[]{fechaInicio, fechaFin}, new RowMapper<OrdenDePago>() {
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
    public List<OrdenDePago> findOrdenPagoByOlimpiada(int idOlimpiada) {
        String sql =
            """
                
                select distinct op.*
                        from participante_catalogo pc, orden_de_pago op, inscripcion i
                        where pc.id_inscripcion = i.id_inscripcion
                        and op.id_inscripcion = i.id_inscripcion
                        and pc.id_olimpiada = ?
            """;
        return jdbcTemplate.query(sql, new Object[]{idOlimpiada}, (rs, rowNum) -> {
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
        });
    }

    public List<EstadoOrdenDePago> getAllEstadoOrdenPago(){
        String sql =
                """
                    Select * from estado_orden_de_pago;
                """;
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            EstadoOrdenDePago estadoOrdenDePago = new EstadoOrdenDePago();
            estadoOrdenDePago.setIdEstado(rs.getInt("id_estado"));
            estadoOrdenDePago.setEstado(rs.getString("estado"));
            return estadoOrdenDePago;
        });
    }

    public boolean changeEstadoOrdenPagoAPagado(int idOrdenPago) {
        OrdenPagoEstadoEnum estadoOrdenPago = OrdenPagoEstadoEnum.PAGADO;
        int idEstado = estadoOrdenPago.getId();
        String sql= """
                    UPDATE orden_de_pago
                    SET id_estado = ?
                    WHERE id_orden_pago = ?
                """;
        int ct = jdbcTemplate.update(sql, idEstado, idOrdenPago);
        if(ct==0){
            throw new RuntimeException("oNo se modifico el estado de la orden de pago");
        }else{
            return true;
        }
    }

    public OrdenDePago getOrdenDePagoByIdComprobantePago(int idComprobantePago){
        String sql = """
                Select op.*
                from orden_de_pago op, comprobante_pago cp
                where op.id_orden_pago = cp.id_orden_pago and cp.id_comprobante_pago = ?;
                """;
        return jdbcTemplate.queryForObject(sql, new Object[]{idComprobantePago}, new BeanPropertyRowMapper<>(OrdenDePago.class));
    }

}