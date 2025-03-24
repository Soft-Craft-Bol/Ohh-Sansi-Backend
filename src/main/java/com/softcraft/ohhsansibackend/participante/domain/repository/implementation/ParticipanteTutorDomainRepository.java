package com.softcraft.ohhsansibackend.participante.domain.repository.implementation;

import com.softcraft.ohhsansibackend.participante.domain.models.ParticipanteTutor;
import com.softcraft.ohhsansibackend.participante.domain.repository.abstraction.IParticipanteTutorRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ParticipanteTutorDomainRepository implements IParticipanteTutorRepository {

    private final JdbcTemplate jdbcTemplate;

    public ParticipanteTutorDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ParticipanteTutor save(ParticipanteTutor participanteTutor) {
        String sql = "INSERT INTO participante_tutor (id_tutor, id_inscripcion, id_participante) VALUES (?, ?, ?) RETURNING id_participante_tutor";
        int idParticipanteTutor = jdbcTemplate.queryForObject(sql, new Object[]{
                participanteTutor.getIdTutor(),
                participanteTutor.getIdInscripcion(),
                participanteTutor.getIdParticipante()
        }, Integer.class);
        participanteTutor.setIdParticipanteTutor(idParticipanteTutor);
        return participanteTutor;
    }
}