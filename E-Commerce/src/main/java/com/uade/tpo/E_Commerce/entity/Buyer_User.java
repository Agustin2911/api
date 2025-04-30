
package com.uade.tpo.E_Commerce.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "buyer_user")
public class Buyer_User {

    public Buyer_User(){}

    @Id
    @Column(name = "id_user", nullable = false)
    private Long id_user;

    @Column(name = "name", length =  30)
    private String name;

    @Column(name = "last_name", length = 30)
    private String last_name;

    @Column(name = "dni", nullable = false)
    private int dni;


    @JsonIgnore
    @OneToMany(mappedBy = "buyer_user")
    private List<Sale> sale;


    @JsonIgnore
    @OneToOne
    @MapsId("id_user")
    @JoinColumn(name = "id_user", nullable = false)
    private Basic_User basic_user;


    public Long getId_user() {
        return id_user;
    }

    public String getName() {
        return name;
    }

    public String getLast_name() {
        return last_name;
    }

    public int getDni() {
        return dni;
    }

    public List<Sale> getSale() {
        return sale;
    }
    public Basic_User getBasic_user() {
        return basic_user;
    }
}
