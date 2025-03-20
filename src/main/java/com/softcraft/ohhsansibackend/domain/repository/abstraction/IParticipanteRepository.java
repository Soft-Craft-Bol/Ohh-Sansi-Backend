package com.softcraft.ohhsansibackend.domain.repository.abstraction;

import com.softcraft.ohhsansibackend.domain.models.Participante;

import java.util.List;

public interface IParticipanteRepository {
    Participante save(Participante participante);
    Participante findById(Long idParticipante);
    List<Participante> findAll();
    Participante findByEmail(String email);
    Participante findByCarnetIdentidad(int carnetIdentidad);
    boolean update(Participante participante);
    boolean deleteParticipant(Long id);
}
