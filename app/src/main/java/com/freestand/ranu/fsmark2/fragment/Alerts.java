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
import com.freestand.ranu.fsmark2.common.Utility;
import com.freestand.ranu.fsmark2.data.model.alert.Alert;
import com.freestand.ranu.fsmark2.data.network.rest.ApiInterface;
import com.freestand.ranu.fsmark2.di.ComponentFactory;
import com.freestand.ranu.fsmark2.di.NetScope;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by prateek on 14/1/18.
 */

public class Alerts extends BaseFragment{
    @Inject @NetScope
    Retrofit retrofitClient;
    @BindView(R.id.rv_alerts) RecyclerView rv_alerts;
    private List<Alert> alertList = new ArrayList<>();
    private AlertAdapter alertAdapter;

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
        return R.layout.fragment_alerts;
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
        alertAdapter= new AlertAdapter(alertList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(AppController.getInstance());
        rv_alerts.setLayoutManager(mLayoutManager);
        rv_alerts.setItemAnimator(new DefaultItemAnimator());
        rv_alerts.setAdapter(alertAdapter);
    }

    private void getData() {
        Utility.DialogClass.showPleaseWait(getContext(), "Loading. Please wait...");
        ApiInterface apiService =
                retrofitClient.create(ApiInterface.class);
        Call<List<Alert>> call = apiService.getAlerts();
        call.enqueue(new Callback<List<Alert>>() {
            @Override
            public void onResponse(Call<List<Alert>>call, Response<List<Alert>> response) {
                alertList.clear();
                alertList.addAll(response.body());
                alertAdapter.notifyDataSetChanged();
                Utility.DialogClass.dismissPleaseWait();
            }
            @Override
            public void onFailure(Call<List<Alert>>call, Throwable t) {
                // Log error here since request failed
                Log.e(" ", t.toString());
                Utility.DialogClass.dismissPleaseWait();
            }
        });
    }
}
