package com.uade.tpo.E_Commerce.entity;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Delivery_Status {


    public Delivery_Status(){}


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_delivery;

    @Column(name = "delivery_type", length = 14)
    private String delivery_type;

    @Column(name = "address", length = 50)
    private String address;

    @Column(name = "delivery_status", length = 15)
    private String delivery_Status;

    @OneToOne
    @JoinColumn(name = "id_sale", nullable = false)
    private Sale sale;



}

