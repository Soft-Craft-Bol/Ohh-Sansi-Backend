package com.softcraft.ohhsansibackend.nivelescolar.application.usecases;

import com.softcraft.ohhsansibackend.nivelescolar.application.ports.NivelEscolarCategoriasAdapter;
import com.softcraft.ohhsansibackend.nivelescolar.domain.models.NivelEscolarCategorias;
import com.softcraft.ohhsansibackend.nivelescolar.domain.repository.implementation.NivelEscolarCategoriasDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Service;

@Service
public class NivelEscolarCategoriaService {

    private final NivelEscolarCategoriasAdapter nivelEscolarCategoriasAdapter;
    private final NivelEscolarCategoriasDomainRepository nivelEscolarCategoriasDomainRepository;

    @Autowired
    public NivelEscolarCategoriaService(NivelEscolarCategoriasAdapter nivelEscolarCategoriasAdapter, NivelEscolarCategoriasDomainRepository nivelEscolarCategoriasDomainRepository) {
        this.nivelEscolarCategoriasAdapter = nivelEscolarCategoriasAdapter;
        this.nivelEscolarCategoriasDomainRepository = nivelEscolarCategoriasDomainRepository;
    }

    public NivelEscolarCategorias saveNivelEscolarCategorias(NivelEscolarCategorias nivelEscolarCategorias) {
        return nivelEscolarCategoriasDomainRepository.save(nivelEscolarCategorias);
    }

}
