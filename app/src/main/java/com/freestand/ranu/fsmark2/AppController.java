package com.freestand.ranu.fsmark2;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.facebook.stetho.Stetho;
import com.freestand.ranu.fsmark2.data.FirebaseDatabaseHelper;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import io.fabric.sdk.android.Fabric;

/**
 * Created by prateek on 7/1/18.
 */

public class AppController extends Application {

    private final String TAG = getClass().getSimpleName();
    private static AppController mInstance;
    public static FirebaseAnalytics firebaseAnalytics;
    public static SharedPreferences sharedPreferences;
    public static final String MyPREFERENCES = "FreeStand_Prefs" ;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Stetho.initializeWithDefaults(this);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        Fabric.with(this, new Crashlytics());
        Stetho.initializeWithDefaults(this);
        initilaiseFireBaseDb();
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
}