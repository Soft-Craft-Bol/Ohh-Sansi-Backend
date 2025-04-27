package com.softcraft.ohhsansibackend.departamento.application.ports;

import com.softcraft.ohhsansibackend.departamento.domain.models.Departamento;
import com.softcraft.ohhsansibackend.departamento.domain.services.DepartamentoDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartamentoAdapter {
    private final DepartamentoDomainService departamentoDomainService;

    @Autowired
    public DepartamentoAdapter(DepartamentoDomainService departamentoDomainService) {
        this.departamentoDomainService = departamentoDomainService;
    }

    public List<Departamento> saveDepartamentos(List<Departamento> departamentos) {
        return departamentoDomainService.createDepartamentos(departamentos);
    }


    public Departamento getDepartamentoById(int id) {
        return departamentoDomainService.getDepartamentoById(id);
    }

    public List<Departamento> getAllDepartamentos() {
        return departamentoDomainService.getAllDepartamentos();
    }
}
