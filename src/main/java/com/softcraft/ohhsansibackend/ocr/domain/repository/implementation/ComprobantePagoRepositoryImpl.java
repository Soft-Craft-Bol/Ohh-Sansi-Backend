package com.softcraft.ohhsansibackend.ocr.domain.repository.implementation;

import com.softcraft.ohhsansibackend.ocr.domain.repository.abstraction.ComprobantePagoRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.dao.DataAccessException;

import java.sql.Date;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Repository
public class ComprobantePagoRepositoryImpl implements ComprobantePagoRepository {

    private final JdbcTemplate jdbcTemplate;

    public ComprobantePagoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String registrarComprobantePago(
            Long carnetIdentidad,
            Double montoPagado,
            String fechaPago,
            String codTransaccion,
            String imagenComprobante,
            String nombreReceptor,
            String estadoOrden,
            String notasAdicionales) {

        try {
            // Conversión de fecha
            Date fechaPagoConvertida = Date.valueOf(LocalDate.parse(fechaPago));

            // Conversión de monto pagado a NUMERIC(10, 2)
            BigDecimal montoPagadoConvertido = BigDecimal.valueOf(montoPagado).setScale(2, RoundingMode.HALF_UP);

            // Ejecución del query
            String sql = "SELECT registrar_comprobante_pago(?, ?, ?, ?, ?, ?, ?, ?)";
            return jdbcTemplate.queryForObject(
                    sql,
                    new Object[] {
                            carnetIdentidad,
                            montoPagadoConvertido,
                            fechaPagoConvertida,
                            codTransaccion,
                            imagenComprobante,
                            nombreReceptor,
                            estadoOrden,
                            notasAdicionales
                    },
                    String.class
            );
        } catch (DataAccessException e) {
            e.printStackTrace();
            return "ERROR: No se pudo registrar el comprobante de pago.";
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR: Formato de fecha incorrecto o error inesperado.";
        }
    }
}
