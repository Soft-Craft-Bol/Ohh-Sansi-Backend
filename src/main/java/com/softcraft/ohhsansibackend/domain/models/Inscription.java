package com.softcraft.ohhsansibackend.domain.models;

import java.sql.Date;
import java.sql.Time;

public class Inscription {
    private Long idInscription;
    private Date insertDate;
    private Time insertTime;

    public Inscription() {

    }

    public Inscription(Long idInscription, Date insertDate, Time insertTime) {
        this.idInscription = idInscription;
        this.insertDate = insertDate;
        this.insertTime = insertTime;
    }

    public Long getIdInscription() {
        return idInscription;
    }

    public void setIdInscription(Long idInscription) {
        this.idInscription = idInscription;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public Time getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Time insertTime) {
        this.insertTime = insertTime;
    }
}
