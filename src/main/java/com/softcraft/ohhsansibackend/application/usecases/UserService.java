package com.softcraft.ohhsansibackend.application.usecases;

import com.softcraft.ohhsansibackend.domain.models.Usuario;
import com.softcraft.ohhsansibackend.domain.repository.implementation.UsuarioDomainRepository;
import com.softcraft.ohhsansibackend.config.filter.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UsuarioDomainRepository usuarioDomainRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UsuarioDomainRepository usuarioDomainRepository, JwtUtils jwtUtils, PasswordEncoder passwordEncoder) {
        this.usuarioDomainRepository = usuarioDomainRepository;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioDomainRepository.save(usuario);
    }

    public String createToken(Authentication authentication) {
        return jwtUtils.createToken(authentication);
    }
}