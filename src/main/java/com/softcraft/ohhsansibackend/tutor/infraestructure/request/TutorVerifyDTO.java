package com.softcraft.ohhsansibackend.tutor.infraestructure.request;

public class TutorVerifyDTO {
    private int ci;
    private String valuePermit;

    public int getCi() {return ci;}

    public void setCi(int ci) {this.ci = ci;}

    public String getValuePermit() {
        return valuePermit;
    }

    public void setValuePermit(String valuePermit) {
        this.valuePermit = valuePermit;
    }
}
