package com.uade.tpo.E_Commerce.entity.dto;

public class StockWarningModification {

    private long id;
    private int stock_warning;


    public StockWarningModification(long id, int stock_warning) {
        this.id = id;
        this.stock_warning = stock_warning;
    }

    public long getId() {
        return id;
    }

    public int getStock_warning() {
        return stock_warning;
    }
}
