package com.softcraft.ohhsansibackend.inscripcion.application.usecases;

import com.softcraft.ohhsansibackend.inscripcion.domain.models.Inscripcion;
import com.softcraft.ohhsansibackend.exception.ResourceNotFoundException;
import com.softcraft.ohhsansibackend.inscripcion.application.ports.InscripcionAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Service
public class InscripcionService {
    private final InscripcionAdapter inscripcionAdapter;

    @Autowired
    public InscripcionService(InscripcionAdapter inscripcionAdapter) {
        this.inscripcionAdapter = inscripcionAdapter;
    }

    public Map<String, Object> saveInscripcion(Inscripcion inscripcion) {
        try {
            inscripcionAdapter.saveInscripcion(inscripcion);
        }catch (DuplicateKeyException e){
            throw new DuplicateKeyException(e.getMessage());
        }
        return Map.of("success", true, "message", "Inscripcion registrada exitosamente");
    }

    public Inscripcion findInscripcionById(int id) {
        Inscripcion inscripcion = inscripcionAdapter.findInscripcionById(id);
        if(inscripcion == null) {
            throw new ResourceNotFoundException("Inscripcion con fecha " + id + " no encontrada");
        }
        return inscripcionAdapter.findInscripcionById(id);
    }

    public List<Inscripcion> getInscripciones() {
        return inscripcionAdapter.findAllInscripciones();
    }

    public List<Inscripcion> findByDateAndTime(String date, String time) {
        return inscripcionAdapter.findByDateAndTime(date, time);
    }

    public List<Inscripcion> findByRangeDate(String date) {
        return inscripcionAdapter.findByRangeDate(date);
    }

    public Map<String, Object> updateInscripcion(Inscripcion inscripcion) {
        if(inscripcionAdapter.findInscripcionById(inscripcion.getIdInscripcion()) == null) {
            throw new ResourceNotFoundException("Inscripcion con ID " + inscripcion.getIdInscripcion() + " no encontrada");
        }
        inscripcionAdapter.updateInscripcion(inscripcion);
        return Map.of("success", true, "message", "Inscripcion actualizada exitosamente");
    }

    public Map<String, Object> deleteInscripcion(int id) {
        if (inscripcionAdapter.findInscripcionById(id) == null) {
            throw new ResourceNotFoundException("Inscripcion con ID " + id + " no encontrada");
        }
        inscripcionAdapter.deleteInscripcion(id);
        return Map.of("success", true, "message", "Inscripcion eliminada exitosamente");
    }

    //devolucion
    public int createInscripcionAndReturnId(Inscripcion inscripcion) {
        inscripcion.setFechaInscripcion(Date.valueOf(LocalDate.now()));
        inscripcion.setHoraInscripcion(Time.valueOf(LocalTime.now()));
        Inscripcion savedInscripcion = inscripcionAdapter.saveInscripcion(inscripcion);
        return savedInscripcion.getIdInscripcion();
    }
}
