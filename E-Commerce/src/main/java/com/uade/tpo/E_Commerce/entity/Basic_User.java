
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

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUser_roles(User_Roles user_roles) {
        this.user_roles = user_roles;
    }

    public void setBuyer_user(Buyer_User buyer_user) {
        this.buyer_user = buyer_user;
    }

    public void setSeller_user(Seller_User seller_user) {
        this.seller_user = seller_user;
    }
}

