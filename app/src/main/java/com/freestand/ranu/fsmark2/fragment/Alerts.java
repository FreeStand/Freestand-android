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
import com.freestand.ranu.fsmark2.data.model.Alert;
import com.freestand.ranu.fsmark2.data.network.rest.ApiClient;
import com.freestand.ranu.fsmark2.data.network.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by prateek on 14/1/18.
 */

public class Alerts extends Fragment{
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_alerts, container, false);
        ButterKnife.bind(this, view);
        getData();
        

        return view;
    }

    private void setRecyclerView() {

        alertAdapter= new AlertAdapter(alertList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(AppController.getInstance());
        rv_alerts.setLayoutManager(mLayoutManager);
        rv_alerts.setItemAnimator(new DefaultItemAnimator());
        rv_alerts.setAdapter(alertAdapter);

    }

    private void getData() {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<List<Alert>> call = apiService.getAlerts();
        call.enqueue(new Callback<List<Alert>>() {
            @Override
            public void onResponse(Call<List<Alert>>call, Response<List<Alert>> response) {
                Log.e("response ", response.toString());
                alertList = response.body();
                setRecyclerView();
                Log.d("hello ", "Number of alerts received: " + alertList.size());
                alertAdapter.notifyDataSetChanged();
                Log.e("list ", alertList.get(0).getDate()+ " hey");
            }

            @Override
            public void onFailure(Call<List<Alert>>call, Throwable t) {
                // Log error here since request failed
                Log.e(" ", t.toString());
            }
        });

    }
}
