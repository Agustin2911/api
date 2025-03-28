package com.uade.tpo.E_Commerce.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Seller_User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_user;

    @Column(name = "cuit", nullable = false)
    private int cuit;

    @Column(name = "company_name", length = 50)
    private String company_name;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "state", length = 30)
    private String state;

    @OneToMany(mappedBy = "Seller_User")
    private Company_Shops company_shops;

}
