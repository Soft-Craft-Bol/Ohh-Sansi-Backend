package com.softcraft.ohhsansibackend.catalogoolimpiadas.domain.DTO;

import java.util.List;

public class CatalogoOlimpiadaDTO {
    private String nombreOlimpiada;
    private String nombreArea;
    private String nombreCategoria;
    private List<String> grados;

    public CatalogoOlimpiadaDTO(String nombreOlimpiada, String nombreArea, String nombreCategoria, List<String> grados) {
        this.nombreOlimpiada = nombreOlimpiada;
        this.nombreArea = nombreArea;
        this.nombreCategoria = nombreCategoria;
        this.grados = grados;
    }

    public CatalogoOlimpiadaDTO() {
    }

    public String getNombreOlimpiada() {
        return nombreOlimpiada;
    }

    public void setNombreOlimpiada(String nombreOlimpiada) {
        this.nombreOlimpiada = nombreOlimpiada;
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
