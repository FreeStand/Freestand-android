package com.freestand.ranu.fsmark2.Activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.freestand.ranu.fsmark2.R;
import com.freestand.ranu.fsmark2.customview.BottomNavigationViewHelper;
import com.freestand.ranu.fsmark2.fragment.Alerts;
import com.freestand.ranu.fsmark2.fragment.Home;
import com.freestand.ranu.fsmark2.fragment.More;
import com.freestand.ranu.fsmark2.fragment.QRScanner;

public class AfterLogin extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBottomBar();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo_white);
        getSupportActionBar().setTitle("");
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_after_login;
    }

    private void setBottomBar() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment;
        switch (item.getItemId()) {
            case R.id.action_home:
                fragment = new Home();
                loadFragment(fragment);
                return true;
            case R.id.action_qr:
                fragment = new QRScanner();
                loadFragment(fragment);
                return true;
            case R.id.action_alerts:
                fragment = new Alerts();
                loadFragment(fragment);
                return true;
            case R.id.action_more:
                fragment = new More();
                loadFragment(fragment);
                return true;
        }

        return false;
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }
}
