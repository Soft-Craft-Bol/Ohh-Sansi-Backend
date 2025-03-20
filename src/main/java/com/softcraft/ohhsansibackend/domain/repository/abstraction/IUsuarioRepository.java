package com.softcraft.ohhsansibackend.domain.repository.abstraction;

import com.softcraft.ohhsansibackend.domain.models.Usuario;

public interface IUsuarioRepository {
    Usuario findByCorreo(String email);
    Usuario findByEmailAndPassword(String email, String password);
    Usuario save(Usuario usuario);
}
