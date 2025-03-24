package com.uade.tpo.E_Commerce.entity;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Buyer_User {

    private int id_user;
    private String name;
    private String last_name;
    private int dni;

    public void Buyer_User(int id,String user_name, String user_last_name,int dni_user ){
        this.id_user=id;
        this.name=user_name;
        this.last_name=user_last_name;
        this.dni=dni_user;
    }



}
