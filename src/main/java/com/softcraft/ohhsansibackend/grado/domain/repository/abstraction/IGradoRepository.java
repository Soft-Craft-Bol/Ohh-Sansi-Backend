package com.softcraft.ohhsansibackend.grado.domain.repository.abstraction;

import com.softcraft.ohhsansibackend.grado.domain.models.Grade;

import java.util.List;

public interface IGradoRepository {
    Grade save(Grade grade);

    boolean update(Grade grado);

    boolean delete(int idGrado);

    Grade findById(int idGrado);

    List<Grade> findAll();
}
