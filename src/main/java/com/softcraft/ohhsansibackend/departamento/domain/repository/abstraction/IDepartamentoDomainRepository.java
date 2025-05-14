package com.softcraft.ohhsansibackend.departamento.domain.repository.abstraction;

import com.softcraft.ohhsansibackend.departamento.domain.models.Departamento;

import java.util.List;

public interface IDepartamentoDomainRepository {
    List<Departamento> saveAll(List<Departamento> departamentos);

    Departamento getDepartamentoById(int id);

    List<Departamento> getAllDepartamentos();
}
