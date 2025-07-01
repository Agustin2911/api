package com.uade.tpo.E_Commerce.entity.dto;

import org.springframework.web.multipart.MultipartFile;

public class newSeller_User {

    // Se incluye id_user, ya que es el identificador del Basic_User que se usará para el seller.
    private long id_user;
    private long cuit;
    private String companyName;
    private String description;
    private String state;
    private String photo_url;
    private MultipartFile file;


    public newSeller_User() {}

    public newSeller_User(long id_user, int cuit, String companyName, String description, String state, String photo_url, MultipartFile file) {
        this.id_user = id_user;
        this.cuit = cuit;
        this.companyName = companyName;
        this.description = description;
        this.state = state;
        this.photo_url = photo_url;
        this.file = file;
    }

    // Getters
    public long getId_user() {
        return id_user;
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

    public String getPhoto_url() {
        return photo_url;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
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

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
