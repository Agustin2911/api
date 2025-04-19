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


}
