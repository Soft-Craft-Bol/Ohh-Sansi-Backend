package com.softcraft.ohhsansibackend.grado.domain.services;

import com.softcraft.ohhsansibackend.grado.domain.models.GradoCategoria;
import com.softcraft.ohhsansibackend.grado.domain.repository.implementation.GradoCategoriaDomainRepository;
import com.softcraft.ohhsansibackend.grado.infraestructure.request.GradoCategoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<GradoCategoriaDTO> getGradoCategorias() {
        return gradoCategoriaDomainRepository.findAll();
    }

}