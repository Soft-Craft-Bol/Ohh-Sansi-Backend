package com.softcraft.ohhsansibackend.nivelescolar.application.usecases;

import com.softcraft.ohhsansibackend.nivelescolar.domain.models.NivelEscolar;
import com.softcraft.ohhsansibackend.exception.ResourceNotFoundException;
import com.softcraft.ohhsansibackend.nivelescolar.application.ports.NivelEscolarAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NivelEscolarService {
    private final NivelEscolarAdapter nivelEscolarAdapter;

    @Autowired
    public NivelEscolarService(NivelEscolarAdapter nivelEscolarAdapter) {
        this.nivelEscolarAdapter = nivelEscolarAdapter;
    }

    public Map<String, Object> saveNivelEscolar(NivelEscolar nivelEscolar) {
        try {
            nivelEscolarAdapter.saveNivelEscolar(nivelEscolar);
        }catch (DuplicateKeyException e){
            throw new DuplicateKeyException(e.getMessage());
        }
        return Map.of("success", true, "message", "Nivel Escolar creado exitosamente");
    }

    public NivelEscolar findNivelEscolarById(int idNivel) {
        NivelEscolar nivelEscolar = nivelEscolarAdapter.findNivelEscolarById(idNivel);
        if(nivelEscolar == null) {
            throw new ResourceNotFoundException("Categoria con ID" + idNivel + " no encontrada");
        }
        return nivelEscolar;
    }

    public List<NivelEscolar> getNivelEscolars() {
        return nivelEscolarAdapter.getNivelEscolars();
    }

    public Map<String, Object> updateNivelEscolar(NivelEscolar nivelEscolar) {
        if (nivelEscolarAdapter.findNivelEscolarById(nivelEscolar.getIdNivel()) == null) {
            throw new ResourceNotFoundException("Nivel Escolar con ID " + nivelEscolar.getIdNivel() + " no encontrada");
        }
        nivelEscolarAdapter.updateNivelEscolar(nivelEscolar);
        return Map.of("success", true, "message", "Nivel Escolar actualizado exitosamente");
    }

    public Map<String, Object> deleteNivelEscolar(int idNivel) {
        if (nivelEscolarAdapter.findNivelEscolarById(idNivel) == null) {
            throw new ResourceNotFoundException("Nivel Escolar con ID " + idNivel + " no encontrado");
        }
        nivelEscolarAdapter.deleteNivelEscolar(idNivel);
        return Map.of("success", true, "message", "Nivel Escolar eliminado exitosamente");
    }
}
