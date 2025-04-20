
package com.uade.tpo.E_Commerce.entity.dto;

import lombok.Data;

@Data
public class Delivery_StatusRequest {

    private Long id_delivery;
    private Long id_sale;
    private String delivery_type;
    private String address;
    private String delivery_status;

    public Long getId_delivery() {
        return id_delivery;
    }

    public Long getId_sale() {
        return id_sale;
    }

    public String getDelivery_type() {
        return delivery_type;
    }

    public String getAddress() {
        return address;
    }

    public String getDelivery_status() {
        return delivery_status;
    }
}

