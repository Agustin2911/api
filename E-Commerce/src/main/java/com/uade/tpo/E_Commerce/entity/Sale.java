package com.uade.tpo.E_Commerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity(name = "sale")
public class Sale {


    public Sale(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_sale;

    @Column(name = "total_price", nullable = false)
    private int total_price;


    @Column(name = "id_user", nullable = false)
    private int id_user;

    @Column(name = "sale_date", nullable = false)
    private Date sale_date;

    @OneToOne(mappedBy = "sale")
    private Delivery_Status delivery_status;

    @OneToOne
    @JoinColumn(name = "id_user", nullable = false)
    private Buyer_User buyer_user;


}

