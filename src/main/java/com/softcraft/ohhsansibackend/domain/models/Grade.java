package com.softcraft.ohhsansibackend.domain.models;

public class Grade {
    private Long idGrade;
    private String nameGrade;

    public Grade() {
    }

    public Grade(Long idGrade, String nameGrade) {
        this.idGrade = idGrade;
        this.nameGrade = nameGrade;
    }

    public Long getIdGrade() {
        return idGrade;
    }
    public void setIdGrade(Long idGrade) {
        this.idGrade = idGrade;

    }
    public String getNameGrade() {
        return nameGrade;
    }
    public void setNameGrade(String nameGrade) {
        this.nameGrade = nameGrade;
    }

}
