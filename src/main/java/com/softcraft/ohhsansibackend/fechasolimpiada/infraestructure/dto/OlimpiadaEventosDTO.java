package com.softcraft.ohhsansibackend.fechasolimpiada.infraestructure.dto;

import java.util.List;

public class OlimpiadaEventosDTO {
    private int anio;
    private String nombreOlimpiada;
    private boolean estadoOlimpiada;
    private List<EventoDTO> eventos;

    public OlimpiadaEventosDTO(int anio, String nombreOlimpiada, boolean estadoOlimpiada, List<EventoDTO> eventos) {
        this.anio = anio;
        this.nombreOlimpiada = nombreOlimpiada;
        this.estadoOlimpiada = estadoOlimpiada;
        this.eventos = eventos;
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
