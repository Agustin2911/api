package com.uade.tpo.E_Commerce.entity.dto;

import java.sql.Timestamp;

public class Orders {

    private long id_sale;
    private String delivery_status;
    private Timestamp sale_date;
    private String product_name;
    private int price;
    private int amount;

    public Orders(long id_sale, String delivery_status, Timestamp sale_date, String product_name, int price, int amount) {
        this.id_sale = id_sale;
        this.delivery_status = delivery_status;
        this.sale_date = sale_date;
        this.product_name = product_name;
        this.price = price;
        this.amount = amount;
    }

    public long getId_sale() {
        return id_sale;
    }

    public String getDelivery_status() {
        return delivery_status;
    }

    public Timestamp getSale_date() {
        return sale_date;
    }

    public String getProduct_name() {
        return product_name;
    }

    public int getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public void setId_sale(long id_sale) {
        this.id_sale = id_sale;
    }

    public void setDelivery_status(String delivery_status) {
        this.delivery_status = delivery_status;
    }

    public void setSale_date(Timestamp sale_date) {
        this.sale_date = sale_date;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
