package com.softcraft.ohhsansibackend.participante.infraestructure.request;

public class ParticipanteVerifyDTO {
    private int ci;
    private String valuePermit;

    // Getters y setters
    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public String getValuePermit() {
        return valuePermit;
    }

    public void setValuePermit(String valuePermit) {
        this.valuePermit = valuePermit;
    }
}
