package com.example.equipo.tec1.data.rest;

import android.os.Message;
import android.util.Log;


import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;


import java.io.IOException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import rx.Subscriber;

public abstract class ApiSubscriber<T> extends Subscriber<T> {

    @SuppressWarnings("FieldCanBeLocal")
    private static String TAG = "ApiSubscriber";

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof retrofit2.HttpException) {
            Message message;
            retrofit2.HttpException response = (retrofit2.HttpException) e;
            Gson gson = new Gson();
            try {
                message = gson.fromJson(response.response().errorBody().string(), Message.class);
                Log.e(TAG, response.response().errorBody().string());

                int code = response.code();


            } catch (IOException ioException) {
                ioException.printStackTrace();
                Log.e(TAG, ((retrofit2.HttpException) e).message());

            } catch (JsonSyntaxException jsonSyntaxException){
                jsonSyntaxException.printStackTrace();
                onError(11, "Ha surgido un problema.1");
            }
        } else if(e instanceof UnknownHostException) {
            Message message = new Message();
            UnknownHostException response = (UnknownHostException) e;

            Log.e(TAG, response.getMessage());

            int code = -1;


        } else if (e instanceof TimeoutException){
            TimeoutException timeoutException = (TimeoutException) e;
            String message = "TimeoutException";
            if (timeoutException.getMessage() != null) message = timeoutException.getMessage();
            timeoutException.printStackTrace();
            Log.e(TAG, message);

            int code = -2;
            onError(code, "Ha ocurrido un error");
        } else {
            e.printStackTrace();

            onError(-3, "Ha ocurrido un error");
        }


    }

    public abstract void onError(Integer statusCode, String message);

}
