package com.softcraft.ohhsansibackend.participante.domain.repository.abstraction;

import com.softcraft.ohhsansibackend.participante.domain.dto.ParticipanteAreasDTO;
import com.softcraft.ohhsansibackend.participante.domain.dto.ParticipanteTutorAreaDTO;
import com.softcraft.ohhsansibackend.participante.domain.models.Participante;

import java.util.List;
import java.util.Optional;

public interface IParticipanteRepository {
    Participante save(Participante participante);
    Participante findById(Long idParticipante);
    List<Participante> findAll();
    Participante findByEmail(String email);
    Participante findByCarnetIdentidad(int carnetIdentidad);
    boolean update(Participante participante);
    boolean deleteParticipant(Long id);
    Optional<ParticipanteAreasDTO> findAreasByCarnetIdentidad(int carnetIdentidad);
    Optional<ParticipanteTutorAreaDTO> findParticipanteAreasTutoresById(int carnetIdentidad);

}