package com.softcraft.ohhsansibackend.area.domain.repository.implementation;

import com.softcraft.ohhsansibackend.area.domain.models.PdfOlimpiada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class PdfOlimpiadaDomainRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PdfOlimpiadaDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public PdfOlimpiada save(String pdfBase64) {
        String sql = """
                    INSERT INTO pdf_olimpiada (pdf_base64)
                    VALUES (?)
                    RETURNING id_pdf_convocatoria;
                """;

        Integer idPdfConvocatoria = jdbcTemplate.query(sql, new Object[]{pdfBase64}, rs -> {
            return rs.next() ? rs.getInt("id_pdf_convocatoria") : null;
        });

        if (idPdfConvocatoria == null) {
            throw new IllegalStateException("Failed to insert PdfOlimpiada, no ID returned.");
        }

        PdfOlimpiada pdfOlimpiada = new PdfOlimpiada();
        pdfOlimpiada.setIdPdfConvocatoria(idPdfConvocatoria);
        pdfOlimpiada.setPdfBase64(pdfBase64);
        return pdfOlimpiada;
    }
    public List<Map<String, Object>> getPdfConvocatorias(int idArea, int idOlimpiada) {
        String sql = """
                    select distinct pdfo.*
                    from convocatoria c, pdf_olimpiada pdfo
                    where c.id_area = ? and c.id_olimpiada=? and c.id_pdf_convocatoria = pdfo.id_pdf_convocatoria;
                """;
        return jdbcTemplate.queryForList(sql, idArea, idOlimpiada);
    }
}
