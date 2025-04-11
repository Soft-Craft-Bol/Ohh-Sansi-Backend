package com.softcraft.ohhsansibackend.grado.application.usecases;

import com.softcraft.ohhsansibackend.grado.application.ports.GradoCategoriaAdapter;
import com.softcraft.ohhsansibackend.grado.domain.models.GradoCategoria;
import com.softcraft.ohhsansibackend.grado.domain.repository.implementation.GradoCategoriaDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradoCategoriaService {

    private final GradoCategoriaAdapter gradoCategoriaAdapter;
    private final GradoCategoriaDomainRepository gradoCategoriaDomainRepository;

    @Autowired
    public GradoCategoriaService(GradoCategoriaAdapter gradoCategoriaAdapter, GradoCategoriaDomainRepository gradoCategoriaDomainRepository) {
        this.gradoCategoriaAdapter = gradoCategoriaAdapter;
        this.gradoCategoriaDomainRepository = gradoCategoriaDomainRepository;
    }

    public GradoCategoria saveGradoCategoria(GradoCategoria gradoCategoria) {
        return gradoCategoriaDomainRepository.save(gradoCategoria);
    }


}
