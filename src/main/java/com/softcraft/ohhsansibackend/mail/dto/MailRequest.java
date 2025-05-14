package com.softcraft.ohhsansibackend.mail.dto;

public class MailRequest {
    private String to;
    private String subject;
    private String content;
    private String codigoUnico;

    public String getTo() {
        return to;
    }

    public String getCodigoUnico(){return codigoUnico;}

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
