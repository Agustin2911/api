package com.uade.tpo.E_Commerce.controllers.auth;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uade.tpo.E_Commerce.entity.Role;
import com.uade.tpo.E_Commerce.entity.Roles;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstname;
    private String email;
    private String password;
    private Long role;

    public String getFirstname() {
        return firstname;
    }



    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public long getRole() {
        return role;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }



    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(long role) {
        this.role = role;
    }
}
