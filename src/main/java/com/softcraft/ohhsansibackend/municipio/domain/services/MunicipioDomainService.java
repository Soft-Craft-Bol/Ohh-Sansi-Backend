package com.softcraft.ohhsansibackend.municipio.domain.services;

import com.softcraft.ohhsansibackend.municipio.domain.models.Municipio;
import com.softcraft.ohhsansibackend.municipio.domain.repository.abstraction.IMunicipioDomainRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MunicipioDomainService {
    private final IMunicipioDomainRepository municipioDomainRepository;

    public MunicipioDomainService(IMunicipioDomainRepository municipioDomainRepository) {
        this.municipioDomainRepository = municipioDomainRepository;
    }

    public Municipio getMunicipioById(int id) {
        return municipioDomainRepository.getMunicipioById(id);
    }

    public List<Municipio> getAllMunicipios() {
        return municipioDomainRepository.getAllMunicipios();
    }

    public List<Municipio> getMunicipiosByDepartamento(int idDepartamento) {
        return municipioDomainRepository.getMunicipiosByDepartamento(idDepartamento);
    }
}
