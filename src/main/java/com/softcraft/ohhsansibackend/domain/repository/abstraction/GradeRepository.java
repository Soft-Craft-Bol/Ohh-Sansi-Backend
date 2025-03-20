package com.softcraft.ohhsansibackend.domain.repository.abstraction;

import com.softcraft.ohhsansibackend.domain.models.Grade;

import java.util.List;
import java.util.Optional;

public interface GradeRepository {
    void save(Grade grade);

    void update(Grade grade);

    void delete(Long idGrade);

    Optional<Grade> findById(Long idGrade);

    List<Grade> findAll();
}
