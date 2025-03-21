package com.softcraft.ohhsansibackend.domain.models;

import jakarta.validation.constraints.NotNull;

public class TipoTutor {
    private Long idTipoTutor;
    @NotNull(message = "El nombre del tipo de tutor no puede ser nulo")
    private String nombreTipoTutor;

    public Long getIdTipoTutor() {
        return idTipoTutor;
    }

    public void setIdTipoTutor(Long idTipoTutor) {
        this.idTipoTutor = idTipoTutor;
    }

    public String getNombreTipoTutor() {
        return nombreTipoTutor;
    }

    public void setNombreTipoTutor(String nombreTipoTutor) {
        this.nombreTipoTutor = nombreTipoTutor;
    }
}
