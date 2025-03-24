package com.uade.tpo.E_Commerce.entity;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Basic_User {

    private int id_user ;
    private String username;
    private String mail;
    private String password;

    public void Basic_User(int id,String user, String mail_user, String password_user){
        this.id_user=id;
        this.username=user;
        this.mail=mail_user;
        this.password=password_user;
    }

}
