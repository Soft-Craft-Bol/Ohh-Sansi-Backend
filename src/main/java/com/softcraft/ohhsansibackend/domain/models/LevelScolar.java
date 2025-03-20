package com.softcraft.ohhsansibackend.domain.models;

public class LevelScolar {
    private Long idLevel;
    private String codeLevel;
    private String nameLevelScolar;

    public LevelScolar() {
    }

    public LevelScolar(Long idLevel, String codeLevel, String nameLevelScolar) {
        this.idLevel = idLevel;
        this.codeLevel = codeLevel;
        this.nameLevelScolar = nameLevelScolar;
    }

    public Long getIdLevel() {
        return idLevel;
    }
    public void setIdLevel(Long idLevel) {
        this.idLevel = idLevel;
    }
    public String getNameLevelScolar() {
        return nameLevelScolar;
    }
    public void setNameLevelScolar(String nameLevelScolar) {
        this.nameLevelScolar = nameLevelScolar;
    }
    public String getCodeLevel() {
        return codeLevel;
    }
    public void setCodeLevel(String codeLevel) {
        this.codeLevel = codeLevel;
    }

}
