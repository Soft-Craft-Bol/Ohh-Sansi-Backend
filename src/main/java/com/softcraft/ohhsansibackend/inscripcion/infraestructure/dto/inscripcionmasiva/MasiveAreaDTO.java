package com.softcraft.ohhsansibackend.inscripcion.infraestructure.dto.inscripcionmasiva;

public class MasiveAreaDTO {
    private int idArea;
    private int idArea2;

    public MasiveAreaDTO() {
    }

    public MasiveAreaDTO(int idArea2, int idArea) {
        this.idArea2 = idArea2;
        this.idArea = idArea;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public int getIdArea2() {
        return idArea2;
    }

    public void setIdArea2(int idArea2) {
        this.idArea2 = idArea2;
    }
}
