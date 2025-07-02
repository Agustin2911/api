package com.uade.tpo.E_Commerce.entity.dto;

public class MailRequest {
    private String mail;

    public MailRequest(){}

    public MailRequest(String mail) {
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
