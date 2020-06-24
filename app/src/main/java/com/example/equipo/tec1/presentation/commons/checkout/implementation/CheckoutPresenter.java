package com.example.equipo.tec1.presentation.commons.checkout.implementation;

import android.widget.Toast;

import com.example.equipo.tec1.data.repositories.implementation.CheckoutRepository;
import com.example.equipo.tec1.data.repositories.interfaces.ICheckoutRepository;
import com.example.equipo.tec1.data.rest.ApiSubscriber;
import com.example.equipo.tec1.domain.model.CardIssuer;
import com.example.equipo.tec1.domain.model.Installment;
import com.example.equipo.tec1.domain.model.PaymentMethod;
import com.example.equipo.tec1.presentation.commons.checkout.interfaces.ICheckoutPresenter;
import com.example.equipo.tec1.presentation.commons.checkout.interfaces.ICheckoutView;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

public class CheckoutPresenter implements ICheckoutPresenter {

    private ICheckoutView view;
    private ICheckoutRepository repository;

    private final static String ACCES_TOKEN="APP_USR-2581510867997531-052619-5d49a70cf4f366fd66e8025c1283ff77-231655826";

    private final static String PUBLIC_KEY="APP_USR-0828514d-0dc6-4269-b284-9c0af5123a6e";

        CheckoutPresenter(ICheckoutView view) {
        this.view = view;
        this.repository = new CheckoutRepository();

    }


    @Override
    public void PaymentMethods() {

        view.showProgress("Cargando Metodos de Pago");
        Observable<List<PaymentMethod>> pmObservable = repository.getPaymentMethods(PUBLIC_KEY);//,"10","amex","279");//ver si agrego la reserva
        pmObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ApiSubscriber<List<PaymentMethod>>() {

                    @Override
                    public void onNext(List<PaymentMethod> paymentMethods) {

                        view.ShowPaymentMethods(paymentMethods);
                    }

                    @Override
                    public void onError(Integer statusCode, String message) {

                    }
                    @Override
                    public void onCompleted()
                    {
                        view.hideProgress();
                    }


                });
    }

    @Override
    public void CardIssuers(final String payment_method_id) {

        view.showProgress("Cargando Emisoras de Tarjeta");
        Observable<List<CardIssuer>> ciObservable = repository.getCardIssuers(PUBLIC_KEY,payment_method_id);//,"10","amex","279");//ver si agrego la reserva
        ciObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ApiSubscriber<List<CardIssuer>>() {

                    @Override
                    public void onNext(List<CardIssuer> cardIssuers) {
                        if(cardIssuers.isEmpty()){
                            view.hideProgress();
                            view.showToast("Debe selecionar otro metodo de pago.");
                        }else{
                            view.ShowCardIssuers(cardIssuers,payment_method_id);
                        }

                    }

                    @Override
                    public void onError(Integer statusCode, String message) {

                    }
                    @Override
                    public void onCompleted()
                    {
                        view.hideProgress();
                    }
                });

    }

    @Override
    public void Installments(String amount, String payment_method_id, String id) {


        String aux =amount.replaceAll("\\s*$","").replaceAll("^\\s*","");

        view.showProgress("Cargando Cuotas");
        Observable<List<Installment>> insObservable = repository.getInstallments(PUBLIC_KEY,aux,payment_method_id,id);//,"10","amex","279");//ver si agrego la reserva
        insObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ApiSubscriber<List<Installment>>() {

                    @Override
                    public void onNext(List<Installment> installment) {

                        if(installment.isEmpty()){
                            view.hideProgress();
                            view.showToast("Debe selecionar otra Entidad Emisora de Tarjeta.");
                        }else{
                            view.ShowInstallments(installment);
                        }


                    }

                    @Override
                    public void onError(Integer statusCode, String message) {

                    }
                    @Override
                    public void onCompleted()
                    {
                        view.hideProgress();
                    }
                });
    }

}
