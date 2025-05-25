package com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.DTO;

import java.util.List;

public class CatalogoOlimpiadaDTO {
    private int idOlimpiada;
    private String nombreOlimpiada;
    private int idArea;
    private String nombreArea;
    private String nombreCategoria;
    private List<String> grados;

    public CatalogoOlimpiadaDTO(int idOlimpiada, String nombreOlimpiada, int idArea, String nombreArea, String nombreCategoria, List<String> grados) {
        this.idOlimpiada = idOlimpiada;
        this.nombreOlimpiada = nombreOlimpiada;
        this.idArea = idArea;
        this.nombreArea = nombreArea;
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
