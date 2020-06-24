package com.example.equipo.tec1.data.request;

import com.google.gson.annotations.SerializedName;

public class CheckoutRequest {


    public CheckoutRequest(String public_key) {
        this.public_key = public_key;
    }

    public String getPublic_key() {
        return public_key;
    }

    public void setPublic_key(String public_key) {
        this.public_key = public_key;
    }

    @SerializedName("access_token")
    private String  public_key;

    @SerializedName("id")
    private String  id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }




}
