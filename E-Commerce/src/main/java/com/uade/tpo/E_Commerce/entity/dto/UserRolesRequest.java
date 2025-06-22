package com.uade.tpo.E_Commerce.entity.dto;

import lombok.Data;

@Data
public class UserRolesRequest {

    private String username;
    private String mail;
    private String role_name;

    public UserRolesRequest(String username, String mail, String roleName) {
        this.username = username;
        this.mail = mail;
        this.role_name = roleName;
    }


    public String getUsername() {
        return username;
    }

    public String getMail() {
        return mail;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }
}
