package com.softcraft.ohhsansibackend.colegio.domain.services;

import com.softcraft.ohhsansibackend.colegio.domain.models.Colegio;
import com.softcraft.ohhsansibackend.colegio.domain.repository.abstraction.IColegioDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ColegioDomainService {
    private final IColegioDomainRepository colegioDomainRepository;


    @Autowired
    public ColegioDomainService(IColegioDomainRepository colegioDomainRepository) {
        this.colegioDomainRepository = colegioDomainRepository;
    }

    public List<Colegio> getColegiosByMunicipio(int idMunicipio) {
        return colegioDomainRepository.getColegiosByMunicipio(idMunicipio);
    }

}
