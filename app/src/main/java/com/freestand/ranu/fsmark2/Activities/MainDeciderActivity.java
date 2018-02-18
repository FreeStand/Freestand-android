package com.freestand.ranu.fsmark2.Activities;

import android.content.Intent;
import android.media.FaceDetector;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.freestand.ranu.fsmark2.R;
import com.freestand.ranu.fsmark2.data.sharedpf.SharedPrefsHelper;

public class MainDeciderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main_decider);
        if(SharedPrefsHelper.get("IS_LOGGED_IN", false)) {
            Intent intent = new Intent(this, LandingScreen.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, FacebookLoginActivity.class);
            startActivity(intent);
        }
    }
}
