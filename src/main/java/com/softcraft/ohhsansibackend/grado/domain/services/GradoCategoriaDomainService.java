package com.softcraft.ohhsansibackend.grado.domain.services;

import com.softcraft.ohhsansibackend.grado.domain.models.GradoCategoria;
import com.softcraft.ohhsansibackend.grado.domain.repository.implementation.GradoCategoriaDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradoCategoriaDomainService {
    private final GradoCategoriaDomainRepository gradoCategoriaDomainRepository;

    @Autowired
    public GradoCategoriaDomainService(GradoCategoriaDomainRepository gradoCategoriaDomainRepository) {
        this.gradoCategoriaDomainRepository = gradoCategoriaDomainRepository;
    }

    public GradoCategoria createGradoCategoria(GradoCategoria gradoCategoria) {
        return gradoCategoriaDomainRepository.save(gradoCategoria);
    }

}