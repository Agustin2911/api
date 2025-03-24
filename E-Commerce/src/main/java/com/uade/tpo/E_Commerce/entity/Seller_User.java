package com.uade.tpo.E_Commerce.entity;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Seller_User {

    private int id_user;
    private int cuit;
    private String company_name;
    private String description;
    private String state;


    public void Seller_user(int id, int cuit_company,String company, String description_brand, String state_brand){
        this.id_user=id;
        this.cuit=cuit_company;
        this.description=description_brand;
        this.company_name=company;
        this.state=state_brand;
    }


}
