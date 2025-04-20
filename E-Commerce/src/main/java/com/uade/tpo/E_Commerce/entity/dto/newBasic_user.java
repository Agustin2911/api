package com.uade.tpo.E_Commerce.entity.dto;

public class newBasic_user {

    private String username;
    private String mail;
    private String password;

    public newBasic_user(String username, String mail, String password) {
        this.username = username;
        this.mail = mail;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }
}
