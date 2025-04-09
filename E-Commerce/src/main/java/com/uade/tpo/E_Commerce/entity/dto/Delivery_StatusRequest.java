
package com.uade.tpo.E_Commerce.entity.dto;

import lombok.Data;

@Data
public class Delivery_StatusRequest {

    private Long id_delivery;
    private Long id_sale;
    private String delivery_type;
    private String address;
    private String delivery_status;

}
