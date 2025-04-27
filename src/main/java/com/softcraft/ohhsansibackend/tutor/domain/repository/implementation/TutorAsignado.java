package com.softcraft.ohhsansibackend.tutor.domain.repository.implementation;

import com.softcraft.ohhsansibackend.tutor.domain.models.Tutor;
import com.softcraft.ohhsansibackend.tutor.domain.repository.abstraction.ITutorAsignado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TutorAsignado implements ITutorAsignado {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TutorAsignado(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Tutor> findAllTutors(String ci) {
        String sql = "SELECT T.* FROM " +
                "(SELECT id_tutor, email_tutor, nombres_tutor, apellidos_tutor, telefono, carnet_identidad_tutor, complemento_ci_tutor" +
                "FROM public.tutor) T, public.participante_tutor Pa, (SELECT id_participante, carnet_identidad_participante" +
                "FROM public.participante WHERE carnet_identidad_participante = ? ) P" +
                "WHERE T.id_tutor = Pa.id_tutor and Pa.id_participante = P.id_participante"; // parámetro para el CI
        return jdbcTemplate.query(sql, new Object[]{ci}, new BeanPropertyRowMapper<>(Tutor.class));
    }
}