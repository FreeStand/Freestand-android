package com.freestand.ranu.fsmark2.data.model;

/**
 * Created by prateek on 03/02/18.
 */

public class User {

    //private variables
    private String fcmToken;
    private String fbId;
    private String dob;
    private String email;
    private String Gender;
    private String photoUrl;
    private String name;
    private String phoneNumber;

    // Empty constructor
    public User(){

    }
    // constructor
    public User(String  fcmToken, String name, String phoneNumber){
        this.fcmToken = fcmToken;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }


    // constructor
    public User(String name, String phoneNumber){
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
    // getting ID
    public String getFcmToken(){
        return this.fcmToken;
    }

    // setting id
    public void setFcmToken(String fcmToken){
        this.fcmToken = fcmToken;
    }

    // getting name
    public String getName(){
        return this.name;
    }

    // setting name
    public void setName(String name){
        this.name = name;
    }

    // getting phone number
    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    // setting phone number
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
