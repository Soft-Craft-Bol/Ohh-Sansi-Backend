package com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.DTO;

import java.util.List;

public class CatalogoOlimpiadaDTO {
    private int idOlimpiada;
    private String nombreOlimpiada;
    private int idCatalogo;
    private int idArea;
    private String nombreArea;
    private String descripcionArea;
    private String nombreCategoria;
    private List<String> grados;

    public CatalogoOlimpiadaDTO(int idOlimpiada, String nombreOlimpiada, int idCatalogo, int idArea, String nombreArea, String descripcionArea,  String nombreCategoria, List<String> grados) {
        this.idOlimpiada = idOlimpiada;
        this.nombreOlimpiada = nombreOlimpiada;
        this.idCatalogo = idCatalogo;
        this.idArea = idArea;
        this.nombreArea = nombreArea;
        this.descripcionArea = descripcionArea;
        this.nombreCategoria = nombreCategoria;
        this.grados = grados;
    }

    public CatalogoOlimpiadaDTO() {
    }

    public int getIdOlimpiada() {
        return idOlimpiada;
    }

    public void setIdOlimpiada(int idOlimpiada) {
        this.idOlimpiada = idOlimpiada;
    }

    public String getNombreOlimpiada() {
        return nombreOlimpiada;
    }

    public void setNombreOlimpiada(String nombreOlimpiada) {
        this.nombreOlimpiada = nombreOlimpiada;
    }

    public int getIdCatalogo() {
        return idCatalogo;
    }

    public void setIdCatalogo(int idCatalogo) {
        this.idCatalogo = idCatalogo;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }
public String getDescripcionArea() {
        return descripcionArea;
    }
public void setDescripcionArea(String descripcionArea) {
        this.descripcionArea = descripcionArea;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public List<String> getGrados() {
        return grados;
    }

    public void setGrados(List<String> grados) {
        this.grados = grados;
    }
}