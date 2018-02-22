package com.freestand.ranu.fsmark2.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.freestand.ranu.fsmark2.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by prateek on 21/02/18.
 */
@Module
public class AppModule  {
    Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }


    @Provides
    @Singleton
    SharedPreferences provideSharedPrefs() {
        return mApplication.getSharedPreferences(Constants.FREESTAND_PREFS, Context.MODE_PRIVATE);
    }
}
