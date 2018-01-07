package com.freestand.ranu.fsmark2.facebook;

import com.facebook.AccessToken;
import com.facebook.Profile;

/**
 * Created by prateek on 7/1/18.
 */

public class FbData {
    static Profile profile;
    static AccessToken accessToken;
    static String userFirstName = profile.getFirstName();
    static String userLastNAme = profile.getLastName();
    static String userEmail = profile.
}
