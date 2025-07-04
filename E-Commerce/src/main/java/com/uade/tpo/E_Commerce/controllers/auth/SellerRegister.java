package com.uade.tpo.E_Commerce.controllers.auth;

import jakarta.annotation.Nullable;
import org.springframework.web.multipart.MultipartFile;

public class SellerRegister extends  RegisterRequest{



    private long cuit;
    private String companyName;
    private String description;
    private String state;
    @Nullable
    private MultipartFile file;


    public SellerRegister() {

    }


    public long getCuit() {
        return cuit;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getDescription() {
        return description;
    }

    public String getState() {
        return state;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setCuit(long cuit) {
        this.cuit = cuit;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
