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
public class QuestionareDatabaseTest {
    private QuestionareDatabase db;
    private Context context;

    @Before
    public void setUp() {
        context = ApplicationProvider.getApplicationContext();
        // Using an in-memory database for testing
        db = new QuestionareDatabase(context, true);
    }

    @Test
    public void testReadData() {
        // Inserting test data
        db.questions("Male", "30", "80", "180", "GoalWeight", 2);

        // Read the data back and verify
        Cursor cursor = db.readAllData();
        assertTrue(cursor.moveToFirst());
        assertEquals("Male", cursor.getString(cursor.getColumnIndex(QuestionareDatabase.getColumnGender())));
        assertEquals("30", cursor.getString(cursor.getColumnIndex(QuestionareDatabase.getColumnAge())));
        assertEquals("80", cursor.getString(cursor.getColumnIndex(QuestionareDatabase.getColumnWeight())));
        assertEquals("180", cursor.getString(cursor.getColumnIndex(QuestionareDatabase.getColumnHeight())));
        assertEquals("GoalWeight", cursor.getString(cursor.getColumnIndex(QuestionareDatabase.getColumnGoalWeight())));
        assertEquals(2, cursor.getInt(cursor.getColumnIndex(QuestionareDatabase.getActivity_Level())));


        cursor.close();
    }

/*    @Test
    public void fakeTestShouldFail() {

        db.questions("Female", "25", "65", "160", "GoalWeight", 1);


        Cursor cursor = db.readAllData();
        assertTrue(cursor.moveToFirst());
        // Intentionally incorrect assertions to force a test failure
        assertEquals("Male", cursor.getString(cursor.getColumnIndex(QuestionareDatabase.getColumnGender())));
        assertEquals("40", cursor.getString(cursor.getColumnIndex(QuestionareDatabase.getColumnAge())));
        assertEquals("80", cursor.getString(cursor.getColumnIndex(QuestionareDatabase.getColumnWeight())));
        assertEquals("180", cursor.getString(cursor.getColumnIndex(QuestionareDatabase.getColumnHeight())));
        assertEquals("GoalWeight", cursor.getString(cursor.getColumnIndex(QuestionareDatabase.getColumnGoalWeight())));
        assertEquals(2, cursor.getInt(cursor.getColumnIndex(QuestionareDatabase.getActivity_Level())));

        cursor.close();
    }*/

    @After
    public void tearDown() {
        db.close();
    }
}
