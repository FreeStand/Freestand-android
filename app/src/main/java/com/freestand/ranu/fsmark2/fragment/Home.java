package com.freestand.ranu.fsmark2.fragment;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

import com.freestand.ranu.fsmark2.R;

/**
 * Created by prateek on 14/1/18.
 */

public class Home extends BaseFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Button button = (Button) view.findViewById(R.id.swipe_button);

//        final AnimationDrawable drawable = new AnimationDrawable();
//        final Handler handler = new Handler();
//
//        drawable.addFrame(new ColorDrawable(Color.RED), 400);
//        drawable.addFrame(new ColorDrawable(Color.GREEN), 400);
//        drawable.setOneShot(false);
//
//
//        button.setBackgroundDrawable(drawable);
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                drawable.start();
//            }
//        }, 100);

//
//        TransitionDrawable drawable = (TransitionDrawable) button.getBackground();
//        drawable.startTransition(2000);


        return view;
    }

    @Override
    int setViewId() {
        return 0;
    }

    @Override
    void onFragmentCreated() {

    }

    @Override
    void bindView(View view) {

    }

    @Override
    void getComponentFactory() {

    }
}
