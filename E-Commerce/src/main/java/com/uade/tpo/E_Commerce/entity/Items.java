package com.uade.tpo.E_Commerce.entity;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Items {
    private int id_items;
    private int id_product;
    private int id_sale;
    private int amount;


    public Items(int id_items, int id_product, int id_sale, int amount){
        this.id_items = id_items;
        this.id_product = id_product;
        this.id_sale = id_sale;
        this.amount = amount;
    }
}
