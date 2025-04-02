package com.softcraft.ohhsansibackend.colegio.application.ports;

import com.softcraft.ohhsansibackend.colegio.domain.models.Colegio;
import com.softcraft.ohhsansibackend.colegio.domain.services.ColegioDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ColegioAdapter {
    private final ColegioDomainService colegioDomainService;

    @Autowired
    public ColegioAdapter(ColegioDomainService colegioDomainService) {
        this.colegioDomainService = colegioDomainService;
    }

    public List<Colegio> getColegiosByMunicipio(int idDepartamento) {
        return colegioDomainService.getColegiosByMunicipio(idDepartamento);
    }
}
