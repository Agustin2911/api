package com.uade.tpo.E_Commerce.service;

import com.uade.tpo.E_Commerce.entity.Items;
import com.uade.tpo.E_Commerce.entity.Sale;
import com.uade.tpo.E_Commerce.entity.dto.ItemsRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

public interface SaleService {

    //GET
    public Optional<Sale> getSaleById(Long id_sale);

    //POST
    public Sale createSale(int total_price, Long id_user, LocalDateTime sale_date, ArrayList<ItemsRequest> items,
                           Long id_sale, String delivery_type, String address, String delivery_status);

    //PUT
    public Optional<Sale> updateSale(Long id_sale, int total_price, Long id_user, LocalDateTime sale_date);

    //DELETE
    public boolean deleteSaleById(Long id_sale);
}
