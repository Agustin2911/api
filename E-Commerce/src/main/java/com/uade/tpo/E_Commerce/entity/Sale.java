package com.uade.tpo.E_Commerce.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Sale {
    private int id_sale;
    private int total_price;
    private int id_user;
    private Date sale_date;

    public Sale (int id_sale, int total_price, int id_user, Date sale_date){
        this.id_sale = id_sale;
        this.total_price = total_price;
        this.id_user = id_user;
        this.sale_date = sale_date;
    }

}

