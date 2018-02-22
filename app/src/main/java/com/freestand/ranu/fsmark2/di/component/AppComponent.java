package com.freestand.ranu.fsmark2.di.component;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.freestand.ranu.fsmark2.Activities.FacebookLoginActivity;
import com.freestand.ranu.fsmark2.Activities.LandingScreen;
import com.freestand.ranu.fsmark2.Activities.MainDeciderActivity;
import com.freestand.ranu.fsmark2.AppController;
import com.freestand.ranu.fsmark2.FirebaseIDService;
import com.freestand.ranu.fsmark2.data.sharedpf.SharedPrefsHelper;
import com.freestand.ranu.fsmark2.di.module.AppModule;
import com.freestand.ranu.fsmark2.di.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Provides;

/**
 * Created by prateek on 21/02/18.
 */

@Singleton
@Component(modules=AppModule.class)
public interface AppComponent {
    void inject(LandingScreen landingScreen);
    void inject (FirebaseIDService firebaseIDService);
    void inject (MainDeciderActivity mainDeciderActivity);
    void inject(FacebookLoginActivity facebookLoginActivity);
    Application getApplicationInstance();
    SharedPrefsHelper sharedPrefsHelper();
    NetComponent plusNetComponent (NetModule netModule);
}