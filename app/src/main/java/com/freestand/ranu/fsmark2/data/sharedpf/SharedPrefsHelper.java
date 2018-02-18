package com.freestand.ranu.fsmark2.data.sharedpf;

import android.content.SharedPreferences;

import com.freestand.ranu.fsmark2.AppController;

/**
 * Created by prateek on 14/1/18.
 */

public class SharedPrefsHelper {


    public static SharedPreferences FSSharedPreferences = AppController.sharedPreferences;


    public static void put(String key, String value) {
        FSSharedPreferences.edit().putString(key, value).apply();
    }

    public static void put(String key, int value) {
        FSSharedPreferences.edit().putInt(key, value).apply();
    }

    public static void put(String key, float value) {
        FSSharedPreferences.edit().putFloat(key, value).apply();
    }

    public static void put(String key, boolean value) {
        FSSharedPreferences.edit().putBoolean(key, value).apply();
    }

    public static String get(String key, String defaultValue) {
        return FSSharedPreferences.getString(key, defaultValue);
    }

    public static Integer get(String key, int defaultValue) {
        return FSSharedPreferences.getInt(key, defaultValue);
    }

    public static Float get(String key, float defaultValue) {
        return FSSharedPreferences.getFloat(key, defaultValue);
    }

    public static Boolean get(String key, boolean defaultValue) {
        return FSSharedPreferences.getBoolean(key, defaultValue);
    }

    public static void deleteSavedData(String key) {
        FSSharedPreferences.edit().remove(key).apply();
    }
}
