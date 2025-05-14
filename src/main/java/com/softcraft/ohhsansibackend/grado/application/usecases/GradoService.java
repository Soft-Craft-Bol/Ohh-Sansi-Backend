package com.softcraft.ohhsansibackend.grado.application.usecases;

import com.softcraft.ohhsansibackend.grado.domain.models.Grade;
import com.softcraft.ohhsansibackend.exception.ResourceNotFoundException;
import com.softcraft.ohhsansibackend.grado.application.ports.GradoAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GradoService {
    private final GradoAdapter gradoAdapter;

    @Autowired
    public GradoService(GradoAdapter gradoAdapter) {
        this.gradoAdapter = gradoAdapter;
    }

    public Map<String, Object> saveGrade(Grade grade) {
        try {
            gradoAdapter.saveGrade(grade);
        }catch (DuplicateKeyException e){
            throw new DuplicateKeyException(e.getMessage());
        }
        return Map.of("success", true, "message", "Nivel Escolar creado exitosamente");
    }

    public Grade findGradeById(int idGrado) {
        Grade grade = gradoAdapter.findGradeById(idGrado);
        if(grade == null) {
            throw new ResourceNotFoundException("Categoria con ID" + idGrado + " no encontrada");
        }
        return grade;
    }

    public List<Grade> getGrades() {
        return gradoAdapter.getGrades();
    }

    public Map<String, Object> updateGrade(Grade grade) {
        if (gradoAdapter.findGradeById(grade.getIdGrado()) == null) {
            throw new ResourceNotFoundException("Nivel Escolar con ID " + grade.getIdGrado() + " no encontrada");
        }
        gradoAdapter.updateGrade(grade);
        return Map.of("success", true, "message", "Nivel Escolar actualizado exitosamente");
    }

    public Map<String, Object> deleteGrade(int idGrado) {
        if (gradoAdapter.findGradeById(idGrado) == null) {
            throw new ResourceNotFoundException("Nivel Escolar con ID " + idGrado + " no encontrado");
        }
        gradoAdapter.deleteGrade(idGrado);
        return Map.of("success", true, "message", "Nivel Escolar eliminado exitosamente");
    }
}
