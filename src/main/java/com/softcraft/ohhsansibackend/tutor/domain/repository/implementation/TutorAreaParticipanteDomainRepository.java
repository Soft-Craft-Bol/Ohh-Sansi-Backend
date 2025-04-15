package com.softcraft.ohhsansibackend.tutor.domain.repository.implementation;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TutorAreaParticipanteDomainRepository {
    private JdbcTemplate jdbcTemplate;
    public TutorAreaParticipanteDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
