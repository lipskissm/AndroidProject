package com.example.androidproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Meal.db";
    private static final int DATABASE_VERSION = 1;
    static final String TABLE_NAME = "Meals";
    private static final String COLUMN_ID = "_id";
    static final String COLUMN_Meal = "Meal";
    static final String COLUMN_Time = "Time";
    static final String COLUMN_Calories = "Calories";
    static final String COLUMN_ingredients = "Ingredients";



    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_Meal + " TEXT, " +
                COLUMN_Time + " TEXT, " +
                COLUMN_Calories + " INTEGER, " +
                COLUMN_ingredients + " TEXT);";
        db.execSQL(query);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void insertMeals (String Meal, String Time, int Calories, String ingredients ){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_Meal, Meal);
        contentValues.put(COLUMN_Time, Time);
        contentValues.put(COLUMN_Calories, Calories);
        contentValues.put(COLUMN_ingredients, Calories);

        long result = database.insert(TABLE_NAME, null, contentValues);
        if(result == -1){
            Log.e("error", "failed insert");
        }

    }


    public boolean mealsExist() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM meals", null);
        boolean mealsExist = cursor.getCount() > 0;
        cursor.close();
        return mealsExist;
    }

}
