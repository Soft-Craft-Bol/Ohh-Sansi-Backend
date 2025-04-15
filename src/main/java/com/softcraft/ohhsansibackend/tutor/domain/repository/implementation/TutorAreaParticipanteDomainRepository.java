package com.softcraft.ohhsansibackend.tutor.domain.repository.implementation;

import com.softcraft.ohhsansibackend.participante.domain.models.ParticipanteTutor;
import com.softcraft.ohhsansibackend.tutor.domain.models.TutorAreaParticipante;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TutorAreaParticipanteDomainRepository {
    private JdbcTemplate jdbcTemplate;
    public TutorAreaParticipanteDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public TutorAreaParticipante insert(int idTutorArea, int idParticipanteTutor) {
        String sql = "INSERT INTO tutor_area_participante (id_area, id_participante_tutor) VALUES (?, ?) RETURNING id_tutor_area_participante";
        int generatedId = jdbcTemplate.queryForObject(sql, new Object[]{idTutorArea, idParticipanteTutor}, Integer.class);
        TutorAreaParticipante tutorAreaParticipante = new TutorAreaParticipante();
        tutorAreaParticipante.setTutorAreaParticipante(generatedId);
        tutorAreaParticipante.setIdArea(idTutorArea);
        tutorAreaParticipante.setIdParticipanteTutor(idParticipanteTutor);
        return tutorAreaParticipante;
    }


}
