package com.softcraft.ohhsansibackend.tutor.application.ports;

import com.softcraft.ohhsansibackend.tutor.domain.models.TipoTutor;
import com.softcraft.ohhsansibackend.tutor.domain.services.TipoTutorDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TipoTutorAdapter {
    private final TipoTutorDomainService tipoTutorDomainService;

    @Autowired
    public TipoTutorAdapter(TipoTutorDomainService tipoTutorDomainService) {
        this.tipoTutorDomainService = tipoTutorDomainService;
    }
    public TipoTutor save(TipoTutor tipoTutor) {
        return tipoTutorDomainService.save(tipoTutor);
    }
    public TipoTutor update(TipoTutor tipoTutor) {
        return tipoTutorDomainService.update(tipoTutor);
    }
    public void delete(int idTipoTutor) {
        tipoTutorDomainService.delete(idTipoTutor);
    }
    public TipoTutor findByIdTipoTutor(int idTipoTutor) {
        return tipoTutorDomainService.findByIdTipoTutor(idTipoTutor);
    }
    public List<TipoTutor> findAllTipoTutor() {
        return tipoTutorDomainService.findAllTipoTutor();
    }
}