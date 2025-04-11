package com.softcraft.ohhsansibackend.inscripcion.application.ports;

import com.softcraft.ohhsansibackend.inscripcion.domain.models.Inscripcion;
import com.softcraft.ohhsansibackend.inscripcion.domain.services.InscripcionDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;


@Repository
public class InscripcionAdapter {
    private final InscripcionDomainService inscripcionDomainService;

    @Autowired
    public InscripcionAdapter(InscripcionDomainService inscripcionDomainService) {
        this.inscripcionDomainService = inscripcionDomainService;
    }

    public Inscripcion saveInscripcion(Inscripcion inscripcion) {
        return inscripcionDomainService.createInscripcion(inscripcion);
    }

    public boolean updateInscripcion(Inscripcion inscripcion) {
        return inscripcionDomainService.updateInscripcion(inscripcion);
    }

    public boolean deleteInscripcion(int id) {
        return inscripcionDomainService.deleteInscripcion(id);
    }

    public Inscripcion findInscripcionById(int id) {
        return inscripcionDomainService.getInscripcion(id);
    }

    public List<Inscripcion> findAllInscripciones() {
        return inscripcionDomainService.listInscripcion();
    }

    public List<Inscripcion> findByDateAndTime(Date date, Time time) {
        return inscripcionDomainService.findByDateAndTime(date, time);
    }

    public List<Inscripcion> findByRangeDate(LocalDate fechaInicio, LocalDate fechaFin) {
        return inscripcionDomainService.findByRangeDate(fechaInicio, fechaFin);
    }

    public Long findIdByCodigoUnico(String codigoUnico) {
        return inscripcionDomainService.findIdByCodigoUnico(codigoUnico);
    }
}
