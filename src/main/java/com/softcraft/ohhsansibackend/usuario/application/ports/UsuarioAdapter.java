package com.softcraft.ohhsansibackend.usuario.application.ports;

import com.softcraft.ohhsansibackend.usuario.domain.models.Usuario;
import com.softcraft.ohhsansibackend.usuario.domain.services.UserDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioAdapter {
    private final UserDomainService userDomainService;
    @Autowired
    public UsuarioAdapter(UserDomainService userDomainService) {
        this.userDomainService = userDomainService;
    }

    public Usuario findByCorreo(String correo) {
        return userDomainService.findByCorreo(correo);
    }

    public Usuario save(Usuario usuario) {
        return userDomainService.save(usuario);
    }
}