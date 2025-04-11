package com.softcraft.ohhsansibackend.grado.application.usecases;

import com.softcraft.ohhsansibackend.grado.application.ports.NivelEscolarCategoriasAdapter;
import com.softcraft.ohhsansibackend.grado.domain.models.NivelEscolarCategorias;
import com.softcraft.ohhsansibackend.grado.domain.repository.implementation.GradoCategoriaDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NivelEscolarCategoriaService {

    private final NivelEscolarCategoriasAdapter nivelEscolarCategoriasAdapter;
    private final GradoCategoriaDomainRepository gradoCategoriaDomainRepository;

    @Autowired
    public NivelEscolarCategoriaService(NivelEscolarCategoriasAdapter nivelEscolarCategoriasAdapter, GradoCategoriaDomainRepository gradoCategoriaDomainRepository) {
        this.nivelEscolarCategoriasAdapter = nivelEscolarCategoriasAdapter;
        this.gradoCategoriaDomainRepository = gradoCategoriaDomainRepository;
    }

    public NivelEscolarCategorias saveNivelEscolarCategorias(NivelEscolarCategorias nivelEscolarCategorias) {
        return gradoCategoriaDomainRepository.save(nivelEscolarCategorias);
    }


}
