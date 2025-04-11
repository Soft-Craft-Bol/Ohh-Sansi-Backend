package com.softcraft.ohhsansibackend.inscripcion.domain.services;

import com.softcraft.ohhsansibackend.inscripcion.domain.models.Inscripcion;
import com.softcraft.ohhsansibackend.inscripcion.domain.repository.implementation.InscripcionDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class InscripcionDomainService {
    private final InscripcionDomainRepository inscripcionDomainRepository;
    @Autowired
    public InscripcionDomainService( InscripcionDomainRepository inscripcionDomainRepository) {
        this.inscripcionDomainRepository = inscripcionDomainRepository;
    }

    public Inscripcion createInscripcion(Inscripcion inscripcion) {
        return inscripcionDomainRepository.saveInscripcion(inscripcion);
    }

    public Inscripcion getInscripcion(int id) {
        return inscripcionDomainRepository.findByIdInscripcion(id);
    }

    public List<Inscripcion> listInscripcion() {
        return inscripcionDomainRepository.findAllInscripcion();
    }

    public Long findIdByCodigoUnico(String codigoUnico) {
        return inscripcionDomainRepository.findIdByCodigoUnico(codigoUnico);
    }

    //askldjakls
    public List<Map<String, Object>> getInscripcionById(int idInscripcion) {
        return inscripcionDomainRepository.findInscripcionById(idInscripcion);
    }

    public List<Map<String, Object>> getParticipantesByInscripcionId(int idInscripcion) {
        return inscripcionDomainRepository.findParticipantesByInscripcionId(idInscripcion);
    }

    public List<Map<String, Object>> getInscripcionAreasByInscripcionId(int idInscripcion) {
        return inscripcionDomainRepository.findInscripcionAreasByInscripcionId(idInscripcion);
    }

    public List<Map<String, Object>> getAreasByInscripcionId(int idInscripcion) {
        return inscripcionDomainRepository.findAreasByInscripcionId(idInscripcion);
    }

    public List<Map<String, Object>> getTutoresByInscripcionId(int idInscripcion) {
        return inscripcionDomainRepository.findTutoresByInscripcionId(idInscripcion);
    }


}
