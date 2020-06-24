package com.example.equipo.tec1.data.rest;


import com.example.equipo.tec1.domain.model.CardIssuer;
import com.example.equipo.tec1.domain.model.Installment;
import com.example.equipo.tec1.domain.model.PaymentMethod;
import java.util.List;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;



public interface TecApi {

    String PRODUCTION_URL = "https://api.mercadopago.com/v1/";

    @GET("payment_methods")
    Observable<List<PaymentMethod>> getMetodosPago(@Query("public_key") String public_key);//    (@Body CheckoutRequest model);

    @GET("payment_methods/card_issuers")
    Observable<List<CardIssuer>> getEmisorasTarjetas(@Query("public_key") String public_key,
                                                     @Query("payment_method_id") String payment_method_id);

    @GET("payment_methods/installments")
    Observable<List<Installment>> getCuotas(@Query("public_key") String public_key,
                                            @Query("amount") String amount,
                                            @Query("payment_method_id") String payment_method_id,
                                            @Query("issuer.id") String id);
}

