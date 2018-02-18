package com.freestand.ranu.fsmark2;

import android.util.Log;

import com.freestand.ranu.fsmark2.data.UserHandler;
import com.freestand.ranu.fsmark2.data.model.User;
import com.freestand.ranu.fsmark2.data.sharedpf.SharedPrefsHelper;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import static com.freestand.ranu.fsmark2.data.sharedpf.SharedPrefsHelper.FSSharedPreferences;

/**
 * Created by prateek on 04/02/18.
 */

public class FirebaseIDService extends FirebaseInstanceIdService {
    private static final String TAG = "FirebaseIDService";

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        UserHandler userHandler = new UserHandler(AppController.getInstance());
        SharedPrefsHelper.put("FIREBASE_TOKEN", refreshedToken);
//        userHandler.updateUser();
        // TODO: Implement this method to send any registration to your app's servers.
        sendRegistrationToServer(refreshedToken);

    }

    /**
     * Persist token to third-party servers.
     *
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
        // Add custom implementation, as needed.
    }
}
