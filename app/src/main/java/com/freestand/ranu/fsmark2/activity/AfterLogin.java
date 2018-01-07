package com.freestand.ranu.fsmark2.activity;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.facebook.Profile;
import com.freestand.ranu.fsmark2.R;
import com.freestand.ranu.fsmark2.customview.BottomNavigationViewHelper;

public class AfterLogin extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBottomBar();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_after_login;
    }

    private void setBottomBar() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
    }
}
