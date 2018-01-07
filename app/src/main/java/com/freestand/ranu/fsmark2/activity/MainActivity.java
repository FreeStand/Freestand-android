package com.freestand.ranu.fsmark2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.freestand.ranu.fsmark2.R;

public class MainActivity extends BaseActivity {
    CallbackManager callbackManager;
    AccessToken accessToken;
    AccessTokenTracker accessTokenTracker;
    ProfileTracker profileTracker;
    Profile profile = Profile.getCurrentProfile();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //restore theme from set by splash screen
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setLoginCallback();
        setProfileTracker();

        setAccessTokenTracker();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        Log.e("rcode, rreslt, data", requestCode + " " + requestCode + " "+ data.toString());
        super.onActivityResult(requestCode, resultCode, data);
        Intent intent = new Intent(MainActivity.this, AfterLogin.class);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }

    private void setLoginCallback() {
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                    }
                    @Override
                    public void onCancel() {
                        Log.e("login unsucessful", "cancel");
                    }
                    @Override
                    public void onError(FacebookException exception) {
                        Log.e("login unsucessful", exception.getMessage());
                    }
                }
        );
    }

    private void setProfileTracker() {
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                profile = currentProfile;
            }
        };
    }

    private void setAccessTokenTracker() {
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {
                // Set the access token using
                // currentAccessToken when it's loaded or set.
                if(accessToken!=null) {
                    Log.e("current access token", accessToken.getUserId() + "");
                }
            }
        };
        // If the access token is available already assign it.
        accessToken = AccessToken.getCurrentAccessToken();
    }

}
