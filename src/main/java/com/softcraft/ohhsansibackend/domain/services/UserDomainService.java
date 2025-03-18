package com.softcraft.ohhsansibackend.domain.services;

import com.softcraft.ohhsansibackend.domain.models.Usuario;
import com.softcraft.ohhsansibackend.domain.repository.UsuarioDomainRepository;
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

    public void registerUser(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioDomainRepository.save(usuario);
    }
}