package com.example.equipo.tec1.domain.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Installment {

    @SerializedName("payment_method_id")
    String payment_method_id;

    @SerializedName("payment_type_id")
    String payment_type_id;

    @SerializedName("payer_costs")
    List<PayerCosts> instalments;

    public String getPayment_method_id() {
        return payment_method_id;
    }

    public void setPayment_method_id(String payment_method_id) {
        this.payment_method_id = payment_method_id;
    }

    public String getPayment_type_id() {
        return payment_type_id;
    }

    public void setPayment_type_id(String payment_type_id) {
        this.payment_type_id = payment_type_id;
    }

    public List<PayerCosts> getInstalments() {
        return instalments;
    }

    public void setInstalments(List<PayerCosts> instalments) {
        this.instalments = instalments;
    }




}
