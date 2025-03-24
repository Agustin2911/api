package com.uade.tpo.E_Commerce.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Roles {


    private int id_role ;
    private String role_name;

    public Roles(int id,String name){
        this.id_role=id;
        this.role_name=name;

    }

}
