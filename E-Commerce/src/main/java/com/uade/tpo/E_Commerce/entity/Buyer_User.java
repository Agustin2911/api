package com.uade.tpo.E_Commerce.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "buyer_user")
public class Buyer_User {

    public Buyer_User(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;

    @Column(name = "name", length =  30)
    private String name;

    @Column(name = "last_name", length = 30)
    private String last_name;

    @Column(name = "dni", nullable = false)
    private int dni;

    @OneToMany(mappedBy = "buyer_user")
    private ArrayList<Sale> sale;

}
