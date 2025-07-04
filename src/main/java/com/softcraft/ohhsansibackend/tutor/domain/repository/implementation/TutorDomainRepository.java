package com.softcraft.ohhsansibackend.tutor.domain.repository.implementation;

import com.softcraft.ohhsansibackend.tutor.domain.models.Tutor;
import com.softcraft.ohhsansibackend.tutor.domain.repository.abstraction.ITutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

@Repository
public class TutorDomainRepository implements ITutorRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Tutor save(Tutor tutor) {
        String sql = "INSERT INTO tutor (id_tipo_tutor, email_tutor, nombres_tutor, apellidos_tutor, telefono, carnet_identidad_tutor, complemento_ci_tutor) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1, tutor.getIdTipoTutor(), java.sql.Types.INTEGER); // Nullable
            ps.setString(2, tutor.getEmailTutor());
            ps.setString(3, tutor.getNombresTutor());
            ps.setString(4, tutor.getApellidosTutor());
            ps.setInt(5, tutor.getTelefono());
            ps.setLong(6, tutor.getCarnetIdentidadTutor());
            ps.setString(7, tutor.getComplementoCiTutor());
            return ps;
        }, keyHolder);
        Map<String, Object> keys = keyHolder.getKeys();
        if (keys != null) {
            tutor.setIdTutor(((Number) keys.get("id_tutor")).longValue());
        }
        return tutor;
    }

    @Override
    public Tutor findByIdTutor(int idTutor) {
        String sql = "SELECT * FROM tutor WHERE id_tutor = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Tutor.class), idTutor);
    }

    @Override
    public List<Tutor> findAllTutor() {
        String sql = "SELECT * FROM tutor";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Tutor.class));
    }

    public Tutor findByEmail(String email) {
        String sql = "SELECT * FROM tutor WHERE email_tutor = ?";
        List<Tutor> tutors = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Tutor.class), email);
        return tutors.isEmpty() ? null : tutors.get(0);
    }

    public Tutor findByCarnetIdentidad(int carnetIdentidad) {
        String sql = "SELECT * FROM tutor WHERE carnet_identidad_tutor = ?";
        List<Tutor> tutors = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Tutor.class), carnetIdentidad);
        return tutors.isEmpty() ? null : tutors.get(0);
    }
    //validacoin para ver si el participante ya tiene cuandtos tutores registrados academicos
    public int countTutorsAcademicosByParticipanteId(int participanteId) {
        String sql = "SELECT COUNT(pt.id_participante) FROM participante_tutor pt, tutor t, tipo_tutor tt WHERE pt.id_participante = ? and t.id_tutor = pt.id_tutor and t.id_tipo_tutor = tt.id_tipo_tutor and tt.nombre_tipo_tutor = 'ACADEMICO';";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, participanteId);
        return (count != null) ? count : 0;
    }
    //validacion para ver si el participante ya tiene cuandtos tutores registrados legales
    public int countTutorsLegalesByParticipanteId(int participanteId) {
        String sql = "SELECT COUNT(pt.id_participante) FROM participante_tutor pt, tutor t, tipo_tutor tt WHERE pt.id_participante = ? and t.id_tutor = pt.id_tutor and t.id_tipo_tutor = tt.id_tipo_tutor and tt.nombre_tipo_tutor = 'LEGAL';";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, participanteId);
        return (count != null) ? count : 0;
    }

    public List<Tutor> findTutorsByCarnetParticipante(int ciParticipante) {
        String sql = """
            select (t.*)
            from tutor t, participante p, participante_tutor pt
            where t.id_tutor = pt.id_tutor and p.id_participante = pt.id_participante and p.carnet_identidad_participante= ?;
        """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Tutor.class), ciParticipante);
    }

    public void guardarTutorAcademico(Tutor tutor, int idParticipante, int idInscripcion, int idArea) {
        String sql = "CALL registrar_tutor_academico2(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                tutor.getCarnetIdentidadTutor(),
                tutor.getComplementoCiTutor(),
                tutor.getNombresTutor(),
                tutor.getApellidosTutor(),
                tutor.getEmailTutor(),
                tutor.getTelefono(),
                idParticipante,
                idInscripcion,
                idArea
        );
    }


}
