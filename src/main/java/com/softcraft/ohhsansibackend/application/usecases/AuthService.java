package com.softcraft.ohhsansibackend.application.usecases;

import com.softcraft.ohhsansibackend.config.filter.JwtUtils;
import com.softcraft.ohhsansibackend.domain.models.Usuario;
import com.softcraft.ohhsansibackend.domain.repository.UsuarioDomainRepository;
import com.softcraft.ohhsansibackend.infraestucture.rest.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final UsuarioDomainRepository usuarioDomainRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(UsuarioDomainRepository usuarioDomainRepository, JwtUtils jwtUtils, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.usuarioDomainRepository = usuarioDomainRepository;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public Map<String, Object> registerUser(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioDomainRepository.save(usuario);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User registered successfully");
        return response;
    }

    public Map<String, Object> authenticate(UserDTO userDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDTO.getCorreoUsuario(), userDTO.getPassword())
            );
            String token = jwtUtils.createToken(authentication);
            response.put("token", token);
        } catch (AuthenticationException e) {
            response.put("message", "Invalid username or password");
        }
        return response;
    }
}