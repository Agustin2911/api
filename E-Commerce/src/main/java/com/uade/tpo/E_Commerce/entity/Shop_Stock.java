package com.uade.tpo.E_Commerce.entity;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Shop_Stock {
    private int id_product;
    private int id_shop;
    private int stock;

    public Shop_Stock(int id_product, int id_shop, int stock) {
        this.id_product = id_product;
        this.id_shop = id_shop;
        this.stock = stock;
    }
}
