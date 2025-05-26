package com.softcraft.ohhsansibackend.catalogoolimpiadas.application.usecases;

import com.softcraft.ohhsansibackend.catalogoolimpiadas.application.adapter.CatalogoOlimpiadaAdapter;
import com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.DTO.CatalogoOlimpiadaDTO;
import com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.model.CatalogoOlimpiada;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CatalogoOlimpiadaService {

    private final CatalogoOlimpiadaAdapter catalogoOlimpiadaAdapter;

    public CatalogoOlimpiadaService(CatalogoOlimpiadaAdapter catalogoOlimpiadaAdapter) {
        this.catalogoOlimpiadaAdapter = catalogoOlimpiadaAdapter;
    }

    public Map<String, Object> save(CatalogoOlimpiada catalogoOlimpiada) {
        try {
            CatalogoOlimpiada saved = catalogoOlimpiadaAdapter.save(catalogoOlimpiada);
            return Map.of(
                    "status", "success",
                    "message", "Catálogo guardado exitosamente",
                    "data", saved
            );
        } catch (Exception e) {
            String errorMessage = e.getMessage();
            if (errorMessage.contains("Ya existe esta categoría registrada")) {
                errorMessage = "Ya existe esta categoría registrada en esta área y olimpiada.";
            } else if (errorMessage.contains("No se puede registrar: la categoría")) {
                errorMessage = "La categoría seleccionada no tiene grados asociados.";
            } else if (errorMessage.contains("Conflicto: Esta categoría comparte grados")) {
                errorMessage = "Esta categoría comparte grados con otra categoría ya registrada en esta área y olimpiada.";
            } else if (errorMessage.contains("Solo se puede modificar el catálogo en estado PLANIFICACION o HISTORICO")) {
                errorMessage = "Solo se puede modificar el catálogo cuando la olimpiada está en estado 'PLANIFICACION'.";
            } else if (errorMessage.contains("No se puede eliminar el catálogo")) {
                errorMessage = "No se puede eliminar el catálogo porque ya tiene inscripciones asociadas.";
            } else {
                errorMessage = "Error al guardar el catálogo. Intenta de nuevo.";
            }

            return Map.of(
                    "status", "error",
                    "message", errorMessage
            );
        }
    }

    public List<CatalogoOlimpiadaDTO> findAll() {
        return catalogoOlimpiadaAdapter.findAll();
    }

    public List<CatalogoOlimpiadaDTO> getCatalogoOlimpiadaById(Integer idCatalogoOlimpiada) {
        return catalogoOlimpiadaAdapter.getCatalogoOlimpiadaById(idCatalogoOlimpiada);
    }
}

