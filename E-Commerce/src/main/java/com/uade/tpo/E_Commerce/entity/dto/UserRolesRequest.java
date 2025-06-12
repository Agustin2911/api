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

}
