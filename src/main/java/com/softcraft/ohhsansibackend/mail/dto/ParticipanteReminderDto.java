package com.softcraft.ohhsansibackend.mail.dto;

import java.sql.Date;

public class ParticipanteReminderDto {
    private String email;
    private String nombreParticipante;
    private String codUnique;
    private String tipoPeriodo;
    private Date fechaFinPeriodo;
    private int diasRestantes;

    public ParticipanteReminderDto() {}

    public ParticipanteReminderDto(String email, String nombreParticipante, String codUnique,
                                   String tipoPeriodo, Date fechaFinPeriodo, int diasRestantes) {
        this.email = email;
        this.nombreParticipante = nombreParticipante;
        this.codUnique = codUnique;
        this.tipoPeriodo = tipoPeriodo;
        this.fechaFinPeriodo = fechaFinPeriodo;
        this.diasRestantes = diasRestantes;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getNombreParticipante() { return nombreParticipante; }
    public void setNombreParticipante(String nombreParticipante) { this.nombreParticipante = nombreParticipante; }

    public String getCodUnique() { return codUnique; }
    public void setCodUnique(String codUnique) { this.codUnique = codUnique; }

    public String getTipoPeriodo() { return tipoPeriodo; }
    public void setTipoPeriodo(String tipoPeriodo) { this.tipoPeriodo = tipoPeriodo; }

    public Date getFechaFinPeriodo() { return fechaFinPeriodo; }
    public void setFechaFinPeriodo(Date fechaFinPeriodo) { this.fechaFinPeriodo = fechaFinPeriodo; }

    public int getDiasRestantes() { return diasRestantes; }
    public void setDiasRestantes(int diasRestantes) { this.diasRestantes = diasRestantes; }
}
