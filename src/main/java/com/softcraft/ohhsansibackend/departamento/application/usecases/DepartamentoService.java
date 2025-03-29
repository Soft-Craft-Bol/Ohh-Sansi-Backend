package com.softcraft.ohhsansibackend.departamento.application.usecases;

import com.softcraft.ohhsansibackend.area.domain.models.Area;
import com.softcraft.ohhsansibackend.departamento.application.ports.DepartamentoAdapter;
import com.softcraft.ohhsansibackend.departamento.domain.models.Departamento;
import com.softcraft.ohhsansibackend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DepartamentoService {
    private final DepartamentoAdapter departamentoAdapter;

    @Autowired
    public DepartamentoService(DepartamentoAdapter departamentoAdapter) {
        this.departamentoAdapter = departamentoAdapter;
    }

    public Map<String, Object> getDepartamentoById(int id) {
        Map<String, Object> response = new HashMap<>();
        Departamento departamento = departamentoAdapter.getDepartamentoById(id);
        if (departamento == null) {
            throw new ResourceNotFoundException("Area no encontrada");
        }
        response.put("departamento", departamento);
        return response;
    }

    public Map<String, Object> getAllDepartamentos() {
        try {
            Map<String, Object> response = new HashMap<>();
            response.put("departamentos", departamentoAdapter.getAllDepartamentos());
            return response;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la lista de departamentos");
        }
    }
}
