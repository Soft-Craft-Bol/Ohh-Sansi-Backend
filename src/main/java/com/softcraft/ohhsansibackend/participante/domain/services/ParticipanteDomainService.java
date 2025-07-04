package com.softcraft.ohhsansibackend.participante.domain.services;

import com.softcraft.ohhsansibackend.participante.domain.dto.ParticipanteAreasDTO;
import com.softcraft.ohhsansibackend.participante.domain.dto.ParticipanteResumenDTO;
import com.softcraft.ohhsansibackend.participante.domain.dto.ParticipanteTutorAreaDTO;
import com.softcraft.ohhsansibackend.participante.domain.models.Participante;
import com.softcraft.ohhsansibackend.participante.domain.repository.implementation.ParticipanteDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Participante findParticipanteByIdInscripcion(int idInscripcion) {
        return participanteRepository.findParticipanteByIdInscripcion(idInscripcion);
    }
    public int countParticipantesEnCatalogoParticipante(int idParticipante){
        return participanteRepository.countParticipantesEnCatalogoParticipante(idParticipante);
    }
    public Optional<ParticipanteAreasDTO> obtenerAreasPorCarnet(int carnetIdentidad) {
        return participanteRepository.findAreasByCarnetIdentidad(carnetIdentidad);
    }

    public Optional<ParticipanteTutorAreaDTO> obtenerTutorAreaPorCarnet (int carnetIdentidad){
        return participanteRepository.findParticipanteAreasTutoresById(carnetIdentidad);
    }
    public Optional<ParticipanteResumenDTO> obtenerParticipanteResumenPorCi(int carnetIdentidad) {
        return participanteRepository.findParticipanteResumenByCi(carnetIdentidad);
    }
}