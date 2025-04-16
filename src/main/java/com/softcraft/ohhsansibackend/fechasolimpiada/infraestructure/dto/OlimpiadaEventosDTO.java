package com.softcraft.ohhsansibackend.fechasolimpiada.infraestructure.dto;

import java.util.List;

public class OlimpiadaEventosDTO {
    private int idOlimpiada;
    private int anio;
    private String nombreOlimpiada;
    private boolean estadoOlimpiada;
    private List<EventoDTO> eventos;

    public OlimpiadaEventosDTO(int idOlimpiada, int anio, String nombreOlimpiada, boolean estadoOlimpiada, List<EventoDTO> eventos) {
        this.idOlimpiada = idOlimpiada;
        this.anio = anio;
        this.nombreOlimpiada = nombreOlimpiada;
        this.estadoOlimpiada = estadoOlimpiada;
        this.eventos = eventos;
    }

    public OlimpiadaEventosDTO() {
    }

    public int getIdOlimpiada() {
        return idOlimpiada;
    }

    public void setIdOlimpiada(int idOlimpiada) {
        this.idOlimpiada = idOlimpiada;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }
    public String getNombreOlimpiada() {
        return nombreOlimpiada;
    }

    public void setNombreOlimpiada(String nombreOlimpiada) {
        this.nombreOlimpiada = nombreOlimpiada;
    }

    public boolean isEstadoOlimpiada() {
        return estadoOlimpiada;
    }

    public void setEstadoOlimpiada(boolean estadoOlimpiada) {
        this.estadoOlimpiada = estadoOlimpiada;
    }

    public List<EventoDTO> getEventos() {
        return eventos;
    }

    public void setEventos(List<EventoDTO> eventos) {
        this.eventos = eventos;
    }
}
