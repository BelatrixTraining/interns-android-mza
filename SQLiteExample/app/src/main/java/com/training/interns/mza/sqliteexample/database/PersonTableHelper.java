package com.training.interns.mza.sqliteexample.database;

import android.database.sqlite.SQLiteDatabase;

public class PersonTableHelper {
    // Table Name
    public static final String TABLE_NAME = "person";

    // Columns
    public static final String  COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_LASTNAME = "lastname";
    public static final String COLUMN_PHONE_NUMBER = "phone_number";

    // Create Table
    private static final String CREATE_PERSON_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY, "
            + COLUMN_NAME + " TEXT, "
            + COLUMN_LASTNAME + " TEXT, "
            + COLUMN_PHONE_NUMBER + " TEXT)";

    // Delete Table
    private static final String DELETE_PERSON_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static void onCreate (SQLiteDatabase db) {
        db.execSQL(CREATE_PERSON_TABLE);
    }

    public static void onUpgrade (SQLiteDatabase db) {
        db.execSQL(DELETE_PERSON_TABLE);
        onCreate(db);
    }
}
