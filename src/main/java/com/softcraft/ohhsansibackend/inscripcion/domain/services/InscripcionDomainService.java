package com.softcraft.ohhsansibackend.inscripcion.domain.services;

import com.softcraft.ohhsansibackend.inscripcion.domain.models.Inscripcion;
import com.softcraft.ohhsansibackend.inscripcion.domain.repository.abstraction.IInscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscripcionDomainService {
    private final IInscripcionRepository inscripcionRepository;

    @Autowired
    public InscripcionDomainService(IInscripcionRepository inscripcionRepository) {
        this.inscripcionRepository = inscripcionRepository;
    }

    public Inscripcion createInscripcion(Inscripcion inscripcion) {
        return inscripcionRepository.saveInscripcion(inscripcion);
    }

    public Inscripcion getInscripcion(int id) {
        return inscripcionRepository.findByIdInscripcion(id);
    }

    public List<Inscripcion> listInscripcion() {
        return inscripcionRepository.findAllInscripcion();
    }

    public boolean updateInscripcion(Inscripcion inscripcion) {
        return inscripcionRepository.updateInscription(inscripcion);
    }

    public boolean deleteInscripcion(int id) {
        return inscripcionRepository.deleteInscripcion(id);
    }

    public List<Inscripcion> findByDateAndTime(String date, String time) {
        return inscripcionRepository.findByDateAndTime(date, time);
    }

    public List<Inscripcion> findByRangeDate(String date) {
        return inscripcionRepository.findByDate(date);
    }
}
