package com.uade.tpo.E_Commerce.service;

import com.uade.tpo.E_Commerce.entity.Delivery_Status;
import com.uade.tpo.E_Commerce.entity.Sale;
import com.uade.tpo.E_Commerce.repository.Delivery_StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Delivery_StatusServiceImpl implements Delivery_StatusService {

    @Autowired
    private Delivery_StatusRepository delivery_statusRepository;

    public Optional<Delivery_Status> getDeliveryStatusById(Long id) {
        return delivery_statusRepository.findDeliveryStatusById(id);
    }


    public Delivery_Status createDeliveryStatus(Long id_sale, String delivery_type, String address,
                                                String delivery_status)  /*throws DuplicateException*/ {
        boolean exists = (delivery_statusRepository.findDeliveryStatusBySale(id_sale)) != null;
        if(!exists){
            delivery_statusRepository.createNewDeliveryStatus(id_sale,delivery_type,address,delivery_status);
            Delivery_Status new_delivery = delivery_statusRepository.findDeliveryStatusBySale(id_sale);
        }
        return null;
//        throw new DuplicateException();
    }


    public Optional<Delivery_Status> updateDeliveryStatus(Long id_delivery, Long id_sale, String delivery_type,
                                                          String address, String delivery_status) {
        Optional<Delivery_Status> current_delivery = delivery_statusRepository.findDeliveryStatusById(id_delivery);
        if (current_delivery.isPresent()){
            delivery_statusRepository.updateDeliveryStatus(id_delivery,id_sale,delivery_type,address,delivery_status);
            return delivery_statusRepository.findDeliveryStatusById(id_delivery);
        }
        return Optional.empty();
//      new NotFoundException();

    }


    public boolean deleteDeliveryStatus(Long id_delivery) {
        delivery_statusRepository.deleteDeliveryStatus(id_delivery);
        Optional<Delivery_Status> check_delivery = delivery_statusRepository.findDeliveryStatusById(id_delivery);
        return check_delivery.isEmpty();
    }
}
