package com.example.androidproject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import android.content.Context;
import android.database.Cursor;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class GoalsDatabaseTest {
    private GoalsDatabase db;
    private Context context;

    @Before
    public void setUp() {
        context = ApplicationProvider.getApplicationContext();
        db = new GoalsDatabase(context, true);
    }

    @Test
    public void testReadData() {
        db.goals("Gain Muscles");

        Cursor cursor = db.readAllData();
        assertTrue(cursor.moveToFirst());
        assertEquals("Gain Muscles", cursor.getString(cursor.getColumnIndex(GoalsDatabase.getColumnGoal())));

        cursor.close();
    }

/*    @Test
    public void fakeTestShouldFail() {
        db.goals("Lose Weight");

        Cursor cursor = db.readAllData();
        assertTrue(cursor.moveToFirst());
        // Intentionally incorrect assertion
        assertEquals("Gain Muscles", cursor.getString(cursor.getColumnIndex(GoalsDatabase.getColumnGoal())));

        cursor.close();
    }*/

    @After
    public void tearDown() {
        db.close();
    }
}
