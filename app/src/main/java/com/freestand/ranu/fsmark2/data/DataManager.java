//package com.freestand.ranu.fsmark2.data;
//
//import android.content.Context;
//import android.content.res.Resources;
//
//import com.freestand.ranu.fsmark2.data.sharedpf.SharedPrefsHelper;
//
//import javax.inject.Inject;
//import javax.inject.Singleton;
//
///**
// * Created by prateek on 11/1/18.
// */
//
//
//@Singleton
//public class DataManager {
//
//    private Context mContext;
//    private DatabaseHandler mDbHandler;
//    private SharedPrefsHelper mSharedPrefsHelper;
//
//    @Inject
//    public DataManager(@ApplicationContext Context context,
//                       DatabaseHandler mDbHandler,
//                       SharedPrefsHelper sharedPrefsHelper) {
//        mContext = context;
//        this.mDbHandler = mDbHandler;
//        mSharedPrefsHelper = sharedPrefsHelper;
//    }
//
//    public void saveAccessToken(String accessToken) {
//        mSharedPrefsHelper.put(SharedPrefsHelper.PREF_KEY_ACCESS_TOKEN, accessToken);
//    }
//
//    public String getAccessToken(){
//        return mSharedPrefsHelper.get(SharedPrefsHelper.PREF_KEY_ACCESS_TOKEN, null);
//    }
//
//    public Long createUser(User user) throws Exception {
//        return mDbHelper.insertUser(user);
//    }
//
//    public User getUser(Long userId) throws Resources.NotFoundException, NullPointerException {
//        return mDbHelper.getUser(userId);
//    }
//}