package com.freestand.ranu.fsmark2.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.freestand.ranu.fsmark2.AppController;
import com.freestand.ranu.fsmark2.R;
import com.freestand.ranu.fsmark2.data.FirebaseDatabaseHelper;
import com.freestand.ranu.fsmark2.data.sharedpf.SharedPrefsHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class FacebookLoginActivity extends BaseActivity {
    @Inject SharedPrefsHelper sharedPrefsHelper;
    CallbackManager callbackManager;
    AccessToken accessToken;
    AccessTokenTracker accessTokenTracker;
    ProfileTracker profileTracker;
    Profile profile = Profile.getCurrentProfile();
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //restore theme from set by splash screen
        super.onCreate(savedInstanceState);
        AppController.getInstance().getAppComponent().inject(this);
        mAuth = FirebaseAuth.getInstance();
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
        super.onActivityResult(requestCode, resultCode, data);
//        Log.e("result activity ", accessToken.getToken());
        sharedPrefsHelper.put("IS_LOGGED_IN", true);
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
                        handleFacebookAccessToken(loginResult.getAccessToken());
                        Log.e("login result", loginResult.getAccessToken().getToken());
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

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d("hello", "handleFacebookAccessToken:" + token);
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e("hello ", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(FacebookLoginActivity.this, LandingScreen.class);
                            startActivity(intent);
                            finish();
                            completeSignIn();
                            Log.e("user id ", user.getUid());
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("hello ", "signInWithCredential:failure", task.getException());
                            Toast.makeText(FacebookLoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void completeSignIn() {
        Map<String, Object> userMainInfo = new HashMap<>();
        userMainInfo.put("email", ""+mAuth.getCurrentUser().getEmail());
        userMainInfo.put("name", mAuth.getCurrentUser().getDisplayName());
        userMainInfo.put("photoURL", makePhotoUrl(AccessToken.getCurrentAccessToken().getUserId()));
        userMainInfo.put("fcmToken", FirebaseInstanceId.getInstance().getToken());
        FirebaseDatabaseHelper.getInstance().setValue(userMainInfo);
        Log.e("user id " , AccessToken.getCurrentAccessToken().getUserId());
    }

    private String makePhotoUrl(@NonNull String fbId) {
        return  "http://graph.facebook.com/" + fbId + "/picture?type=large";
    }

}
