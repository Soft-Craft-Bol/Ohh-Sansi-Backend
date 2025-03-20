package com.softcraft.ohhsansibackend.domain.models;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
@AllArgsConstructor
public class Usuario {
    private int idUsuario;
    private String carnetIdentidad;
    private String nombreUsuario;
    private String apellidoPaternoUsuario;
    private String apellidoMaternoUsuario;
    private String correoUsuario;
    private String contrasenaUsuario;
    private String password;
    private Date fechaNacimiento;

    public Usuario() {

    }

}