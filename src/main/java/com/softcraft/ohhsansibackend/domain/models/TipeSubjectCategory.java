package com.softcraft.ohhsansibackend.domain.models;

public class TipeSubjectCategory {
    private Long idTipeSubjectCategory;
    private String nameSubject;

    public TipeSubjectCategory() {

    }

    public TipeSubjectCategory(Long idTipeSubjectCategory, String nameSubject) {
        this.idTipeSubjectCategory = idTipeSubjectCategory;
        this.nameSubject = nameSubject;
    }

    public Long getIdTipeSubjectCategory() {
        return idTipeSubjectCategory;
    }

    public void setIdTipeSubjectCategory(Long idTipeSubjectCategory) {
        this.idTipeSubjectCategory = idTipeSubjectCategory;
    }

    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }
}
