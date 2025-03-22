package com.softcraft.ohhsansibackend.usuario.infraestructure.dto;

public class UserDTO {
    private String correoUsuario;
    private String password;

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
