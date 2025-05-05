package com.softcraft.ohhsansibackend.tutor.domain.services;

import com.softcraft.ohhsansibackend.tutor.domain.models.TutorParentesco;
import com.softcraft.ohhsansibackend.tutor.domain.repository.implementation.TutorParentescoDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TutorParentescoDomainService {
    private final TutorParentescoDomainRepository tutorParentescoDomainRepository;

    @Autowired
    public TutorParentescoDomainService(TutorParentescoDomainRepository tutorParentescoDomainRepository) {
        this.tutorParentescoDomainRepository = tutorParentescoDomainRepository;
    }
    public TutorParentesco save(TutorParentesco tutorParentesco) {
        return tutorParentescoDomainRepository.save(tutorParentesco);
    }
    public TutorParentesco update(TutorParentesco tutorParentesco) {
        return tutorParentescoDomainRepository.update(tutorParentesco);
    }
    public void delete(int idTutorParentesco) {
        tutorParentescoDomainRepository.delete(idTutorParentesco);
    }
    public TutorParentesco findByIdTutorParentesco(int idTutorParentesco) {
        return tutorParentescoDomainRepository.findByIdTutorParentesco(idTutorParentesco);
    }
    public List<TutorParentesco> findAllParentescos() {
        return tutorParentescoDomainRepository.findAllParentescos();
    }
}
