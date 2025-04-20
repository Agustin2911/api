
package com.uade.tpo.E_Commerce.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;

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


    @JsonIgnore
    @OneToOne(mappedBy = "basic_user")
    private User_Roles user_roles;

    @JsonIgnore
    @OneToOne(mappedBy = "basic_user")
    private Buyer_User buyer_user;

    @JsonIgnore
    @OneToOne(mappedBy = "basic_user")
    private Seller_User seller_user;

    public Long getId_user() {
        return id_user;
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

    public User_Roles getUser_roles() {
        return user_roles;
    }

    public Buyer_User getBuyer_user() {
        return buyer_user;
    }

    public Seller_User getSeller_user() {
        return seller_user;
    }
}

