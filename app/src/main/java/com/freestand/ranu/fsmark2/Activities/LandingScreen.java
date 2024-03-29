package com.freestand.ranu.fsmark2.Activities;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.freestand.ranu.fsmark2.AppController;
import com.freestand.ranu.fsmark2.Constants;
import com.freestand.ranu.fsmark2.R;
import com.freestand.ranu.fsmark2.customview.BottomNavigationViewHelper;
import com.freestand.ranu.fsmark2.data.FirebaseDatabaseHelper;
import com.freestand.ranu.fsmark2.data.UserHandler;
import com.freestand.ranu.fsmark2.data.sharedpf.SharedPrefsHelper;
import com.freestand.ranu.fsmark2.di.ComponentFactory;
import com.freestand.ranu.fsmark2.fragment.Alerts;
import com.freestand.ranu.fsmark2.fragment.Coupon;
import com.freestand.ranu.fsmark2.fragment.FAQ;
import com.freestand.ranu.fsmark2.fragment.Home;
import com.freestand.ranu.fsmark2.fragment.QRScanner;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import javax.inject.Inject;

import de.hdodenhof.circleimageview.CircleImageView;


public class LandingScreen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    TextView movingInfo;
    @Inject AppController appController;
    @Inject SharedPrefsHelper sharedPrefsHelper;
    CircleImageView profileImage ;
    TextView userName, email;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_landing);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);

        movingInfo = (TextView)findViewById(R.id.moving_info);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        ComponentFactory.getComponentFactory().getAppComponent(this.getApplication()).inject(this);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View hView =  navigationView.getHeaderView(0);
        profileImage = (CircleImageView) hView.findViewById(R.id.profile_image);
        userName = (TextView) hView.findViewById(R.id.name);
        email = (TextView) hView.findViewById(R.id.email);
        setBottomBar();
        setActionBar();
        Log.e("hello ", FirebaseInstanceId.getInstance().getToken());
        UserHandler userHandler = new UserHandler(this);
        setUserProfile();
        movingInfo.setSelected(true);
        if(getData()!=null) {
            if(getData().equals("partner")) {
                onNavigationItemSelected(bottomNavigationView.getMenu().getItem(1));
            } else if(getData().equals("elsewhere")){
                onNavigationItemSelected(bottomNavigationView.getMenu().getItem(0));
            }
        }

    }

    private void setUserProfile() {
        FirebaseDatabaseHelper.user.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Picasso.with(getBaseContext()).load(dataSnapshot.child("photoURL").getValue().toString()).into(profileImage);
                email.setText(dataSnapshot.child("email").getValue().toString());
                userName.setText(dataSnapshot.child("name").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setActionBar() {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo_white);
        getSupportActionBar().setTitle("");
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
                loadFragmentWithBottomBar(fragment);
                return true;
            case R.id.action_qr:
                fragment = new QRScanner();
                loadFragmentWithBottomBar(fragment);
                return true;
            case R.id.action_alerts:
                fragment = new Alerts();
                loadFragmentWithBottomBar(fragment);
                return true;
            case R.id.action_coupon:
                fragment = new Coupon();
                loadFragmentWithBottomBar(fragment);
                return true;
            case R.id.faq:
                fragment = new FAQ();
                loadFragmentWithoutBottomBar(fragment);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            case R.id.contact_us: {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto",getString(R.string.freestand_email), null));
                emailIntent.putExtra(Intent.EXTRA_EMAIL, getString(R.string.freestand_email));
                startActivity(Intent.createChooser(emailIntent, "Send Email..."));
            }
            case R.id.log_out: {
                sharedPrefsHelper.put("IS_LOGGED_IN", false);
                LoginManager.getInstance().logOut();
                Intent intent = new Intent(this, FacebookLoginActivity.class);
                startActivity(intent);
                finish();
            }
        }
        return true;
    }

    private void loadFragmentWithBottomBar(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }

    private void loadFragmentWithoutBottomBar(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment currentFragment = getSupportFragmentManager()
                .findFragmentById(R.id.frame_no_bottombar);
        if(currentFragment != null) {
            getSupportFragmentManager().popBackStack();
            transaction.add(R.id.frame_no_bottombar, fragment).addToBackStack("no_bottom_frag");
        } else{
            transaction.add(R.id.frame_no_bottombar, fragment).addToBackStack("no_bottom_frag");
        }
        transaction.commit();
    }

    private void setBottomBar() {
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        onNavigationItemSelected(bottomNavigationView.getMenu().getItem(0));
    }

    private String getData() {
        return getIntent().getStringExtra("open");
    }

}
