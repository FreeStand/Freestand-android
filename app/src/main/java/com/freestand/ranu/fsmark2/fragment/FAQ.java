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
import com.freestand.ranu.fsmark2.adapter.FaqAdapter;
import com.freestand.ranu.fsmark2.data.model.FAQ.Faq;
import com.freestand.ranu.fsmark2.data.model.alert.Alert;
import com.freestand.ranu.fsmark2.data.network.rest.ApiClient;
import com.freestand.ranu.fsmark2.data.network.rest.ApiInterface;
import com.freestand.ranu.fsmark2.di.ComponentFactory;

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
 * Created by prateek on 07/02/18.
 */

public class FAQ extends Fragment{
    @BindView(R.id.rv_faq)
    RecyclerView rv_faq;
    @Inject
    Retrofit retrofitClient;
    private List<Faq> faqList = new ArrayList<>();
    private FaqAdapter faqAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_faq, container, false);
        ButterKnife.bind(this, view);
        ComponentFactory.getComponentFactory().getNetComponent().inject(this);
        getData();
        return view;
    }

    private void setRecyclerView() {

        faqAdapter= new FaqAdapter(faqList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(AppController.getInstance());
        rv_faq.setLayoutManager(mLayoutManager);
        rv_faq.setItemAnimator(new DefaultItemAnimator());
        rv_faq.setAdapter(faqAdapter);

    }

    private void getData() {
        ApiInterface apiService =
                retrofitClient.create(ApiInterface.class);

        Call<List<Faq>> call = apiService.getFaqs();
        call.enqueue(new Callback<List<Faq>>() {
            @Override
            public void onResponse(Call<List<Faq>>call, Response<List<Faq>> response) {
                Log.e("response ", response.toString());
                faqList = response.body();
                setRecyclerView();
                Log.d("hello ", "Number of alerts received: " + faqList.size());
                faqAdapter.notifyDataSetChanged();
                Log.e("list ", faqList.get(0).getAnswer()+ " hey");
            }

            @Override
            public void onFailure(Call<List<Faq>>call, Throwable t) {
                // Log error here since request failed
                Log.e(" ", t.toString());
            }
        });

    }

}
