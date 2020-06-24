package com.example.equipo.tec1.presentation.commons.checkout.interfaces;

import com.example.equipo.tec1.IView;
import com.example.equipo.tec1.domain.model.CardIssuer;
import com.example.equipo.tec1.domain.model.Installment;
import com.example.equipo.tec1.domain.model.PaymentMethod;

import java.util.List;

public interface ICheckoutView extends IView {

    void ShowPaymentMethods(List<PaymentMethod> paymentMethods);

    void ShowCardIssuers(List<CardIssuer> cardIssuers,String payment_method_id);

    void ShowInstallments(List<Installment> installment);

}
