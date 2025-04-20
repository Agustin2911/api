package com.uade.tpo.E_Commerce.entity.dto;


import lombok.Data;

@Data
public class ModifyStockResponse {

    private long id_product;
    private int all_stock;
    private long id_shop;
    private int stock_shop;

    public ModifyStockResponse(long id_product, int all_stock, long id_shop, int stock_shop) {
        this.id_product = id_product;
        this.all_stock = all_stock;
        this.id_shop = id_shop;
        this.stock_shop = stock_shop;
    }


    public long getId_product() {
        return id_product;
    }

    public int getAll_stock() {
        return all_stock;
    }

    public long getId_shop() {
        return id_shop;
    }

    public int getStock_shop() {
        return stock_shop;
    }
}
