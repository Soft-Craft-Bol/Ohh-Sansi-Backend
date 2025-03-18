package com.softcraft.ohhsansibackend.domain.repository;

import com.softcraft.ohhsansibackend.domain.models.Usuario;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDomainRepository {
    private final JdbcTemplate jdbcTemplate;

    public UsuarioDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Usuario findByCorreo(String correo) {
        String sql = "SELECT * FROM USUARIO WHERE CORREO_USUARIO = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{correo}, new BeanPropertyRowMapper<>(Usuario.class));
    }


    public void save(Usuario usuario) {
        String sql = "INSERT INTO USUARIO (ID_GENERAL, NOMBRE_USUARIO, APELLIDO_PATERNO_USUARIO, APELLIDO_MATERNO_USUARIO, CORREO_USUARIO, CONTRASENA_USUARIO, PASSWORD, FECHA_NACIMIENTO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, usuario.getIdGeneral(), usuario.getNombreUsuario(), usuario.getApellidoPaternoUsuario(), usuario.getApellidoMaternoUsuario(), usuario.getCorreoUsuario(), usuario.getContrasenaUsuario(), usuario.getPassword(), usuario.getFechaNacimiento());
    }
}