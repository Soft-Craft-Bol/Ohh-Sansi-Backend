package com.softcraft.ohhsansibackend.application.usecases;

import com.softcraft.ohhsansibackend.config.filter.JwtUtils;
import com.softcraft.ohhsansibackend.domain.models.Usuario;
import com.softcraft.ohhsansibackend.infraestucture.rest.dto.UserDTO;
import com.softcraft.ohhsansibackend.application.ports.UsuarioAdapter;
import com.softcraft.ohhsansibackend.application.exception.DuplicateResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final UsuarioAdapter usuarioAdapter;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(UsuarioAdapter usuarioAdapter, JwtUtils jwtUtils, AuthenticationManager authenticationManager) {
        this.usuarioAdapter = usuarioAdapter;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    public Map<String, Object> registerUser(Usuario usuario) {
        try {
            usuarioAdapter.save(usuario);
        } catch (DuplicateKeyException e) {
            throw new DuplicateResourceException("Email o carnet de identidad ya registrados");
        }
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Usuario registrado exitosamente");
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