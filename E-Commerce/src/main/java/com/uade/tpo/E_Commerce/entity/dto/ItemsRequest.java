
package com.uade.tpo.E_Commerce.entity.dto;

import lombok.Data;

@Data
public class ItemsRequest {

    private Long id_product;
    private int amount;

    public Long getId_product() {
        return id_product;
    }

    public int getAmount() {
        return amount;
    }
}

