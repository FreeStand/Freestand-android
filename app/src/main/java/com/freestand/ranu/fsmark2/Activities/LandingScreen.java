package com.freestand.ranu.fsmark2.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.freestand.ranu.fsmark2.R;
import com.freestand.ranu.fsmark2.customview.BottomNavigationViewHelper;
import com.freestand.ranu.fsmark2.data.UserHandler;
import com.freestand.ranu.fsmark2.fragment.Alerts;
import com.freestand.ranu.fsmark2.fragment.Home;
import com.freestand.ranu.fsmark2.fragment.QRScanner;
import com.google.firebase.iid.FirebaseInstanceId;

public class LandingScreen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_landing);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        setBottomBar();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo_white);
        getSupportActionBar().setTitle("");
        Log.e("hello ", FirebaseInstanceId.getInstance().getToken());
        UserHandler userHandler = new UserHandler(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment;
        switch (id){
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
            case R.id.total_saving:
                return true;
            case R.id.delivery_status:
                return true;
            case R.id.faq:
                return true;
            case R.id.contact_us:
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto",getString(R.string.freestand_email), null));
                emailIntent.putExtra(Intent.EXTRA_EMAIL, getString(R.string.freestand_email));
                startActivity(Intent.createChooser(emailIntent, "Send Email..."));
                return true;
            case R.id.log_out:
                return true;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }

    private void setBottomBar() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
    }
}