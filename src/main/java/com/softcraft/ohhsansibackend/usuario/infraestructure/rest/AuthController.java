package com.softcraft.ohhsansibackend.usuario.infraestructure.rest;

import com.softcraft.ohhsansibackend.usuario.application.usecases.AuthService;
import com.softcraft.ohhsansibackend.usuario.domain.models.Usuario;
import com.softcraft.ohhsansibackend.usuario.infraestructure.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerUser(@RequestBody Usuario usuario) {
        Map<String, Object> response = authService.registerUser(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody UserDTO userDTO) {
        Map<String, Object> response = authService.authenticate(userDTO);
        if (response.containsKey("token")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}