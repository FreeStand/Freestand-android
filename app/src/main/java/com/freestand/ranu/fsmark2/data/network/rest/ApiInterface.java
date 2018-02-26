package com.freestand.ranu.fsmark2.data.network.rest;

/**
 * Created by prateek on 14/1/18.
 */
import com.freestand.ranu.fsmark2.data.model.CouponItem;
import com.freestand.ranu.fsmark2.data.model.CouponItemResponse;
import com.freestand.ranu.fsmark2.data.model.FAQ.Faq;
import com.freestand.ranu.fsmark2.data.model.alert.Alert;
import com.freestand.ranu.fsmark2.data.model.checkqr.CheckQr;
import com.freestand.ranu.fsmark2.data.model.home.HomeData;

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

    @GET("showCoupons")
    Call<List<CouponItem>> getCoupons(@QueryMap Map<String, String> params);

    @GET("sendCoupon")
    Call<CouponItemResponse> getUniqueCoupons(@QueryMap Map<String, String> params);

    @GET("sendGeneralCouponSurvey")
    Call<CouponItemResponse> getGeneralCouponSurvey(@QueryMap Map<String, String> params);

    @GET("getFAQs")
    Call<List<Faq>> getFaqs();


    @GET("newHome")
    Call<HomeData> getHomeData(@QueryMap Map<String, String> params);


//    @GET("movie/{id}")
//    Call<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
}
