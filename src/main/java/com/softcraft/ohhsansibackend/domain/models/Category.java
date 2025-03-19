package com.softcraft.ohhsansibackend.domain.models;

public class Category {
    private Long idCategory;
    private Long idArea;
    private Long idTipoSubjectCategory;
    private String codCategory;

    public Category() {

    }

    public Category(Long idCategory, Long idArea, Long idTipoSubjectCategory, String codCategory) {
        this.idCategory = idCategory;
        this.idArea = idArea;
        this.idTipoSubjectCategory = idTipoSubjectCategory;
        this.codCategory = codCategory;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public Long getIdArea() {
        return idArea;
    }

    public void setIdArea(Long idArea) {
        this.idArea = idArea;
    }

    public Long getIdTipoSubjectCategory() {
        return idTipoSubjectCategory;
    }

    public void setIdTipoSubjectCategory(Long idTipoSubjectCategory) {
        this.idTipoSubjectCategory = idTipoSubjectCategory;
    }

    public String getCodCategory() {
        return codCategory;
    }

    public void setCodCategory(String codCategory) {
        this.codCategory = codCategory;
    }

}
