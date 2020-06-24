package com.example.equipo.tec1.domain.model;

import com.google.gson.annotations.SerializedName;

public class PayerCosts {


    @SerializedName("installments")
    String installments;

    @SerializedName("recommended_message")
    String recommended_message;

    @SerializedName("installment_amount")
    String installment_amount;

    @SerializedName("total_amount")
    String total_amoun;

    public PayerCosts(String installments, String recommended_message, String installment_amount, String total_amoun) {
        this.installments = installments;
        this.recommended_message = recommended_message;
        this.installment_amount = installment_amount;
        this.total_amoun = total_amoun;
    }

    public String getInstallments() {
        return installments;
    }

    public void setInstallments(String installments) {
        this.installments = installments;
    }

    public String getRecommended_message() {
        return recommended_message;
    }

    public void setRecommended_message(String recommended_message) {
        this.recommended_message = recommended_message;
    }

    public String getInstallment_amount() {
        return installment_amount;
    }

    public void setInstallment_amount(String installment_amount) {
        this.installment_amount = installment_amount;
    }

    public String getTotal_amoun() {
        return total_amoun;
    }

    public void setTotal_amoun(String total_amoun) {
        this.total_amoun = total_amoun;
    }

}
