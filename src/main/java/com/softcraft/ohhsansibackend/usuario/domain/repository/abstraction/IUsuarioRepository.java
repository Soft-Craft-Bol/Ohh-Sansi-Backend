package com.softcraft.ohhsansibackend.usuario.domain.repository.abstraction;

import com.softcraft.ohhsansibackend.usuario.domain.models.Usuario;

public interface IUsuarioRepository {
    Usuario findByCorreo(String email);
    Usuario findByEmailAndPassword(String email, String password);
    Usuario save(Usuario usuario);
}
