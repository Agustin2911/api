package com.uade.tpo.E_Commerce.service;

import com.uade.tpo.E_Commerce.entity.Delivery_Status;

import java.util.Optional;

@SuppressWarnings("UnnecessaryModifier")
public interface Delivery_StatusService {

    //GET
    public Optional<Delivery_Status> getDeliveryStatusById(Long id);

    //POST
    public Delivery_Status createDeliveryStatus(Long id_sale, String delivery_type, String address,
                                                String delivery_status) /*throws DuplicateException*/;

    //PUT
    public Optional<Delivery_Status> updateDeliveryStatus(Long id_delivery, Long id_sale, String delivery_type, String address,
                                                          String delivery_status);

    //DELETE
    public boolean deleteDeliveryStatus(Long id_delivery);



}
