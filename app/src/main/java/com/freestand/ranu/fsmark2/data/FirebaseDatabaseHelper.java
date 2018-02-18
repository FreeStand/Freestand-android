package com.freestand.ranu.fsmark2.data;

import android.util.Log;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by prateek on 13/02/18.
 */
public class FirebaseDatabaseHelper {
    private static FirebaseDatabaseHelper firebaseHelperInstance = null;
    private static String UUID = FirebaseAuth.getInstance().getUid();
    private static DatabaseReference firebaseDbReference = FirebaseDatabase.getInstance().getReference();;
    private static DatabaseReference user = firebaseDbReference.child("users").child(UUID);

    public static FirebaseDatabaseHelper getInstance() {
        if ( firebaseHelperInstance == null)
            firebaseHelperInstance = new FirebaseDatabaseHelper();
        return firebaseHelperInstance;
    }

    public boolean setValue(Map<String, Object> userData) {
        try{
            user.updateChildren(userData);
            Log.e("set value ", "ho gyi");
            return  true;
        }catch (Exception exception) {
            return false;
        }
    }
}