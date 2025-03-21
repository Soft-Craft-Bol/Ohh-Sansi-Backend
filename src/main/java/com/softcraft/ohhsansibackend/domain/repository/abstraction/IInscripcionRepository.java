package com.softcraft.ohhsansibackend.domain.repository.abstraction;

import com.softcraft.ohhsansibackend.domain.models.Inscripcion;

import java.util.List;


public interface IInscripcionRepository {
    Inscripcion saveInscripcion(Inscripcion inscripcion);

    boolean updateInscription(Inscripcion inscripcion);

    boolean deleteInscripcion(int idInscripcion);

    Inscripcion findByIdInscripcion(int idInscripcion);

    List <Inscripcion> findAllInscripcion();

    List<Inscripcion> findByDateAndTime(String date, String time);

    List<Inscripcion> findByDate(String date);
}
