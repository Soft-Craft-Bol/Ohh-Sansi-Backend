package com.softcraft.ohhsansibackend.application.ports;

import com.softcraft.ohhsansibackend.domain.models.Usuario;
import com.softcraft.ohhsansibackend.domain.repository.implementation.UsuarioDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioAdapter {

    private final UsuarioDomainRepository usuarioDomainRepository;

    @Autowired
    public UsuarioAdapter(UsuarioDomainRepository usuarioDomainRepository) {
        this.usuarioDomainRepository = usuarioDomainRepository;
    }

    public Usuario findByCorreo(String correo) {
        return usuarioDomainRepository.findByCorreo(correo);
    }

    public Usuario save(Usuario usuario) {
        return usuarioDomainRepository.save(usuario);
    }
}