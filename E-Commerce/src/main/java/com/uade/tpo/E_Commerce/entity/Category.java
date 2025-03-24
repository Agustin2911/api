package com.uade.tpo.E_Commerce.entity;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Category {

    private int id_category;
    private String name_category;


    public Category(int id,String name){

        this.id_category=id;
        this.name_category=name;
    }

}
