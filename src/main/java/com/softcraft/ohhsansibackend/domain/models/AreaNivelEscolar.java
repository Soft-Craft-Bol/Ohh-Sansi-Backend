package com.softcraft.ohhsansibackend.domain.models;

public class AreaNivelEscolar {
    private int idAreaNivelEscolar;
    private int idArea;
    private int idNivel;

    public AreaNivelEscolar() {
    }

    public AreaNivelEscolar(int idAreaNivelEscolar, int idArea, int idNivel) {
        this.idAreaNivelEscolar = idAreaNivelEscolar;
        this.idArea = idArea;
        this.idNivel = idNivel;
    }

    public int getIdAreaNivelEscolar() {
        return idAreaNivelEscolar;
    }

    public void setIdAreaNivelEscolar(int idAreaNivelEscolar) {
        this.idAreaNivelEscolar = idAreaNivelEscolar;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }


    public int getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(int idNivel) {
        this.idNivel = idNivel;
    }

}
