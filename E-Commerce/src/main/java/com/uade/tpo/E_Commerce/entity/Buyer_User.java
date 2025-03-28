package com.uade.tpo.E_Commerce.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "buyer_user")
public class Buyer_User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;

    @Column(name = "name", length =  30)
    private String name;

    @Column(name = "last_name", length = 30)
    private String last_name;

    @Column(name = "dni", nullable = false)
    private int dni;

    @OneToOne(mappedBy = "buyer_user")
    private Sale sale;

}
