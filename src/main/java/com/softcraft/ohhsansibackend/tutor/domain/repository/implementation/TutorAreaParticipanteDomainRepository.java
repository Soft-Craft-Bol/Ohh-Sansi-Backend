package com.softcraft.ohhsansibackend.tutor.domain.repository.implementation;

import com.softcraft.ohhsansibackend.participante.domain.models.ParticipanteTutor;
import com.softcraft.ohhsansibackend.tutor.domain.models.TutorAreaParticipante;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public List<Integer> findDistinctAreasByCarnetIdentidad(int carnetIdentidadParticipante) {
        String sql = """
        SELECT DISTINCT tab.id_area
        FROM tutor_area_participante tab
        JOIN participante_tutor pt ON tab.id_participante_tutor = pt.id_participante_tutor
        JOIN participante p ON pt.id_participante = p.id_participante
        WHERE p.carnet_identidad_participante = ?
    """;
        return jdbcTemplate.queryForList(sql, new Object[]{carnetIdentidadParticipante}, Integer.class);
    }
}
