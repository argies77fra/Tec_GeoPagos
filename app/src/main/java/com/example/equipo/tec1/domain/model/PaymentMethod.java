package com.example.equipo.tec1.domain.model;

import com.google.gson.annotations.SerializedName;

public class PaymentMethod {

    @SerializedName("id")
    String id;


    @SerializedName("name")
    String name;

    @SerializedName("payment_type_id")
    String payment_type_id;

    public PaymentMethod(String id, String name, String payment_type_id) {
        this.id = id;
        this.name = name;
        this.payment_type_id = payment_type_id;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPayment_type_id() {
        return payment_type_id;
    }

    public void setPayment_type_id(String payment_type_id) {
        this.payment_type_id = payment_type_id;
    }



}
