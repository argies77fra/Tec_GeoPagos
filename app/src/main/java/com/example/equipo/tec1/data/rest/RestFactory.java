package com.example.equipo.tec1.data.rest;

import android.content.Context;
import android.util.Log;

import com.example.equipo.tec1.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestFactory {

    private static TecApi tecApi;

    public static TecApi get() {

        if (tecApi == null) {

                tecApi = buildRetrofit(TecApi.PRODUCTION_URL)
                        .create(TecApi.class);

        }

        return tecApi;
    }

    private static Retrofit buildRetrofit(String baseUrl) {

        Gson gson = new GsonBuilder()
                .serializeNulls()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        OkHttpClient.Builder client = new OkHttpClient.Builder();

        client.addInterceptor(new LoggingInterceptor.Builder()
                .loggable(BuildConfig.DEBUG)
                .setLevel(Level.BASIC)
                .log(Log.INFO)
                .request("Request")
                .response("Response")
                .addHeader("version", BuildConfig.VERSION_NAME)
                .build());

        client.readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS);

        OkHttpClient okHttpClient = client.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    public static void clearApi(){
        tecApi = null;
    }

}
