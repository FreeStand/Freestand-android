package com.freestand.ranu.fsmark2.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.FaceDetector;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.stetho.inspector.domstorage.SharedPreferencesHelper;
import com.freestand.ranu.fsmark2.AppController;
import com.freestand.ranu.fsmark2.R;
import com.freestand.ranu.fsmark2.data.sharedpf.SharedPrefsHelper;
import com.freestand.ranu.fsmark2.di.ComponentFactory;
import com.freestand.ranu.fsmark2.di.component.AppComponent;

import javax.inject.Inject;

public class MainDeciderActivity extends AppCompatActivity {
    @Inject
    SharedPrefsHelper sharedPreferencesHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main_decider);
        ComponentFactory.getComponentFactory().getAppComponent(this.getApplication()).inject(this);
        if(sharedPreferencesHelper.get("IS_LOGGED_IN", false)) {
            Intent intent = new Intent(this, LandingScreen.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, FacebookLoginActivity.class);
            startActivity(intent);
        }
    }
}
