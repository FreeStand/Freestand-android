package com.freestand.ranu.fsmark2.data;

/**
 * Created by prateek on 03/02/18.
 */
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "FREESTAND_DB";

    // Contacts table name

    // Contacts Table Columns names

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public abstract void onCreate(SQLiteDatabase db);

    // Upgrading database
    @Override
    public abstract void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact

    // Getting single contact

    // Getting All Contacts


    // Updating single contact



    // Getting contacts Count


}