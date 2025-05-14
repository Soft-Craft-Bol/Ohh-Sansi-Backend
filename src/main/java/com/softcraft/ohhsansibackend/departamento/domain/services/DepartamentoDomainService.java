package com.softcraft.ohhsansibackend.departamento.domain.services;

import com.softcraft.ohhsansibackend.departamento.domain.models.Departamento;
import com.softcraft.ohhsansibackend.departamento.domain.repository.abstraction.IDepartamentoDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoDomainService {
    private final IDepartamentoDomainRepository departamentoDomainRepository;

    @Autowired
    public DepartamentoDomainService(IDepartamentoDomainRepository departamentoDomainRepository) {
        this.departamentoDomainRepository = departamentoDomainRepository;
    }

    public List<Departamento> createDepartamentos(List<Departamento> departamentos) {
        return departamentoDomainRepository.saveAll(departamentos);
    }


    public Departamento getDepartamentoById(int id) {
        return departamentoDomainRepository.getDepartamentoById(id);
    }

    public List<Departamento> getAllDepartamentos() {
        return departamentoDomainRepository.getAllDepartamentos();
    }

}
