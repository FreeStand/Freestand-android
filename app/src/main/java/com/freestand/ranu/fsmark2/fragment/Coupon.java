package com.freestand.ranu.fsmark2.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.freestand.ranu.fsmark2.AppController;
import com.freestand.ranu.fsmark2.R;
import com.freestand.ranu.fsmark2.adapter.AlertAdapter;
import com.freestand.ranu.fsmark2.adapter.CouponAdapter;
import com.freestand.ranu.fsmark2.data.model.CouponItem;
import com.freestand.ranu.fsmark2.data.model.alert.Alert;
import com.freestand.ranu.fsmark2.data.network.rest.ApiClient;
import com.freestand.ranu.fsmark2.data.network.rest.ApiInterface;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by prateek on 04/02/18.
 */

public class Coupon extends Fragment {
    @BindView(R.id.rv_coupons)
    RecyclerView rv_coupons;
    private List<CouponItem> couponItemList = new ArrayList<>();
    private CouponAdapter couponAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_coupon, container, false);
        ButterKnife.bind(this, view);
        getData();


        return view;
    }

    private void setRecyclerView() {

        couponAdapter= new CouponAdapter(couponItemList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(AppController.getInstance());
        rv_coupons.setLayoutManager(mLayoutManager);
        rv_coupons.setItemAnimator(new DefaultItemAnimator());
        rv_coupons.setAdapter(couponAdapter);

    }

    private void getData() {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Map<String, String> headers = new HashMap<>();
        headers.put("uid", FirebaseAuth.getInstance().getUid());
        Call<List<CouponItem>> call = apiService.getCoupons(headers);
        call.enqueue(new Callback<List<CouponItem>>() {
            @Override
            public void onResponse(Call<List<CouponItem>>call, Response<List<CouponItem>> response) {
                Log.e("response ", response.toString());
                couponItemList = response.body();
                setRecyclerView();
                Log.d("hello ", "Number of alerts received: " + couponItemList.size());
                couponAdapter.notifyDataSetChanged();
                Log.e("list ", couponItemList.get(0).getBrandName()+ " hey");
            }

            @Override
            public void onFailure(Call<List<CouponItem>>call, Throwable t) {
                // Log error here since request failed
                Log.e(" ", t.toString());
            }
        });

    }
}