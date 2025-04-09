
package com.uade.tpo.E_Commerce.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "basic_user")
public class Basic_User {


    public Basic_User(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;

    @Column(name = "username", length = 100)
    private String username;

    @Column(name = "mail", length = 100)
    private String mail;

    @Column(name = "password", length = 200)
    private String password;

    @OneToOne(mappedBy = "basic_user")
    private User_Roles user_roles;

    @OneToOne(mappedBy = "basic_user")
    private Buyer_User buyer_user;

    @OneToOne(mappedBy = "basic_user")
    private Seller_User seller_user;
}

