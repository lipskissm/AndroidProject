package com.example.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Goals extends AppCompatActivity {

    Button enterDashboard1, enterDashboard2, enterDashboard3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);

        GoalsDatabase GDB = new GoalsDatabase(Goals.this);

        enterDashboard1 = (Button) findViewById(R.id.gainMuscles);
        enterDashboard1.setOnClickListener(v -> {
            GDB.goals("Gain Muscles");
            Intent intent = new Intent(Goals.this, MainDashboard.class);
            startActivity(intent);
        });

        enterDashboard2 = (Button) findViewById(R.id.loseWeight);
        enterDashboard2.setOnClickListener(v -> {
            GDB.goals("Lose Weight");
            Intent intent = new Intent(Goals.this, MainDashboard.class);
            startActivity(intent);
        });

        enterDashboard3 = (Button) findViewById(R.id.sustainHealth);
        enterDashboard3.setOnClickListener(v -> {
            GDB.goals("Sustain Health");
            Intent intent = new Intent(Goals.this, MainDashboard.class);
            startActivity(intent);
        });
    }
}