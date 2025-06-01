package com.uade.tpo.E_Commerce.entity.dto;

import org.springframework.web.multipart.MultipartFile;

public class newBuyer_User {

    private long id_user;
    private String name;
    private String lastName;
    private int dni;
    private MultipartFile file;

    public newBuyer_User() {}

    public newBuyer_User(long id_user, String name, String lastName, int dni, MultipartFile file) {
        this.id_user = id_user;
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.file = file;
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

    public MultipartFile getFile() {
        return file;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}