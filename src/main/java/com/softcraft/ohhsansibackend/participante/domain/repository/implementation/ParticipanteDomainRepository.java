package com.softcraft.ohhsansibackend.participante.domain.repository.implementation;

import com.softcraft.ohhsansibackend.domain.IDatabaseRepository;
import com.softcraft.ohhsansibackend.participante.domain.models.Participante;
import com.softcraft.ohhsansibackend.participante.domain.repository.abstraction.IParticipanteRepository;
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
public class ParticipanteDomainRepository implements IParticipanteRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ParticipanteDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Participante save(Participante participante) {
        String sql = "INSERT INTO participante (id_inscripcion, id_departamento, id_municipio, id_colegio, participante_hash, apellido_paterno, apellido_materno, nombre_participante, fecha_nacimiento, correo_electronico_participante, carnet_identidad_participante, id_nivel) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, participante.getIdInscripcion());
            ps.setInt(2, participante.getIdDepartamento());
            ps.setInt(3, participante.getIdMunicipio());
            ps.setInt(4, participante.getIdColegio());
            ps.setString(5, participante.getParticipanteHash());
            ps.setString(6, participante.getApellidoPaterno());
            ps.setString(7, participante.getApellidoMaterno());
            ps.setString(8, participante.getNombreParticipante());
            ps.setDate(9, new java.sql.Date(participante.getFechaNacimiento().getTime()));
            ps.setString(10, participante.getCorreoElectronicoParticipante());
            ps.setInt(11, participante.getCarnetIdentidadParticipante());
            ps.setInt(12, participante.getIdNivel());
            return ps;
        }, keyHolder);
        Map<String, Object> keys = keyHolder.getKeys();
        if (keys != null) {
            participante.setIdParticipante((Integer) keys.get("id_participante"));
        }
        return participante;
    }

    @Override
    public Participante findById(Long idParticipante) {
        String sql = "SELECT * FROM participante WHERE id_participante = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{idParticipante}, new BeanPropertyRowMapper<>(Participante.class));
    }

    @Override
    public List<Participante> findAll() {
        String sql = "SELECT * FROM participante";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Participante.class));
    }

    @Override
    public Participante findByEmail(String email) {
        String sql = "SELECT * FROM participante WHERE correo_electronico_participante = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{email}, new BeanPropertyRowMapper<>(Participante.class));
    }

    @Override
    public Participante findByCarnetIdentidad(int carnetIdentidad) {
        String sql = "SELECT * FROM participante WHERE carnet_identidad_participante = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{carnetIdentidad}, new BeanPropertyRowMapper<>(Participante.class));
    }

    @Override
    public boolean update(Participante participante) {
        String sql = "UPDATE participante SET id_departamento = ?, id_municipio = ?, id_colegio = ?, participante_hash = ?, apellido_paterno = ?, apellido_materno = ?, nombre_participante = ?, fecha_nacimiento = ?, correo_electronico_participante = ?, carnet_identidad_participante = ?, id_nivel = ? WHERE id_inscripcion = ? AND id_participante = ?";
        int rowsAffected = jdbcTemplate.update(sql,
                participante.getIdDepartamento(),
                participante.getIdMunicipio(),
                participante.getIdColegio(),
                participante.getParticipanteHash(),
                participante.getApellidoPaterno(),
                participante.getApellidoMaterno(),
                participante.getNombreParticipante(),
                participante.getFechaNacimiento(),
                participante.getCorreoElectronicoParticipante(),
                participante.getCarnetIdentidadParticipante(),
                participante.getIdNivel(),
                participante.getIdInscripcion(),
                participante.getIdParticipante()
        );
        return rowsAffected > 0;
    }

    @Override
    public boolean deleteParticipant(Long id) {
        String sql = "DELETE FROM participante WHERE id_inscripcion = ? AND id_participante = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }
}