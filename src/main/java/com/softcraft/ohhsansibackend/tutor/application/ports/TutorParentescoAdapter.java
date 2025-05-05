package com.softcraft.ohhsansibackend.tutor.application.ports;

import com.softcraft.ohhsansibackend.tutor.domain.models.TutorParentesco;
import com.softcraft.ohhsansibackend.tutor.domain.services.TutorParentescoDomainService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TutorParentescoAdapter {
    private final TutorParentescoDomainService tutorParentescoDomainService;

    @Autowired
    public TutorParentescoAdapter (TutorParentescoDomainService tutorParentescoDomainService){
        this.tutorParentescoDomainService = tutorParentescoDomainService;
    }
    public TutorParentesco save(TutorParentesco tutorParentesco) {
        return tutorParentescoDomainService.save(tutorParentesco);
    }
    public TutorParentesco update(TutorParentesco tutorParentesco) {
        return tutorParentescoDomainService.update(tutorParentesco);
    }
    public void delete(int idTutorParentesco) {
        tutorParentescoDomainService.delete(idTutorParentesco);
    }
    public TutorParentesco findByIdTutorParentesco(int idTutorParentesco) {
        return tutorParentescoDomainService.findByIdTutorParentesco(idTutorParentesco);
    }
    public List<TutorParentesco> findAllParentescos() {
        return tutorParentescoDomainService.findAllParentescos();
    }
}
