package com.softcraft.ohhsansibackend.domain.repository.implementation;

import com.softcraft.ohhsansibackend.domain.models.Participante;
import com.softcraft.ohhsansibackend.domain.repository.abstraction.IParticipanteRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParticipanteDomainRepository implements IParticipanteRepository {
    private JdbcTemplate jdbcTemplate;

    @Override
    public Participante save(Participante participante) {
        return null;
    }

    @Override
    public Participante findById(Long idParticipante) {
        return null;
    }

    @Override
    public List<Participante> findAll() {
        return List.of();
    }

    @Override
    public Participante findByEmail(String email) {
        return null;
    }

    @Override
    public Participante findByCarnetIdentidad(int carnetIdentidad) {
        return null;
    }

    @Override
    public boolean update(Participante participante) {
        return false;
    }

    @Override
    public boolean deleteParticipant(Long id) {
        return false;
    }
}
