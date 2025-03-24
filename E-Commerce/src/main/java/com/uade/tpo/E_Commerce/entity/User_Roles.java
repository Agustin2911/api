package com.uade.tpo.E_Commerce.entity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User_Roles {

    private int id_role;
    private int id_user;

    public User_Roles(int id_r,int id_u){
        this.id_role=id_r;
        this.id_user=id_u;
    }

}
