package com.example.reyanthonypaano.apitester.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    public static final String API_BASE_URL = "http://172.16.26.124:8080/shifu/";

    private static Retrofit.Builder builder = new Retrofit.Builder()
                                                .baseUrl(API_BASE_URL)
                                                .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    public static Retrofit getClient() {
        return retrofit;
    }
}
