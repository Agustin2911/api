package com.uade.tpo.E_Commerce.controllers.auth;

import jakarta.annotation.Nullable;
import org.springframework.web.multipart.MultipartFile;

public class BuyerRegister extends RegisterRequest{

    private String name;
    private String last_name;
    private int dni;
    @Nullable
    private MultipartFile file;


    public BuyerRegister() {

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

    public MultipartFile getFile() {
        return file;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
