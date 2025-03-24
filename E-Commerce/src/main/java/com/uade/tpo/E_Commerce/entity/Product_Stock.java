package com.uade.tpo.E_Commerce.entity;


import lombok.Builder;
import lombok.Data;

@Builder
@Data

public class Product_Stock {
    private int id_product;
    private int stock_warning;
    private int id_shop;

    public Product_Stock(int id_product, int stock_warning, int id_shop) {
        this.id_product = id_product;
        this.stock_warning = stock_warning;
        this.id_shop = id_shop;
    }

}
