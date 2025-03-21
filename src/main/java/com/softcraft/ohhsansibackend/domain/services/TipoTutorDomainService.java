package com.softcraft.ohhsansibackend.domain.services;

import com.softcraft.ohhsansibackend.domain.models.TipoTutor;
import com.softcraft.ohhsansibackend.domain.repository.implementation.TipoTutorDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoTutorDomainService {

    private final TipoTutorDomainRepository tipoTutorDomainRepository;

    @Autowired
    public TipoTutorDomainService(TipoTutorDomainRepository tipoTutorDomainRepository) {
        this.tipoTutorDomainRepository = tipoTutorDomainRepository;
    }
    public TipoTutor save(TipoTutor tipoTutor) {
        return tipoTutorDomainRepository.save(tipoTutor);
    }
    public TipoTutor update(TipoTutor tipoTutor) {
        return tipoTutorDomainRepository.update(tipoTutor);
    }
    public void delete(int idTipoTutor) {
        tipoTutorDomainRepository.delete(idTipoTutor);
    }
    public TipoTutor findByIdTipoTutor(int idTipoTutor) {
        return tipoTutorDomainRepository.findByIdTipoTutor(idTipoTutor);
    }
    public List<TipoTutor> findAllTipoTutor() {
        return tipoTutorDomainRepository.findAllTipoTutor();
    }

}
