package com.softcraft.ohhsansibackend.participante.domain.dto;

import java.util.List;

public class ParticipanteAreasDTO {
    private Integer idParticipante;
    private String nombreCompleto;
    private Integer carnetIdentidad;
    private String grado;
    private List<Integer> idsAreas;
    private List<String> nombresAreas;

    public ParticipanteAreasDTO(Integer idParticipante, String nombreCompleto, Integer carnetIdentidad, String grado, List<Integer> idsAreas, List<String> nombresAreas) {
        this.idParticipante = idParticipante;
        this.nombreCompleto = nombreCompleto;
        this.carnetIdentidad = carnetIdentidad;
        this.grado = grado;
        this.idsAreas = idsAreas;
        this.nombresAreas = nombresAreas;
    }

    public ParticipanteAreasDTO() {

    }

    public Integer getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(Integer idParticipante) {
        this.idParticipante = idParticipante;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Integer getCarnetIdentidad() {
        return carnetIdentidad;
    }

    public void setCarnetIdentidad(Integer carnetIdentidad) {
        this.carnetIdentidad = carnetIdentidad;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public List<Integer> getIdsAreas() {
        return idsAreas;
    }

    public void setIdsAreas(List<Integer> idsAreas) {
        this.idsAreas = idsAreas;
    }

    public List<String> getNombresAreas() {
        return nombresAreas;
    }

    public void setNombresAreas(List<String> nombresAreas) {
        this.nombresAreas = nombresAreas;
    }
}