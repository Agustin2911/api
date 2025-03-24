package com.uade.tpo.E_Commerce.entity;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Sub_Category {

    private int id_sub_category;
    private int id_category;
    private String name_sub_category;

    public Sub_Category(int id,int id_cate,String name){
        this.id_category=id;
        this.id_sub_category=id_cate;
        this.name_sub_category=name;
    }


}
