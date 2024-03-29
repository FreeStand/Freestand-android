package com.freestand.ranu.fsmark2.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.freestand.ranu.fsmark2.Activities.FeedbackScreen;
import com.freestand.ranu.fsmark2.AppController;
import com.freestand.ranu.fsmark2.R;
import com.freestand.ranu.fsmark2.adapter.FaqAdapter;
import com.freestand.ranu.fsmark2.adapter.ProductAdapter;
import com.freestand.ranu.fsmark2.common.Utility;
import com.freestand.ranu.fsmark2.customview.OnSwipeTouchListener;
import com.freestand.ranu.fsmark2.data.model.FAQ.Faq;
import com.freestand.ranu.fsmark2.data.model.checkqr.CheckQr;
import com.freestand.ranu.fsmark2.data.model.checkqr.Question;
import com.freestand.ranu.fsmark2.data.model.home.HomeData;
import com.freestand.ranu.fsmark2.data.model.home.Product;
import com.freestand.ranu.fsmark2.data.network.rest.ApiInterface;
import com.freestand.ranu.fsmark2.di.ComponentFactory;
import com.freestand.ranu.fsmark2.di.NetScope;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by prateek on 14/1/18.
 */

public class Home extends BaseFragment {

    @Inject Retrofit retrofitClient;
    @BindView(R.id.swipe_button) Button swipeButton;
    @BindView(R.id.rv_products) RecyclerView rvProducts;
    @BindView(R.id.tv_message) TextView tvMessage;
    @BindView(R.id.linearLayout2)
    LinearLayout boxToGrab;
    List<Question> questions;
    @BindView(R.id.viewKonfetti) KonfettiView konfettiView;
    String surveyID;
    String sender;
    private List<Product> productList = new ArrayList<>();
    private ProductAdapter productAdapter;

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
        return R.layout.fragment_home;
    }

    @Override
    void onFragmentCreated() {
        setRecyclerView();
        setSwipeButton();
    }

    private void setKonfetti() {
        konfettiView.build()
                .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA, Color.BLUE, Color.CYAN, Color.RED)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 25f)
                .setFadeOutEnabled(true)
                .setTimeToLive(1000000L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .setPosition(0f, konfettiView.getWidth() + 50f, -50f, -50f)
                .stream(70, 5000L);
    }

    private void setSwipeButton() {
        swipeButton.setOnTouchListener(new OnSwipeTouchListener(getContext()) {
            public void onSwipeRight() {
                Intent intent = new Intent(getActivity() , FeedbackScreen.class);
                intent.putExtra("sender", sender);
                intent.putExtra("question_list", (Serializable) questions);
                intent.putExtra("survey_id", surveyID);
                intent.putExtra("direction", "com.freestand.ranu.fsmark2.Activities.ThankYou");
                startActivity(intent);
            }

        });
    }

    @Override
    void bindView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    void getComponentFactory() {
        ComponentFactory.getComponentFactory().getNetComponent().inject(this);
    }

    private void getSetData() {
        Utility.DialogClass.showPleaseWait(getContext(), "Loading. Please wait...");
        ApiInterface apiService = retrofitClient.create(ApiInterface.class);
        Map map = new HashMap();
        map.put("uid", FirebaseAuth.getInstance().getUid());
        map.put("gender", "Male");
        Call<HomeData> call = apiService.getHomeData(map);
        call.enqueue(new Callback<HomeData>() {

            @Override
            public void onResponse(Call<HomeData>call, Response<HomeData> response) {
                HomeData homeData = response.body();
                setData(homeData);
                Utility.DialogClass.dismissPleaseWait();
            }

            @Override
            public void onFailure(Call<HomeData>call, Throwable t) {
                // Log error here since request failed
                Log.e(" ", t.toString());
                Utility.DialogClass.dismissPleaseWait();
            }
        });

    }

    private void setData(HomeData homeData) {
        if(!homeData.getIsEmpty()) {
            if(homeData.getSurveyType().equals("pre")) {
                productList.clear();
                productList.addAll(homeData.getSurvey().getProducts());
                productAdapter.notifyDataSetChanged();
                swipeButton.setEnabled(true);
                questions = homeData.getSurvey().getQuestions();
                surveyID = homeData.getSurvey().getSurveyID();
//                Get the surveyQuestions to show on next screen from questions array
//                Get the surveyID to send to feedback screen from surveyID key
                boxToGrab.setVisibility(View.VISIBLE);
                swipeButton.setText(">>> SWIPE TO COLLECT >>>");
                swipeButton.setBackgroundColor(Color.parseColor("#5B86FF"));
                sender = "pre_sampling";

            } else if (homeData.getSurveyType().equals("post")) {
                swipeButton.setEnabled(true);
                rvProducts.setVisibility(View.GONE);
                tvMessage.setVisibility(View.VISIBLE);
                boxToGrab.setVisibility(View.INVISIBLE);
                tvMessage.setText(homeData.getMessage());
                swipeButton.setText(">>> SWIPE TO ANSWER >>>");
                swipeButton.setBackgroundColor(Color.parseColor("#5E7E47"));
                questions = homeData.getSurvey().getQuestions();
                surveyID = homeData.getSurvey().getSurveyID();
                sender = "post_sampling";
//                Get the surveyQuestions to show on next screen from questions array
//                Get the surveyID to send to feedback screen from surveyID key
            }
        } else {
            setKonfetti();
            boxToGrab.setVisibility(View.INVISIBLE);
            rvProducts.setVisibility(View.GONE);
            tvMessage.setVisibility(View.VISIBLE);
            tvMessage.setText(homeData.getMessage());
            swipeButton.setText(">>> DO NOT SWIPE >>>");
            swipeButton.setBackgroundColor(Color.parseColor("#FF0000"));
        }

    }
    private void setRecyclerView() {
        productAdapter= new ProductAdapter(productList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(AppController.getInstance());
        rvProducts.setLayoutManager(mLayoutManager);
        rvProducts.setItemAnimator(new DefaultItemAnimator());
        rvProducts.setAdapter(productAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        getSetData();
    }
}
