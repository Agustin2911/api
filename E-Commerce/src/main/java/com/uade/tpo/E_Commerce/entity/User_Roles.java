package com.uade.tpo.E_Commerce.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
public class User_Roles implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_role", referencedColumnName = "id_role", nullable = false)
    private Roles role;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = false)
    private Basic_User user;

    public User_Roles() {
    }

    public User_Roles(Roles role, Basic_User user) {
        this.role = role;
        this.user = user;
    }
}
