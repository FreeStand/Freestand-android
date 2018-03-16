package com.freestand.ranu.fsmark2.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.freestand.ranu.fsmark2.AppController;
import com.freestand.ranu.fsmark2.R;
import com.freestand.ranu.fsmark2.adapter.CouponAdapter;
import com.freestand.ranu.fsmark2.common.Utility;
import com.freestand.ranu.fsmark2.data.model.CouponItem;
import com.freestand.ranu.fsmark2.data.network.rest.ApiInterface;
import com.freestand.ranu.fsmark2.di.ComponentFactory;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by prateek on 04/02/18.
 */

public class Coupon extends BaseFragment {
    @BindView(R.id.rv_coupons)
    RecyclerView rv_coupons;
    @Inject
    Retrofit retrofitClient;
    private List<CouponItem> couponItemList = new ArrayList<>();
    private CouponAdapter couponAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    int setViewId() {
        return R.layout.fragment_coupon;
    }

    @Override
    void onFragmentCreated() {
        setRecyclerView();
        getData();
    }

    @Override
    void bindView(View view) {
        ButterKnife.bind(this, view);

    }

    @Override
    void getComponentFactory() {
        ComponentFactory.getComponentFactory().getNetComponent().inject(this);
    }

    private void setRecyclerView() {
        couponAdapter= new CouponAdapter(couponItemList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(AppController.getInstance());
        rv_coupons.setLayoutManager(mLayoutManager);
        rv_coupons.setItemAnimator(new DefaultItemAnimator());
        rv_coupons.setAdapter(couponAdapter);
    }

    private void getData() {
        Utility.DialogClass.showPleaseWait(getContext(), "Loading. Please wait...");
        ApiInterface apiService = retrofitClient.create(ApiInterface.class);
        Map<String, String> headers = new HashMap<>();
        headers.put("uid", FirebaseAuth.getInstance().getUid());
        Call<List<CouponItem>> call = apiService.getCoupons(headers);
        call.enqueue(new Callback<List<CouponItem>>() {
            @Override
            public void onResponse(Call<List<CouponItem>>call, Response<List<CouponItem>> response) {
                if(response.body() != null) {
                    couponItemList.clear();
                    couponItemList.addAll(response.body());
                    couponAdapter.notifyDataSetChanged();
                    Utility.DialogClass.dismissPleaseWait();
                }

            }

            @Override
            public void onFailure(Call<List<CouponItem>>call, Throwable t) {
                // Log error here since request failed
                Utility.DialogClass.dismissPleaseWait();
            }
        });

    }
}