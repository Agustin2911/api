package com.uade.tpo.E_Commerce.entity.dto;

public class newSeller_User {

    // Se incluye id_user, ya que es el identificador del Basic_User que se usará para el seller.
    private long id_user;
    private int cuit;
    private String companyName;
    private String description;
    private String state;

    public newSeller_User() {}

    public newSeller_User(long id_user, int cuit, String companyName, String description, String state) {
        this.id_user = id_user;
        this.cuit = cuit;
        this.companyName = companyName;
        this.description = description;
        this.state = state;
    }

    // Getters
    public long getId_user() {
        return id_user;
    }

    public int getCuit() {
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
}
