package com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.services;

import com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.DTO.CatalogoOlimpiadaDTO;
import com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.model.CatalogoOlimpiada;
import com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.repository.abstraction.ICatalogoOlimpiadaRepository;
import com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.repository.implementation.CatalogoOlimpiadaDomainRepository;
import com.softcraft.ohhsansibackend.participante.infraestructure.request.AreaCatalogoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogoOlimpiadaDomainService {
    private final ICatalogoOlimpiadaRepository catalogoOlimpiadaRepository;
    private final CatalogoOlimpiadaDomainRepository catalogoOlimpiadaDomainRepository;
    public CatalogoOlimpiadaDomainService(
            ICatalogoOlimpiadaRepository catalogoOlimpiadaRepository,
            CatalogoOlimpiadaDomainRepository catalogoOlimpiadaDomainRepository

    ) {
        this.catalogoOlimpiadaRepository = catalogoOlimpiadaRepository;
        this.catalogoOlimpiadaDomainRepository = catalogoOlimpiadaDomainRepository;
    }

    public CatalogoOlimpiada save(CatalogoOlimpiada catalogoOlimpiada) {
        return catalogoOlimpiadaRepository.save(catalogoOlimpiada);
    }

    public List<CatalogoOlimpiadaDTO> findAll() {
        return catalogoOlimpiadaRepository.findAll();
    }

    public List<CatalogoOlimpiadaDTO> getCatalogoOlimpiadaById(Integer idCatalogoOlimpiada) {
        return catalogoOlimpiadaRepository.getCatalogoOlimpiadaById(idCatalogoOlimpiada);
    }
    public List<AreaCatalogoDTO> getAreaCatalogoByIdAreaAndIdOlimpiada(int idArea,int idArea2 ,int idOlimpiada, int idGrado) {
        return catalogoOlimpiadaDomainRepository.getAreaCatalogoByIdAreaAndIdOlimpiada(idArea,idArea2, idOlimpiada, idGrado);
    }
}
