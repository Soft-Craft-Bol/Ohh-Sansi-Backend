package com.softcraft.ohhsansibackend.municipio.domain.repository.abstraction;

import com.softcraft.ohhsansibackend.municipio.domain.models.Municipio;

import java.util.List;

public interface IMunicipioDomainRepository {
    List<Municipio> saveAll(List<Municipio> municipios);

    Municipio getMunicipioById(int id);

    List<Municipio> getAllMunicipios();

    List<Municipio> getMunicipiosByDepartamento(int idDepartamento);
}
