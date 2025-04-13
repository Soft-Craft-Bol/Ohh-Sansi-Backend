package com.softcraft.ohhsansibackend.participante.application.ports;

import com.softcraft.ohhsansibackend.participante.domain.models.Participante;
import com.softcraft.ohhsansibackend.participante.domain.services.ParticipanteDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParticipanteAdapter {
    private final ParticipanteDomainService participanteDomainService;
    @Autowired
    public ParticipanteAdapter(ParticipanteDomainService participanteDomainService) {
        this.participanteDomainService = participanteDomainService;
    }
    public Participante save(Participante participante){
        return participanteDomainService.save(participante);
    }

    public Participante findById(Long idParticipante){
        return participanteDomainService.findById(idParticipante);
    }

    public List<Participante> findAll(){
        return participanteDomainService.findAll();
    }

    public Participante findByEmail(String email){
        return participanteDomainService.findByEmail(email);
    }
    public Participante findByCarnetIdentidad(int carnetIdentidad){
        return participanteDomainService.findByCarnetIdentidad(carnetIdentidad);
    }
    public boolean update(Participante participante){
        return participanteDomainService.update(participante);
    }
    public boolean deleteParticipant(Long id){
        return participanteDomainService.deleteParticipant(id);
    }
    public Participante findParticipanteByIdInscripcion(int idInscripcion) {
        return participanteDomainService.findParticipanteByIdInscripcion(idInscripcion);
    }
    public int countParticipantesEnCatalogoParticipante(int idParticipante){
        return participanteDomainService.countParticipantesEnCatalogoParticipante(idParticipante);
    }
}