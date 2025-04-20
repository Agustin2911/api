package com.uade.tpo.E_Commerce.entity.dto;

public class CreateProductStockDTO {

    private long id;
    private int stock_entry;
    private long shop;
    private int stock_warning;

    public CreateProductStockDTO(long id, int stock_entry, long shop, int stock_warning) {
        this.id = id;
        this.stock_entry = stock_entry;
        this.shop = shop;
        this.stock_warning = stock_warning;

    }

    public long getId() {
        return id;
    }

    public int getStock_entry() {
        return stock_entry;
    }

    public long getShop() {
        return shop;
    }

    public int getStock_warning() {
        return stock_warning;
    }

}
