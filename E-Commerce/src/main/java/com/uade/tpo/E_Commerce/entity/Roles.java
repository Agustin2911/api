package com.uade.tpo.E_Commerce.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "Roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private int idRole;

    @Column(name = "role_name", length = 100)
    private String roleName;

    public Roles() {
    }

    public Roles(int idRole, String roleName) {
        this.idRole = idRole;
        this.roleName = roleName;
    }
}
