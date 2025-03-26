package com.softcraft.ohhsansibackend.municipio.application.ports;

import com.softcraft.ohhsansibackend.municipio.domain.models.Municipio;
import com.softcraft.ohhsansibackend.municipio.domain.services.MunicipioDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MunicipioAdapter {
    private final MunicipioDomainService municipioDomainService;

    @Autowired
    public MunicipioAdapter(MunicipioDomainService municipioDomainService) {
        this.municipioDomainService = municipioDomainService;
    }

    public Municipio getMunicipioById(int id) {
        return municipioDomainService.getMunicipioById(id);
    }

    public List<Municipio> getAllMunicipios() {
        return municipioDomainService.getAllMunicipios();
    }

    public List<Municipio> getMunicipiosByDepartamento(int idDepartamento) {
        return municipioDomainService.getMunicipiosByDepartamento(idDepartamento);
    }
}
