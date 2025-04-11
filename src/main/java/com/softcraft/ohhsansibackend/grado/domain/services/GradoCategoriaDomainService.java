package com.softcraft.ohhsansibackend.grado.domain.services;

import com.softcraft.ohhsansibackend.grado.domain.models.GradoCategoria;
import com.softcraft.ohhsansibackend.grado.domain.repository.implementation.NivelEscolarCategoriasDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaDomainService {
    private final NivelEscolarCategoriasDomainRepository nivelEscolarCategoriasDomainRepository;

    @Autowired
    public NivelEscolarCategoriasDomainService(NivelEscolarCategoriasDomainRepository nivelEscolarCategoriasDomainRepository) {
        this.nivelEscolarCategoriasDomainRepository = nivelEscolarCategoriasDomainRepository;
    }

    public NivelEscolarCategorias createNivelEscolarCategorias(NivelEscolarCategorias nivelEscolarCategorias) {
        return nivelEscolarCategoriasDomainRepository.save(nivelEscolarCategorias);
    }

}