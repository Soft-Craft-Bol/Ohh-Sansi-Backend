package com.softcraft.ohhsansibackend.grado.application.usecases;

import com.softcraft.ohhsansibackend.grado.application.ports.GradoCategoriaAdapter;
import com.softcraft.ohhsansibackend.grado.domain.models.GradoCategoria;
import com.softcraft.ohhsansibackend.grado.domain.repository.implementation.GradoCategoriaDomainRepository;
import com.softcraft.ohhsansibackend.grado.infraestructure.request.GradoCategoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return gradoCategoriaAdapter.saveGradoCategoria(gradoCategoria);
    }

    public List<GradoCategoriaDTO> getGradoCategorias() {
        return gradoCategoriaAdapter.getGradoCategorias();
    }




}
