package com.freestand.ranu.fsmark2.data.sharedpf;

import android.content.SharedPreferences;

import com.freestand.ranu.fsmark2.AppController;

/**
 * Created by prateek on 14/1/18.
 */

public class SharedPrefsHelper {


    public static SharedPreferences mSharedPreferences = AppController.sharedPreferences;


    public static void put(String key, String value) {
        mSharedPreferences.edit().putString(key, value).apply();
    }

    public static void put(String key, int value) {
        mSharedPreferences.edit().putInt(key, value).apply();
    }

    public static void put(String key, float value) {
        mSharedPreferences.edit().putFloat(key, value).apply();
    }

    public static void put(String key, boolean value) {
        mSharedPreferences.edit().putBoolean(key, value).apply();
    }

    public static String get(String key, String defaultValue) {
        return mSharedPreferences.getString(key, defaultValue);
    }

    public static Integer get(String key, int defaultValue) {
        return mSharedPreferences.getInt(key, defaultValue);
    }

    public static Float get(String key, float defaultValue) {
        return mSharedPreferences.getFloat(key, defaultValue);
    }

    public static Boolean get(String key, boolean defaultValue) {
        return mSharedPreferences.getBoolean(key, defaultValue);
    }

    public static void deleteSavedData(String key) {
        mSharedPreferences.edit().remove(key).apply();
    }
}
