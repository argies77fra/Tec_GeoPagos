package com.example.equipo.tec1.presentation.commons.checkout.interfaces;

public interface ICheckoutPresenter {

    void PaymentMethods();

    void CardIssuers(String payment_method_id);

    void Installments(String amount,String payment_method_id,String id);

}
