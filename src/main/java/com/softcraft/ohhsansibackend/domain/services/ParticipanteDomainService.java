package com.softcraft.ohhsansibackend.domain.services;

import com.softcraft.ohhsansibackend.domain.models.Participante;
import com.softcraft.ohhsansibackend.domain.repository.implementation.ParticipanteDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipanteDomainService {
    private final ParticipanteDomainRepository participanteRepository;
    @Autowired
    public ParticipanteDomainService(ParticipanteDomainRepository participanteRepository) {
        this.participanteRepository = participanteRepository;
    }
    public Participante save(Participante participante){
        return participanteRepository.save(participante);
    }
    public Participante findById(Long idParticipante){
        return participanteRepository.findById(idParticipante);
    }

    public List<Participante> findAll(){
        return participanteRepository.findAll();
    }

    public Participante findByEmail(String email){
        return participanteRepository.findByEmail(email);
    }
    public Participante findByCarnetIdentidad(int carnetIdentidad){
        return participanteRepository.findByCarnetIdentidad(carnetIdentidad);
    }
    public boolean update(Participante participante){
        return participanteRepository.update(participante);
    }
    public boolean deleteParticipant(Long id){
        return participanteRepository.deleteParticipant(id);
    }

}
