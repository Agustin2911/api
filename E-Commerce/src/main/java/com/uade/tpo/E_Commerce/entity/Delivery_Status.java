package com.uade.tpo.E_Commerce.entity;


import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class Delivery_Status {
    private int id_delivery;
    private String delivery_type;
    private String address;
    private int id_sale;
    private String delivery_Status;

    public Delivery_Status(int id_delivery, String delivery_type, String address, int id_sale, String delivery_Status){
        this.id_delivery = id_delivery;
        this.delivery_type = delivery_type;
        this.address = address;
        this.id_sale = id_sale;
        this.delivery_Status = delivery_Status;
    }
}

