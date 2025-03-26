package com.softcraft.ohhsansibackend.nivelescolar.domain.services;

import com.softcraft.ohhsansibackend.nivelescolar.domain.models.NivelEscolarCategorias;
import com.softcraft.ohhsansibackend.nivelescolar.domain.repository.abstraction.INivelEscolarCategoriasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NivelEscolarCategoriasDomainService {
    private final INivelEscolarCategoriasRepository INivelEscolarCategoriasRepository;

    @Autowired
    public NivelEscolarCategoriasDomainService(INivelEscolarCategoriasRepository INivelEscolarCategoriasRepository) {
        this.INivelEscolarCategoriasRepository = INivelEscolarCategoriasRepository;
    }

    public NivelEscolarCategorias createNivelEscolarCategorias(NivelEscolarCategorias nivelEscolarCategorias) {
        return INivelEscolarCategoriasRepository.save(nivelEscolarCategorias);
    }

    public boolean updateNivelEscolarCategorias(NivelEscolarCategorias nivelEscolarCategorias) {
        return INivelEscolarCategoriasRepository.update(nivelEscolarCategorias);
    }

    public boolean deleteNivelEscolarCategorias(int id) {
        return INivelEscolarCategoriasRepository.delete(id);
    }

    public NivelEscolarCategorias findNivelEscolarCategoriasById(int id) {
        return INivelEscolarCategoriasRepository.findById(id);
    }

    public List<NivelEscolarCategorias> getNivelEscolarCategoriass() {
        return INivelEscolarCategoriasRepository.findAll();
    }
}