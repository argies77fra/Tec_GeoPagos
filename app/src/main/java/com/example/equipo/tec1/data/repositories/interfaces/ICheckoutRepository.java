package com.example.equipo.tec1.data.repositories.interfaces;

import com.example.equipo.tec1.domain.model.CardIssuer;
import com.example.equipo.tec1.domain.model.Installment;
import com.example.equipo.tec1.domain.model.PaymentMethod;

import java.util.List;

import rx.Observable;

public interface ICheckoutRepository {

    Observable<List<PaymentMethod>> getPaymentMethods(String public_key);//,String amount, String payment_method_id,String id);

    Observable<List<CardIssuer>> getCardIssuers(String public_key, String payment_method_id);//,String amount,String id);

    Observable<List<Installment>> getInstallments(String public_key, String amount, String payment_method_id, String id);



}
