
package com.uade.tpo.E_Commerce.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "Roles")
public class Roles {

    public Roles(){}


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_role;

    @Column(name = "role_name", length = 100)
    private String roleName;

    @OneToMany(mappedBy = "roles")
    private List<User_Roles> userRolesList;

    public long getId_role() {
        return id_role;
    }

    public String getRoleName() {
        return roleName;
    }

    public List<User_Roles> getUserRolesList() {
        return userRolesList;
    }
}
