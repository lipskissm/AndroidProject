package com.example.androidproject;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;
import android.database.Cursor;


import androidx.annotation.Nullable;

import org.w3c.dom.Text;

public class QuestionareDatabase extends SQLiteOpenHelper {

    public QuestionareDatabase(@Nullable Context context, boolean useInMemory) {
        // If useInMemory is true, pass null for the database name to create an in-memory database
        super(context, useInMemory ? null : DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
    private Context context;

    private static final String DATABASE_NAME = "QuestionareDB.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Questionai";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_GENDER = "Gender";
    public static String getColumnGender() {
        return COLUMN_GENDER;
    }
    private static final String COLUMN_AGE = "Age";
    public static String getColumnAge() {
        return COLUMN_AGE;
    }
    private static final String COLUMN_WEIGHT = "Weight";
    public static String getColumnWeight() {
        return COLUMN_WEIGHT;
    }
    private static final String COLUMN_HEIGHT = "Height";
    public static String getColumnHeight() {
        return COLUMN_HEIGHT;
    }
    private static final String COLUMN_GOALWEIGHT = "GOAL";
    public static String getColumnGoalWeight() {
        return COLUMN_GOALWEIGHT;
    }
    private static final String COLUMN_ACTIVITY_LEVEL = "ActivityLVL";
    public static String getActivity_Level() {
        return COLUMN_ACTIVITY_LEVEL;
    }

    public QuestionareDatabase(@Nullable Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database){
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_GENDER + " TEXT, " +
                COLUMN_AGE + " TEXT, " +
                COLUMN_WEIGHT + " TEXT, " +
                COLUMN_HEIGHT + " TEXT, " +
                COLUMN_GOALWEIGHT + " TEXT, " +
                COLUMN_ACTIVITY_LEVEL + " INTEGER);";
        database.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
    }

    void questions (String gender, String age, String  weight, String height, String goal, int activity){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_GENDER, gender);
        contentValues.put(COLUMN_AGE, age);
        contentValues.put(COLUMN_WEIGHT, weight);
        contentValues.put(COLUMN_HEIGHT, height);
        contentValues.put(COLUMN_GOALWEIGHT, goal);
        contentValues.put(COLUMN_ACTIVITY_LEVEL, activity);

        long result = database.insert(TABLE_NAME, null, contentValues);
        if (result == -1){
            Log.e("DatabaseError", "Insert failed");
        }
    }

    public Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}
