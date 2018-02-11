package com.freestand.ranu.fsmark2.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.freestand.ranu.fsmark2.data.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prateek on 03/02/18.
 */

public class UserHandler extends DatabaseHandler {
    private static final String TABLE_USER = "user";
    private static final String KEY_FCM_TOKEN = "fcm_token";
    private static final String KEY_NAME = "name";
    private static final String KEY_FB_ID = "fb_id";
    private static final String KEY_DOB = "dob";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_PHOTO_URL = "photo_url";
    private static final String KEY_PH_NO = "phone_number";
    public UserHandler(Context context) {
        super(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_FCM_TOKEN + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_FB_ID + " TEXT," + KEY_DOB + " TEXT,"
                + KEY_EMAIL + " TEXT," + KEY_GENDER + " TEXT,"
                + KEY_PHOTO_URL + " TEXT," + KEY_PH_NO + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        // Create tables again
        onCreate(db);
    }


    void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FCM_TOKEN, user.getFcmToken());
        values.put(KEY_NAME, user.getName()); // User Name
        values.put(KEY_FB_ID, user.getFbId());
        values.put(KEY_DOB, user.getDob());
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_GENDER, user.getGender());
        values.put(KEY_PHOTO_URL, user.getPhotoUrl());
        values.put(KEY_PH_NO, user.getPhoneNumber()); // User Phone


        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection
    }

    User getUser(String fcmToken) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USER, new String[] {KEY_FCM_TOKEN, KEY_NAME, KEY_FB_ID,
                        KEY_DOB, KEY_EMAIL, KEY_GENDER, KEY_PHOTO_URL, KEY_PH_NO }, KEY_FCM_TOKEN + "=?",
                new String[] { fcmToken }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        User user = new User();
        user.setFcmToken(cursor.getString(0));
        user.setName(cursor.getString(1));
        user.setFbId(cursor.getString(2));
        user.setDob(cursor.getString(3));
        user.setEmail(cursor.getString(4));
        user.setGender(cursor.getString(5));
        user.setPhotoUrl(cursor.getString(6));
        user.setPhoneNumber(cursor.getString(7));
        return user;
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<User>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setFcmToken(cursor.getString(0));
                user.setName(cursor.getString(1));
                user.setFbId(cursor.getString(2));
                user.setDob(cursor.getString(3));
                user.setEmail(cursor.getString(4));
                user.setGender(cursor.getString(5));
                user.setPhotoUrl(cursor.getString(6));
                user.setPhoneNumber(cursor.getString(7));
                // Adding user to list
                userList.add(user);
            } while (cursor.moveToNext());
        }

        // return contact list
        return userList;
    }
    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getName());
        values.put(KEY_PH_NO, user.getPhoneNumber());

        // updating row
        return db.update(TABLE_USER, values, KEY_FCM_TOKEN + " = ?",
                new String[] { user.getFcmToken() });
    }

    // Deleting single user
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, KEY_FCM_TOKEN + " = ?",
                new String[] { user.getFcmToken() });
        db.close();
    }

    public int getUsersCount() {
        String countQuery = "SELECT  * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();
    }


}
