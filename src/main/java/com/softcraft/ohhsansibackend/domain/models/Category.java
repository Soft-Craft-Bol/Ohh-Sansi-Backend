package com.softcraft.ohhsansibackend.domain.models;

public class Category {
    private Long idCategory;
    private String codCategory;

    public Category() {

    }

    public Category(Long idCategory, String codCategory) {
        this.idCategory = idCategory;
        this.codCategory = codCategory;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public String getCodCategory() {
        return codCategory;
    }

    public void setCodCategory(String codCategory) {
        this.codCategory = codCategory;
    }

}
