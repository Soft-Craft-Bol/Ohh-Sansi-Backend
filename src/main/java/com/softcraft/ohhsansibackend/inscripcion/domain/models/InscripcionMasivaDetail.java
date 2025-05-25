package com.softcraft.ohhsansibackend.inscripcion.domain.models;

public class InscripcionMasivaDetail {
    private Integer idInscripcion;
    private String nombreTut;
    private String apellidoTut;
    private String correoTut;
    private Long ciTut;
    private Integer cantAreas;
    private Integer cantPaticipantes;

    public InscripcionMasivaDetail() {
    }

    public InscripcionMasivaDetail( String nombreTut, String apellidoTut,String correoTut,  Long ciTut, Integer cantAreas, Integer cantPaticipantes, Integer idInscripcion) {
        this.correoTut = correoTut;
        this.nombreTut = nombreTut;
        this.apellidoTut = apellidoTut;
        this.ciTut = ciTut;
        this.cantAreas = cantAreas;
        this.cantPaticipantes = cantPaticipantes;
        this.idInscripcion = idInscripcion;
    }

    public Integer getIdInscripcion(){return this.idInscripcion;}

    public void setIdInscripcion(Integer idInscripcion){this.idInscripcion = idInscripcion;}

    public String getCorreoTut() {return correoTut;}

    public void setCorreoTut(String correoTut) {this.correoTut = correoTut;}

    public String getNombreTut() {return nombreTut;}

    public void setNombreTut(String nombreTut) {this.nombreTut = nombreTut;}

    public String getApellidoTut() {return apellidoTut;}

    public void setApellidoTut(String apellidoTut) {this.apellidoTut = apellidoTut;}

    public Long getCiTut() {return ciTut;}

    public void setCiTut(Long ciTut) {this.ciTut = ciTut;}

    public Integer getCantAreas() {return cantAreas;}

    public void setCantAreas(Integer cantAreas) {this.cantAreas = cantAreas;}

    public Integer getCantPaticipantes(){return this.cantPaticipantes;}

    public void setCantPaticipantes(Integer cantPaticipantes){this.cantPaticipantes = cantPaticipantes;}
}
