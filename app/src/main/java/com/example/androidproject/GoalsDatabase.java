package com.example.androidproject;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
/*import android.widget.Toast;*/
import android.database.Cursor;
import androidx.annotation.Nullable;
public class GoalsDatabase extends  SQLiteOpenHelper{

    public GoalsDatabase(@Nullable Context context, boolean useInMemory) {
        // If useInMemory is true, pass null for the database name to create an in-memory database
        super(context, useInMemory ? null : DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
    private Context context;
    private static final String DATABASE_NAME = "GOALSDATA.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Goalsai";
    private final String COLUMN_ID = "_id";
    private static final String COLUMN_GOAL = "Goal";
    public static String getColumnGoal() {return COLUMN_GOAL;}

    public GoalsDatabase(@Nullable Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database){
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_GOAL + " TEXT); ";
        database.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
    }

    void goals (String goals) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_GOAL, goals);
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

