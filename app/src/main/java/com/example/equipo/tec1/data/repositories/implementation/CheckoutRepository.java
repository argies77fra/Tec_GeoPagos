package com.example.equipo.tec1.data.repositories.implementation;

import com.example.equipo.tec1.data.repositories.interfaces.ICheckoutRepository;
import com.example.equipo.tec1.domain.model.CardIssuer;
import com.example.equipo.tec1.domain.model.Installment;
import com.example.equipo.tec1.domain.model.PaymentMethod;
import java.util.List;
import rx.Observable;
import rx.schedulers.Schedulers;



public class CheckoutRepository extends Repository implements ICheckoutRepository {



    @Override
    public Observable<List<PaymentMethod>> getPaymentMethods(String public_key) {
        return api().getMetodosPago(public_key).subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<List<CardIssuer>> getCardIssuers(String public_key, String payment_method_id) {
        return api().getEmisorasTarjetas(public_key,payment_method_id).subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<List<Installment>> getInstallments(String public_key, String amount, String payment_method_id, String id) {
        return api().getCuotas(public_key,amount,payment_method_id,id).subscribeOn(Schedulers.io());
    }
}
