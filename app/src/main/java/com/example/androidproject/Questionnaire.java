package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;


public class Questionnaire extends AppCompatActivity{

    Button enterGoals;
    private TextView selectedOptionTextView;
    EditText age, weight, height, goalWeight;
    RadioButton gender1, gender2;
    private int selectedPosition = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);

        gender1 = findViewById(R.id.radia_id1);
        gender2 = findViewById(R.id.radia_id2);
        age = findViewById(R.id.editTextAge);
        weight = findViewById(R.id.editTextWeight);
        height = findViewById(R.id.editTextHeight);
        goalWeight = findViewById(R.id.editGoalWeight);
        //activity = findViewById(R.id.spinner);

        Spinner dropdown = findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);

        // Add items to the ArrayAdapter
        adapter.add("Sedentary");
        adapter.add("Light exercise 1-2 days/week");
        adapter.add("Moderate exercise 3-5 days/week");
        adapter.add("Heavy exercise 6-7 days/week");
        adapter.add("Professional athlete");

        dropdown.setAdapter(adapter);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Save the selected item's index to the variable
                selectedPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do something when nothing is selected
            }
        });

        enterGoals = (Button) findViewById(R.id.buttonContinue);
        enterGoals.setOnClickListener(v -> {

            String gender;
            if(gender1.isChecked()){
                gender = gender1.getText().toString().trim();
            }
            else{
                gender = gender2.getText().toString().trim();
            }

            QuestionareDatabase QDB = new QuestionareDatabase(Questionnaire.this);
            QDB.questions(
                    gender,
                    age.getText().toString().trim(),
                    weight.getText().toString().trim(),
                    height.getText().toString().trim(),
                    goalWeight.getText().toString().trim(),
                    selectedPosition
            );

            Intent intent = new Intent(Questionnaire.this, Goals.class);
            startActivity(intent);
        });
    }

}