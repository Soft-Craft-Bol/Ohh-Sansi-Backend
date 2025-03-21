package com.softcraft.ohhsansibackend.domain.models;

public class NivelEscolar {
    private int idNivel;
    private String codigoNivel;
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
