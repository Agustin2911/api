package com.uade.tpo.E_Commerce.entity.dto;

public class newBuyer_User {

    private long id_user;
    private String name;
    private String lastName;
    private int dni;

    public newBuyer_User() {}

    public newBuyer_User(String name, String lastName, int dni) {
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
    }


    public long getId_user() {
        return id_user;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public int getDni() {
        return dni;
    }
}