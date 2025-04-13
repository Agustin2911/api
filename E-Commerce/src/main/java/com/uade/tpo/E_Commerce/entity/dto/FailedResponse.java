package com.uade.tpo.E_Commerce.entity.dto;

public class FailedResponse {

    private String Respond;
    private String status;

    public FailedResponse(String respond ) {
        Respond = respond;
        status="failed";
    }

    public String getRespond() {
        return Respond;
    }

    public String getStatus() {
        return status;
    }
}
