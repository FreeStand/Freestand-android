package com.freestand.ranu.fsmark2.data.network.rest;

/**
 * Created by prateek on 14/1/18.
 */
import com.freestand.ranu.fsmark2.data.model.Alert;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiInterface {
    @GET("getAlerts")
    Call<List<Alert>> getAlerts();
//    @GET("movie/{id}")
//    Call<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
}
