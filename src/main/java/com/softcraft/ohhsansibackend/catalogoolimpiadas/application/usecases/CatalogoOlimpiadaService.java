package com.softcraft.ohhsansibackend.catalogoolimpiadas.application.usecases;

import com.softcraft.ohhsansibackend.catalogoolimpiadas.application.adapter.CatalogoOlimpiadaAdapter;
import com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.DTO.CatalogoOlimpiadaDTO;
import com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.model.CatalogoOlimpiada;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class CatalogoOlimpiadaService {

    private final CatalogoOlimpiadaAdapter catalogoOlimpiadaAdapter;
    private final JdbcTemplate jdbcTemplate;

    public CatalogoOlimpiadaService(CatalogoOlimpiadaAdapter catalogoOlimpiadaAdapter, JdbcTemplate jdbcTemplate) {
        this.catalogoOlimpiadaAdapter = catalogoOlimpiadaAdapter;
        this.jdbcTemplate = jdbcTemplate;
    }


    public Map<String, Object> save(CatalogoOlimpiada catalogoOlimpiada) {
        try {
            // Validar que los datos requeridos estén presentes
            if (catalogoOlimpiada.getIdOlimpiada() <= 0) {
                return Map.of(
                        "status", "error",
                        "message", "ID de olimpiada es requerido"
                );
            }

            if (catalogoOlimpiada.getIdArea() <= 0) {
                return Map.of(
                        "status", "error",
                        "message", "ID de área es requerido"
                );
            }

            if (catalogoOlimpiada.getIdCategoria() <= 0) {
                return Map.of(
                        "status", "error",
                        "message", "ID de categoría es requerido"
                );
            }

            // Para creación (idCatalogo es 0 o no está definido)
            boolean isCreating = catalogoOlimpiada.getIdCatalogo() == null || catalogoOlimpiada.getIdCatalogo() <= 0;

            if (isCreating) {
                // Verificar si ya existe esta combinación
                if (existsCombination(catalogoOlimpiada.getIdOlimpiada(),
                        catalogoOlimpiada.getIdArea(),
                        catalogoOlimpiada.getIdCategoria())) {
                    return Map.of(
                            "status", "error",
                            "message", "Ya existe esta combinación de área y categoría para esta olimpiada"
                    );
                }
                // Para creación, asegurar que idCatalogo sea 0
                catalogoOlimpiada.setIdCatalogo(null);
            }

            CatalogoOlimpiada saved = catalogoOlimpiadaAdapter.save(catalogoOlimpiada);

            return Map.of(
                    "status", "success",
                    "message", isCreating ? "Catálogo creado exitosamente" : "Catálogo actualizado exitosamente",
                    "data", saved
            );

        } catch (DataIntegrityViolationException e) {
            String errorMessage = parseErrorMessage(e.getMessage());
            return Map.of(
                    "status", "error",
                    "message", errorMessage,
                    "errorDetails", e.getMessage()
            );
        } catch (Exception e) {
            return Map.of(
                    "status", "error",
                    "message", "Error al procesar la solicitud. Por favor intente nuevamente.",
                    "errorDetails", e.getMessage()
            );
        }
    }

    private boolean existsCombination(int idOlimpiada, int idArea, int idCategoria) {
        String sql = "SELECT COUNT(*) FROM catalogo_olimpiada WHERE id_olimpiada = ? AND id_area = ? AND id_categoria = ?";
        try {
            Integer count = jdbcTemplate.queryForObject(sql, Integer.class, idOlimpiada, idArea, idCategoria);
            return count != null && count > 0;
        } catch (Exception e) {
            return false;
        }
    }

    private String parseErrorMessage(String originalMessage) {
        if (originalMessage == null) {
            return "Error desconocido al procesar la solicitud";
        }

        if (originalMessage.contains("Ya existe esta categoría registrada")) {
            return "Ya existe esta categoría registrada en esta área y olimpiada.";
        } else if (originalMessage.contains("No se puede registrar: la categoría")) {
            return "La categoría seleccionada no tiene grados asociados.";
        } else if (originalMessage.contains("Conflicto: Esta categoría comparte grados")) {
            return "Esta categoría comparte grados con otra categoría ya registrada.";
        } else if (originalMessage.contains("No se puede modificar el catálogo mientras la olimpiada está en estado \"EN INSCRIPCION\"")) {
            return "Solo se puede modificar el catálogo cuando la olimpiada está en estado 'PLANIFICACION'.";
        } else {
            return "Error al procesar la solicitud. Por favor intente nuevamente.";
        }
    }

    public List<CatalogoOlimpiadaDTO> findAll() {
        return catalogoOlimpiadaAdapter.findAll();
    }

    public List<CatalogoOlimpiadaDTO> getCatalogoOlimpiadaById(Integer idCatalogoOlimpiada) {
        return catalogoOlimpiadaAdapter.getCatalogoOlimpiadaById(idCatalogoOlimpiada);
    }
}

