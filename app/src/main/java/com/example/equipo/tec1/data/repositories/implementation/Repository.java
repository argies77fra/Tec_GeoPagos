package com.example.equipo.tec1.data.repositories.implementation;

import com.example.equipo.tec1.data.rest.RestFactory;
import com.example.equipo.tec1.data.rest.TecApi;


public abstract class Repository {

    public TecApi api() {return RestFactory.get(); }


}
