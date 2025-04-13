package com.softcraft.ohhsansibackend.participante.domain.repository.implementation;

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
        String sql = "INSERT INTO participante (id_inscripcion, " +
                "id_departamento, " +
                "id_municipio, " +
                "id_colegio, " +
                "id_grado, " +
                "participante_hash, " +
                "nombre_participante, " +
                "apellido_paterno, " +
                "apellido_materno, " +
                "fecha_nacimiento, " +
                "carnet_identidad_participante, " +
                "complemento_ci_participante, " +
                "email_participante, " +
                "tutor_requerido) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, participante.getIdInscripcion());
            ps.setInt(2, participante.getIdDepartamento());
            ps.setInt(3, participante.getIdMunicipio());
            ps.setInt(4, participante.getIdColegio());
            ps.setObject(5, participante.getIdGrado(), java.sql.Types.INTEGER);
            ps.setString(6, participante.getParticipanteHash());
            ps.setString(7, participante.getNombreParticipante());
            ps.setString(8, participante.getApellidoPaterno());
            ps.setString(9, participante.getApellidoMaterno());
            ps.setDate(10, new java.sql.Date(participante.getFechaNacimiento().getTime()));
            ps.setLong(11, participante.getCarnetIdentidadParticipante());
            ps.setString(12, participante.getComplementoCiParticipante());
            ps.setString(13, participante.getEmailParticipante());
            ps.setBoolean(14, participante.isTutorRequerido());
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
        String sql = "SELECT * FROM participante WHERE email_participante = ?";
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
                participante.getEmailParticipante(),
                participante.getCarnetIdentidadParticipante(),
                participante.getIdGrado(),
                participante.getIdInscripcion(),
                participante.getIdParticipante()
        );
        return rowsAffected > 0;
    }

    @Override
    public boolean deleteParticipant(Long id) {
        String sql = "DELETE FROM participante WHERE id_participante = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }

    public Participante findParticipanteByIdInscripcion(int idInscripcion) {
        String sql = "SELECT p.* FROM participante p, inscripcion i WHERE p.id_inscripcion = ? AND i.inscripcion_masiva = false AND p.id_inscripcion = i.id_inscripcion";
        return jdbcTemplate.queryForObject(sql, new Object[]{idInscripcion}, new BeanPropertyRowMapper<>(Participante.class));
    }

    public int countParticipantesEnCatalogoParticipante(int idParticipante){
        String sql = "SELECT COUNT(participante_catalogo.id_participante) FROM participante_catalogo WHERE id_participante = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{idParticipante}, Integer.class);
        return count != null ? count : 0;
    }
}