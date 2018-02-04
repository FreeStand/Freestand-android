package com.freestand.ranu.fsmark2.data.network.rest;

/**
 * Created by prateek on 14/1/18.
 */
import com.freestand.ranu.fsmark2.data.model.alert.Alert;
import com.freestand.ranu.fsmark2.data.model.checkqr.CheckQr;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;


public interface ApiInterface {
    @GET("getAlerts")
    Call<List<Alert>> getAlerts();
    @GET("CheckQR")
    Call<CheckQr> getQrScannedRespose(@QueryMap Map<String, String> params);
//    @GET("movie/{id}")
//    Call<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
}
