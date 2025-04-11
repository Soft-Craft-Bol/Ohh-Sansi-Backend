package com.softcraft.ohhsansibackend.grado.application.usecases;

import com.softcraft.ohhsansibackend.grado.domain.models.Grade;
import com.softcraft.ohhsansibackend.exception.ResourceNotFoundException;
import com.softcraft.ohhsansibackend.grado.application.ports.NivelEscolarAdapter;
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

    public Map<String, Object> saveNivelEscolar(Grade grade) {
        try {
            nivelEscolarAdapter.saveNivelEscolar(grade);
        }catch (DuplicateKeyException e){
            throw new DuplicateKeyException(e.getMessage());
        }
        return Map.of("success", true, "message", "Nivel Escolar creado exitosamente");
    }

    public Grade findNivelEscolarById(int idNivel) {
        Grade grade = nivelEscolarAdapter.findNivelEscolarById(idNivel);
        if(grade == null) {
            throw new ResourceNotFoundException("Categoria con ID" + idNivel + " no encontrada");
        }
        return grade;
    }

    public List<Grade> getNivelEscolars() {
        return nivelEscolarAdapter.getNivelEscolars();
    }

    public Map<String, Object> updateNivelEscolar(Grade grade) {
        if (nivelEscolarAdapter.findNivelEscolarById(grade.getIdNivel()) == null) {
            throw new ResourceNotFoundException("Nivel Escolar con ID " + grade.getIdNivel() + " no encontrada");
        }
        nivelEscolarAdapter.updateNivelEscolar(grade);
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
