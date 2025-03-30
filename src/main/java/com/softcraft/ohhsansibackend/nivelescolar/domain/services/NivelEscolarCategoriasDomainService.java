package com.softcraft.ohhsansibackend.nivelescolar.domain.services;

import com.softcraft.ohhsansibackend.nivelescolar.domain.models.NivelEscolarCategorias;
import com.softcraft.ohhsansibackend.nivelescolar.domain.repository.abstraction.INivelEscolarCategoriasRepository;
import com.softcraft.ohhsansibackend.nivelescolar.domain.repository.implementation.NivelEscolarCategoriasDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NivelEscolarCategoriasDomainService {
    private final NivelEscolarCategoriasDomainRepository nivelEscolarCategoriasDomainRepository;

    @Autowired
    public NivelEscolarCategoriasDomainService(NivelEscolarCategoriasDomainRepository nivelEscolarCategoriasDomainRepository) {
        this.nivelEscolarCategoriasDomainRepository = nivelEscolarCategoriasDomainRepository;
    }

    public NivelEscolarCategorias createNivelEscolarCategorias(NivelEscolarCategorias nivelEscolarCategorias) {
        return nivelEscolarCategoriasDomainRepository.save(nivelEscolarCategorias);
    }

}