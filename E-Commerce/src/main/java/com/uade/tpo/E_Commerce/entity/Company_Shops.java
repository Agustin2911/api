package com.uade.tpo.E_Commerce.entity;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Company_Shops {
    private int id_user;
    private int id_shop;

    public Company_Shops(int id_user, int id_shop){
        this.id_user = id_user;
        this.id_shop = id_shop;
    }

}
