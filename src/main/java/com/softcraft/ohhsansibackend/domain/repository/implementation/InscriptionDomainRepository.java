package com.softcraft.ohhsansibackend.domain.repository.implementation;

import com.softcraft.ohhsansibackend.domain.models.Inscription;
import com.softcraft.ohhsansibackend.domain.repository.abstraction.IInscriptionRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public class InscriptionDomainRepository implements IInscriptionRepository {
    private final JdbcTemplate jdbcTemplate;

    public InscriptionDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveInscription(Inscription inscription) {
        String sql = "SELECT InsertInscription(?)";
        Boolean result = jdbcTemplate.queryForObject(sql, Boolean.class);
        if (result == null || !result) {
            throw new RuntimeException("Not able to save inscription");
        }
    }

    @Override
    public void updateInscription(Inscription inscription) {
        String sql = "SELECT UpdateInscription(?)";
        Boolean result = jdbcTemplate.queryForObject(sql, Boolean.class);
        if (result == null || !result) {
            throw new RuntimeException("Not able to update inscription");
        }
    }

    @Override
    public void deleteInscription(Long inscriptionId) {
        String sql = "SELECT DeleteInscription(?)";
        Boolean result = jdbcTemplate.queryForObject(sql, Boolean.class);
        if (result == null || !result) {
            throw new RuntimeException("Not able to delete inscription");
        }
    }

    @Override
    public Optional<Inscription> findByIdInscription(Long inscriptionId) {
        String sql = "SELECT * FROM SelectInscriptionById(?)";
        List<Inscription> inscriptions = jdbcTemplate.query(sql, new Object[]{inscriptionId}, new BeanPropertyRowMapper<>(Inscription.class));
        return inscriptions.stream().findFirst();
    }

    @Override
    public List <Inscription>findAllInscription() {
        String sql = "SELECT * FROM SelectAllInscription()";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Inscription.class));
    }

    @Override
    public List<Inscription> findByDateAndTime(String date, String time) {
        String sql = "SELECT * FROM SelectInscriptionByDateAndTime(?, ?)";
        return jdbcTemplate.query(sql, new Object[]{date, time},
                new BeanPropertyRowMapper<>(Inscription.class));
    }
}
