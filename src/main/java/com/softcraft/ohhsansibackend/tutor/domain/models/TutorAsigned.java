package com.softcraft.ohhsansibackend.tutor.domain.models;

public class TutorAsigned {
    private String correoTut;
    private String nombreTut;
    private String apellidoTut;
    private Integer telf;
    private Long ciTut;
    private String complemento;

    public TutorAsigned() {
    }

    public TutorAsigned( String correoTut, String nombreTut, String apellidoTut, Integer telf, Long ciTut, String complemento) {
        this.correoTut = correoTut;
        this.nombreTut = nombreTut;
        this.apellidoTut = apellidoTut;
        this.telf = telf;
        this.ciTut = ciTut;
        this.complemento = complemento;
    }

    public String getCorreoTut() {return correoTut;}

    public void setCorreoTut(String correoTut) {this.correoTut = correoTut;}

    public String getNombreTut() {return nombreTut;}

    public void setNombreTut(String nombreTut) {this.nombreTut = nombreTut;}

    public String getApellidoTut() {return apellidoTut;}

    public void setApellidoTut(String apellidoTut) {this.apellidoTut = apellidoTut;}

    public Integer getTelf() {return telf;}

    public void setTelf(Integer telf) {this.telf = telf;}

    public Long getCiTut() {return ciTut;}

    public void setCiTut(Long ciTut) {this.ciTut = ciTut;}

    public String getComplemento() {return complemento;}

    public void setComplemento(String complemento) {this.complemento = complemento;}
}
