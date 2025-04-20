package com.uade.tpo.E_Commerce.entity.dto;

public class SuccesResponse {

    private String respond;
    private String status;

    public SuccesResponse(String respond) {
        this.respond = respond;
        this.status = "success";
    }

    public String getRespond() {
        return respond;
    }

    public String getStatus() {
        return status;
    }
}
