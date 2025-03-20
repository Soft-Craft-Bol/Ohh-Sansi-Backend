package com.softcraft.ohhsansibackend.domain.services;

import com.softcraft.ohhsansibackend.domain.models.Usuario;
import com.softcraft.ohhsansibackend.domain.repository.implementation.UsuarioDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDomainService {

    private final UsuarioDomainRepository usuarioDomainRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserDomainService(UsuarioDomainRepository usuarioDomainRepository, PasswordEncoder passwordEncoder) {
        this.usuarioDomainRepository = usuarioDomainRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario findByCorreo(String correo) {
        return usuarioDomainRepository.findByCorreo(correo);
    }

    public Usuario save(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioDomainRepository.save(usuario);
    }
}