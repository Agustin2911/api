package com.uade.tpo.E_Commerce.entity;

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

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private Buyer_User id_user;

    @OneToMany(mappedBy = "sale")
    private ArrayList<Items> items;


}

