package com.softcraft.ohhsansibackend.domain.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class NivelEscolar {
    private int idNivel;

    @NotBlank(message = "El código del nivel escolar no puede estar vacío")
    @Size(min = 2, max = 2, message = "El código del nivel escolar debe tener 2 caracteres")
    private String codigoNivel;

    @NotBlank(message = "El nombre del nivel escolar no puede estar vacío")
    @Size(min = 15, max = 55, message = "El nombre del nivel escolar debe tener entre 15 y 55 caracteres")
    private String nombreNivelEscolar;

    public NivelEscolar() {
    }

    public NivelEscolar(int idNivel, String codigoNivel, String nombreNivelEscolar) {
        this.idNivel = idNivel;
        this.codigoNivel = codigoNivel;
        this.nombreNivelEscolar = nombreNivelEscolar;
    }

    public int getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(int idNivel) {
        this.idNivel = idNivel;
    }

    public String getCodigoNivel() {
        return codigoNivel;
    }

    public void setCodigoNivel(String codigoNivel) {
        this.codigoNivel = codigoNivel;
    }

    public String getNombreNivelEscolar() {
        return nombreNivelEscolar;
    }

    public void setNombreNivelEscolar(String nombreNivelEscolar) {
        this.nombreNivelEscolar = nombreNivelEscolar;
    }
}
