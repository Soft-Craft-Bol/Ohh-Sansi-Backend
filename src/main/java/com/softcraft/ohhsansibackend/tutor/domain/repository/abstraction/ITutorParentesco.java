package com.softcraft.ohhsansibackend.tutor.domain.repository.abstraction;

import com.softcraft.ohhsansibackend.tutor.domain.models.TutorParentesco;

import java.util.List;

public interface ITutorParentesco {
    TutorParentesco save(TutorParentesco tutorParentesco);
    TutorParentesco update(TutorParentesco tutorParentesco);
    void delete(int idTutorParentesco);
    TutorParentesco findByIdTutorParentesco(int idTutorParentesco);
    List<TutorParentesco> findAllParentescos();
}
