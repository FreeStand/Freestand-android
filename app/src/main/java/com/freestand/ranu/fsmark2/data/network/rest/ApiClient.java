package com.freestand.ranu.fsmark2.data.network.rest;

/**
 * Created by prateek on 14/1/18.
 */
import com.freestand.ranu.fsmark2.di.NetScope;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class ApiClient {

    public static final String BASE_URL = "https://us-central1-fsmark0-c03e0.cloudfunctions.net/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}