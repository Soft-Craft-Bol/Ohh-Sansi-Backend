package com.softcraft.ohhsansibackend.inscripcion.domain.services;

import com.softcraft.ohhsansibackend.inscripcion.domain.models.InscripcionArea;
import com.softcraft.ohhsansibackend.inscripcion.domain.repository.abstraction.IInscripcionAreaDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscripcionAreaDomainService {
    private final IInscripcionAreaDomainRepository inscripcionAreaDomainRepository;

    @Autowired
    public InscripcionAreaDomainService(IInscripcionAreaDomainRepository inscripcionAreaDomainRepository) {
        this.inscripcionAreaDomainRepository = inscripcionAreaDomainRepository;
    }

    public InscripcionArea createInscripcionArea(int idInscripcion, int idArea) {
        return inscripcionAreaDomainRepository.insertInscripcionArea(idInscripcion, idArea);
    }

    public List<InscripcionArea> listInscripcionArea() {
        return inscripcionAreaDomainRepository.findAllInscripcionAreas();
    }

    public List<InscripcionArea> listInscripcionAreaByArea(int idArea) {
        return inscripcionAreaDomainRepository.findByAreaId(idArea);
    }

   public List<InscripcionArea> listInscripcionAreaByInscripcionId(int idInscripcion) {
        return inscripcionAreaDomainRepository.findByInscripcionId(idInscripcion);
   }


}
