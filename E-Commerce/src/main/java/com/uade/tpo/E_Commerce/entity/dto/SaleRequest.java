
package com.uade.tpo.E_Commerce.entity.dto;

import com.uade.tpo.E_Commerce.entity.Items;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

@Data
public class SaleRequest {

    private Long id_sale;
    private int total_price;
    private Long id_user;
    private LocalDateTime sale_date;
    private ArrayList<ItemsRequest> items;
    private Long id_shop;


    public Long getId_sale() {
        return id_sale;
    }

    public int getTotal_price() {
        return total_price;
    }

    public Long getId_user() {
        return id_user;
    }

    public LocalDateTime getSale_date() {
        return sale_date;
    }

    public ArrayList<ItemsRequest> getItems() {
        return items;
    }


    public Long getId_shop(){return id_shop;}

}

