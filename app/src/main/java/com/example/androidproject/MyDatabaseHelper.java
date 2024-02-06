package com.example.androidproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Login.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Loginai";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_USER = "Username";
    private static final String COLUMN_PASSWD = "Password";


    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USER + " TEXT, " +
                COLUMN_PASSWD + " TEXT);";
        db.execSQL(query);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void login(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USER, username);
        cv.put(COLUMN_PASSWD, password);
        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }

}
