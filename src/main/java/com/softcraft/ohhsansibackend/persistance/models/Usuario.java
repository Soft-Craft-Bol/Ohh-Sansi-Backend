package com.softcraft.ohhsansibackend.persistance.models;

import java.sql.Date;

public class Usuario {
    private int idUsuario;
    private String idGeneral;
    private String nombreUsuario;
    private String apellidoPaternoUsuario;
    private String apellidoMaternoUsuario;
    private String correoUsuario;
    private String contrasenaUsuario;
    private String password;
    private Date fechaNacimiento;

    public Usuario() {

    }

    public Usuario(int idUsuario, String idGeneral, String nombreUsuario, String apellidoPaternoUsuario, String apellidoMaternoUsuario, String correoUsuario, String contrasenaUsuario, String password, Date fechaNacimiento) {
        this.idUsuario = idUsuario;
        this.idGeneral = idGeneral;
        this.nombreUsuario = nombreUsuario;
        this.apellidoPaternoUsuario = apellidoPaternoUsuario;
        this.apellidoMaternoUsuario = apellidoMaternoUsuario;
        this.correoUsuario = correoUsuario;
        this.contrasenaUsuario = contrasenaUsuario;
        this.password = password;
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdGeneral() {
        return idGeneral;
    }

    public void setIdGeneral(String idGeneral) {
        this.idGeneral = idGeneral;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoPaternoUsuario() {
        return apellidoPaternoUsuario;
    }

    public void setApellidoPaternoUsuario(String apellidoPaternoUsuario) {
        this.apellidoPaternoUsuario = apellidoPaternoUsuario;
    }

    public String getApellidoMaternoUsuario() {
        return apellidoMaternoUsuario;
    }

    public void setApellidoMaternoUsuario(String apellidoMaternoUsuario) {
        this.apellidoMaternoUsuario = apellidoMaternoUsuario;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public String getContrasenaUsuario() {
        return contrasenaUsuario;
    }

    public void setContrasenaUsuario(String contrasenaUsuario) {
        this.contrasenaUsuario = contrasenaUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
