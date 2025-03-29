package com.uade.tpo.E_Commerce.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "basic_user")
public class Basic_User {


    public Basic_User(){}

    @Id
    @ManyToOne
    @JoinColumn(name = "id_role", nullable = false)
    private Long id_user;


    @Column(name = "username", length = 100)
    private String username;

    @Column(name = "mail", length = 100)
    private String mail;

    @Column(name = "password", length = 200)
    private String password;

    @OneToOne(mappedBy = "Basic_User")
    private User_Roles user_roles;

}
