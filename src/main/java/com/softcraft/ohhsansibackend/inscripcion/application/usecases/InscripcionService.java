package com.softcraft.ohhsansibackend.inscripcion.application.usecases;

import com.softcraft.ohhsansibackend.inscripcion.domain.models.Inscripcion;
import com.softcraft.ohhsansibackend.exception.ResourceNotFoundException;
import com.softcraft.ohhsansibackend.inscripcion.application.ports.InscripcionAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
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
            Inscripcion nuevaInscripcion = inscripcionAdapter.saveInscripcion(inscripcion);
            return Map.of("success", true, "message", "Inscripción registrada exitosamente",
                    "data", nuevaInscripcion
            );
        } catch (Exception e) {
            return Map.of("success", false, "message", "Error al registrar la inscripción");
        }
    }
    public Inscripcion findInscripcionById(int id) {
        Inscripcion inscripcion = inscripcionAdapter.findInscripcionById(id);
        if (inscripcion == null) {
            throw new ResourceNotFoundException("Inscripcion con ID " + id + " no encontrada");
        }
        return inscripcion;
    }

    public List<Inscripcion> getInscripciones() {
        return inscripcionAdapter.findAllInscripciones();
    }

    public List<Inscripcion> findByDateAndTime(Date date, Time time) {
        return inscripcionAdapter.findByDateAndTime(date, time);
    }

    public List<Inscripcion> findByRangeDate(LocalDate fechaInicio, LocalDate fechaFin) {
        return inscripcionAdapter.findByRangeDate(fechaInicio, fechaFin);
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
        boolean deleted = inscripcionAdapter.deleteInscripcion(id);

        if (!deleted) {
            return Map.of("success", false, "message", "Error al eliminar la inscripción");
        }

        return Map.of("success", true, "message", "Inscripción eliminada exitosamente");
    }

}
