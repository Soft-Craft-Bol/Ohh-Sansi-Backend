package com.softcraft.ohhsansibackend.comprobantepago.domain;

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

}
