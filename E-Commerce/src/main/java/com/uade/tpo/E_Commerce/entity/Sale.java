
package com.uade.tpo.E_Commerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uade.tpo.E_Commerce.entity.dto.ItemsRequest;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity(name = "sale")
public class Sale {


    public Sale(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_sale;

    @Column(name = "total_price", nullable = false)
    private int total_price;

    @Column(name = "sale_date", nullable = false)
    private LocalDateTime sale_date;

    @OneToOne(mappedBy = "sale")
    private Delivery_Status delivery_status;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private Buyer_User buyer_user;


    @OneToMany(mappedBy = "sale")
    private List<Items> items;

    public Long getId_sale() {
        return id_sale;
    }

    public int getTotal_price() {
        return total_price;
    }

    public LocalDateTime getSale_date() {
        return sale_date;
    }

    public Delivery_Status getDelivery_status() {
        return delivery_status;
    }

    public Buyer_User getBuyer_user() {
        return buyer_user;
    }

    public List<Items> getItems() {
        return items;
    }
}

