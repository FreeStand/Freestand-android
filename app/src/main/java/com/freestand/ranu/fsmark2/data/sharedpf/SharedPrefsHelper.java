package com.freestand.ranu.fsmark2.data.sharedpf;

import android.content.SharedPreferences;

import com.freestand.ranu.fsmark2.AppController;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by prateek on 14/1/18.
 */
@Singleton
public class SharedPrefsHelper {


    private SharedPreferences FSSharedPreferences;

    @Inject
    SharedPrefsHelper(SharedPreferences sharedPreferences) {
        this.FSSharedPreferences = sharedPreferences;
    }

    public void put(String key, String value) {
        FSSharedPreferences.edit().putString(key, value).apply();
    }

    public void put(String key, int value) {
        FSSharedPreferences.edit().putInt(key, value).apply();
    }

    public void put(String key, float value) {
        FSSharedPreferences.edit().putFloat(key, value).apply();
    }

    public void put(String key, boolean value) {
        FSSharedPreferences.edit().putBoolean(key, value).apply();
    }

    public String get(String key, String defaultValue) {
        return FSSharedPreferences.getString(key, defaultValue);
    }

    public Integer get(String key, int defaultValue) {
        return FSSharedPreferences.getInt(key, defaultValue);
    }

    public Float get(String key, float defaultValue) {
        return FSSharedPreferences.getFloat(key, defaultValue);
    }

    public Boolean get(String key, boolean defaultValue) {
        return FSSharedPreferences.getBoolean(key, defaultValue);
    }

    public void deleteSavedData(String key) {
        FSSharedPreferences.edit().remove(key).apply();
    }
}
