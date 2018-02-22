package com.freestand.ranu.fsmark2;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.facebook.stetho.Stetho;
import com.freestand.ranu.fsmark2.di.component.AppComponent;
import com.freestand.ranu.fsmark2.di.component.DaggerAppComponent;
import com.freestand.ranu.fsmark2.di.module.AppModule;
import com.google.firebase.analytics.FirebaseAnalytics;

import javax.inject.Inject;

import io.fabric.sdk.android.Fabric;

/**
 * Created by prateek on 7/1/18.
 */
public class AppController extends Application {

    private final String TAG = getClass().getSimpleName();
    private static AppController mInstance;
    public static FirebaseAnalytics firebaseAnalytics;
    public static SharedPreferences sharedPreferences;
//    public static final String MyPREFERENCES = Constants.FREESTAND_PREFS ;
    private AppComponent appComponent;

    @Inject AppController () {
        setAppComponent();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Stetho.initializeWithDefaults(this);
//        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        Fabric.with(this, new Crashlytics());
        Stetho.initializeWithDefaults(this);
        initilaiseFireBaseDb();
    }

    private void setAppComponent() {
        appComponent = DaggerAppComponent.builder()
                // list of modules that are part of this component need to be created here too
                .appModule(new AppModule(this))// This also corres
                .build();
    }

    private void initilaiseFireBaseDb() {}

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public static void showToast(String show){
        Toast.makeText(getInstance(),show, Toast.LENGTH_LONG).show();
    }

    public static boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

}