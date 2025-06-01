package com.uade.tpo.E_Commerce.controllers.auth;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class AuthenticationResponse {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("id_user")
    private long id_user;

    @JsonProperty("photo_url")
    private String photo_url;

    public AuthenticationResponse(String accessToken,long id, String photo_url) {
        this.accessToken = accessToken;
        this.id_user=id;
        this.photo_url=photo_url;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getId_user() {
        return id_user;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }
}
