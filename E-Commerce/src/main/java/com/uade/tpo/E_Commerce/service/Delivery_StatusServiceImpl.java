package com.uade.tpo.E_Commerce.service;

import com.uade.tpo.E_Commerce.entity.Delivery_Status;
import com.uade.tpo.E_Commerce.repository.Delivery_StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Delivery_StatusServiceImpl implements Delivery_StatusService {

    @Autowired
    private Delivery_StatusRepository delivery_statusRepository;

    public Optional<Delivery_Status> getDeliveryStatusById(Long id) {
        return Optional.of(delivery_statusRepository.findDeliveryStatusById(id));
    }


    public Delivery_Status createDeliveryStatus(Long id_sale, String delivery_type, String address,
                                                String delivery_status)  /*throws DuplicateException*/ {
        boolean exists = delivery_statusRepository.findById(id_sale).isPresent();
        if(!exists){
            return delivery_statusRepository.createNewDeliveryStatus(id_sale,delivery_type,address,
                    delivery_status);
        }
        return null;
//        throw new DuplicateException();
    }


    public Optional<Delivery_Status> updateDeliveryStatus(Long id_delivery, Long id_sale, String delivery_type,
                                                          String address, String delivery_status) {
        Delivery_Status current_delivery = delivery_statusRepository.findDeliveryStatusById(id_delivery);
        if (current_delivery != null){
            return Optional.of(delivery_statusRepository.updateDeliveryStatus(id_delivery,id_sale,delivery_type,address,delivery_status));
        }
        return Optional.empty();
//      new NotFoundException();

    }


    public boolean deleteDeliveryStatus(Long id_delivery) {
        boolean exists = delivery_statusRepository.findById(id_delivery).isPresent();
        if(exists){
            return delivery_statusRepository.deleteDeliveryStatus(id_delivery);

        }
        return false;
    }
}
