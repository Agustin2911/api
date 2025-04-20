
package com.uade.tpo.E_Commerce.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class User_Roles{


    public User_Roles(){}

    @Id
    @Column(name = "id_user")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = false)
    private Basic_User basic_user;

    @ManyToOne
    @JoinColumn(name = "id_role", referencedColumnName = "id_role", nullable = false)
    private Roles roles;

    public Long getId() {
        return id;
    }

    public Basic_User getBasic_user() {
        return basic_user;
    }

    public Roles getRoles() {
        return roles;
    }
}

