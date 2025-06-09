package com.softcraft.ohhsansibackend.comprobantepago.domain;

import com.softcraft.ohhsansibackend.comprobantepago.domain.model.ComprobantePago;
import com.softcraft.ohhsansibackend.comprobantepago.domain.model.EstadoComprobantePago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ComprobantePagoAppRepository {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public ComprobantePagoAppRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Map<String, Object>> getComprobantesPago() {
        String sql = """
                    select distinct cp.*, ecp.nombre_estado_comprobante
                    from comprobante_pago cp, estado_comprobante_pago ecp, orden_de_pago op
                    where cp.id_estado_comprobante = ecp.id_estado_comprobante;
                """;
        return jdbcTemplate.queryForList(sql);
    }

    public void actualizarEstadoComprobantePago(int idComprobantePago, int idEstadoComprobante) {
        String sql = """
                UPDATE comprobante_pago
                SET id_estado_comprobante = ?
                WHERE id_comprobante_pago = ?
            """;
        jdbcTemplate.update(sql, idEstadoComprobante, idComprobantePago);
    }
    public boolean verificarExistenciaComprobantePago(int ciParticipante) {
        String sql = """
                SELECT EXISTS (
                    select 1
                    from participante p,  comprobante_pago cp
                    where p.id_inscripcion = cp.id_inscripcion and p.carnet_identidad_participante = ?
                );
                """;
        Boolean exists = jdbcTemplate.queryForObject(sql, new Object[]{ciParticipante}, Boolean.class);
        return (exists != null) ? exists : false;
    }

    public EstadoComprobantePago getEstadoComprobantePago(int ciParticipante) {
        String sql = """
        select distinct ecp.id_estado_comprobante, ecp.nombre_estado_comprobante
        from participante p, inscripcion i, comprobante_pago cp, estado_comprobante_pago ecp
        where p.id_inscripcion = i.id_inscripcion
          and i.id_inscripcion = cp.id_inscripcion
          and cp.id_estado_comprobante = ecp.id_estado_comprobante
          and cp.id_inscripcion = ?;
    """;
        return jdbcTemplate.queryForObject(sql, new Object[]{ciParticipante}, (rs, rowNum) -> {
            EstadoComprobantePago estadoComprobantePago = new EstadoComprobantePago();
            estadoComprobantePago.setIdEstadoComprobantePago(rs.getInt("id_estado_comprobante"));
            estadoComprobantePago.setNombreEstadoComprobante(rs.getString("nombre_estado_comprobante"));
            return estadoComprobantePago;
        });
    }

    public ComprobantePago getComprobantePagoByCiParticipante(int ciParticipante) {
        String sql = """
        select distinct cp.*
        from participante p, inscripcion i, comprobante_pago cp
        where p.id_inscripcion = i.id_inscripcion
          and i.id_inscripcion = cp.id_inscripcion
          and p.carnet_identidad_participante = ?;
    """;
        return jdbcTemplate.queryForObject(sql, new Object[]{ciParticipante}, (rs, rowNum) -> {
            ComprobantePago comprobantePago = new ComprobantePago();
            comprobantePago.setIdInscripcion(rs.getInt("id_inscripcion"));
            comprobantePago.setIdOrdenPago(rs.getInt("id_orden_pago"));
            comprobantePago.setIdComprobantePago(rs.getInt("id_comprobante_pago"));
            comprobantePago.setMontoPagado(rs.getBigDecimal("monto_pagado"));
            comprobantePago.setFechaPago(rs.getDate("fecha_pago"));
            comprobantePago.setCodTransaccion(rs.getString("cod_transaccion"));
            comprobantePago.setImagenComprobante(rs.getString("imagen_comprobante"));
            comprobantePago.setNombreReceptor(rs.getString("nombre_receptor"));
            comprobantePago.setNotasAdicionales(rs.getString("notas_adicionales"));
            comprobantePago.setIdEstadoComprobante(rs.getInt("id_estado_comprobante"));
            return comprobantePago;
        });
    }
    public List<ComprobantePago> findAllComprobanteByIdOlimpiada(int idOlimpiada){
        String sql=
                """
                    select distinct cp.*
                    from participante_catalogo pc, inscripcion i, orden_de_pago op, comprobante_pago cp
                    where pc.id_inscripcion = i.id_inscripcion
                      and op.id_inscripcion = i.id_inscripcion
                      and cp.id_inscripcion = op.id_inscripcion
                      and op.id_orden_pago = cp.id_orden_pago
                      and pc.id_olimpiada = ?;
                """;
        return jdbcTemplate.query(sql, new Object[]{idOlimpiada}, (rs, rowNum) -> {
            ComprobantePago comprobantePago = new ComprobantePago();
            comprobantePago.setIdInscripcion(rs.getInt("id_inscripcion"));
            comprobantePago.setIdOrdenPago(rs.getInt("id_orden_pago"));
            comprobantePago.setIdComprobantePago(rs.getInt("id_comprobante_pago"));
            comprobantePago.setMontoPagado(rs.getBigDecimal("monto_pagado"));
            comprobantePago.setFechaPago(rs.getDate("fecha_pago"));
            comprobantePago.setCodTransaccion(rs.getString("cod_transaccion"));
            comprobantePago.setImagenComprobante(rs.getString("imagen_comprobante"));
            comprobantePago.setNombreReceptor(rs.getString("nombre_receptor"));
            comprobantePago.setNotasAdicionales(rs.getString("notas_adicionales"));
            comprobantePago.setIdEstadoComprobante(rs.getInt("id_estado_comprobante"));
            return comprobantePago;
        });
    }
    public List<EstadoComprobantePago> findAllEstadosComprobantePago() {
        String sql = "SELECT * FROM estado_comprobante_pago";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            EstadoComprobantePago estadoComprobantePago = new EstadoComprobantePago();
            estadoComprobantePago.setIdEstadoComprobantePago(rs.getInt("id_estado_comprobante"));
            estadoComprobantePago.setNombreEstadoComprobante(rs.getString("nombre_estado_comprobante"));
            return estadoComprobantePago;
        });
    }

    public List<Map<String, Object>> getEstadosComprobantePago(){
        String sql =
                """
                    select distinct *
                    from estado_comprobante_pago
                """;
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> getComprobantePagoByCod(String codigoUnicoInscripcion) {
        String sql=
                """
                    select distinct op.*
                    from inscripcion i, orden_de_pago op
                    where i.codigo_unico_inscripcion='?'
                    and i.id_inscripcion=op.id_inscripcion;
                """;
        return jdbcTemplate.queryForList(sql, codigoUnicoInscripcion);
    }



}
