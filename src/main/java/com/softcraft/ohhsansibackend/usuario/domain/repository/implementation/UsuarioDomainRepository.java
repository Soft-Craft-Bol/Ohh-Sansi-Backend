package com.softcraft.ohhsansibackend.usuario.domain.repository.implementation;

import com.softcraft.ohhsansibackend.usuario.domain.models.Usuario;
import com.softcraft.ohhsansibackend.usuario.domain.repository.abstraction.IUsuarioRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDomainRepository implements IUsuarioRepository {
    private final JdbcTemplate jdbcTemplate;

    public UsuarioDomainRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Usuario findByCorreo(String email) {
        String sql = "SELECT * FROM USUARIO WHERE CORREO_USUARIO = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{email}, new BeanPropertyRowMapper<>(Usuario.class));
    }

    @Override
    public Usuario findByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM USUARIO WHERE CORREO_USUARIO = ? AND PASSWORD = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{email, password}, new BeanPropertyRowMapper<>(Usuario.class));
    }

    @Override
    public Usuario save(Usuario usuario) {
        String sql = "SELECT insert_usuario(?, ?, ?, ?, ?, ?, ?)";
        int id = jdbcTemplate.queryForObject(sql, new Object[]{
                usuario.getCarnetIdentidad(),
                usuario.getNombreUsuario(),
                usuario.getApellidoPaternoUsuario(),
                usuario.getApellidoMaternoUsuario(),
                usuario.getCorreoUsuario(),
                usuario.getPassword(),
                usuario.getFechaNacimiento()
        }, Integer.class);
        usuario.setIdUsuario(id);
        return usuario;
    }
}