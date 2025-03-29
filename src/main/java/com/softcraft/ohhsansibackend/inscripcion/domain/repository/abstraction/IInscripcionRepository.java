package com.softcraft.ohhsansibackend.inscripcion.domain.repository.abstraction;

import com.softcraft.ohhsansibackend.inscripcion.domain.models.Inscripcion;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;


public interface IInscripcionRepository {
    Inscripcion saveInscripcion(Inscripcion inscripcion);

    boolean updateInscription(Inscripcion inscripcion);

    boolean deleteInscripcion(int idInscripcion);

    Inscripcion findByIdInscripcion(int idInscripcion);

    List <Inscripcion> findAllInscripcion();

    List<Inscripcion> findByDateAndTime(Date date, Time time);

    List<Inscripcion> findByRangeDate(LocalDate fechaInicio, LocalDate fechaFin);
}
