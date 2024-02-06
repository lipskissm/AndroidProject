package com.example.androidproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainDashboard extends AppCompatActivity {


    private CalorieCalculator calculator = new CalorieCalculator();
    private ProgressBar progressBar;
    private TextView progressText;

    QuestionareDatabase questionareDatabase;
    GoalsDatabase goalDatabase;

    ArrayList<String> gender, age, weight, height, goalWeight, goal;
    ArrayList<Integer> activity;

    int i = 0;
    int consumedCalories;
    int dailyCalToConsume;
    float consumedCalInPercentage;
    int calBasedOnGoals;
    float formulaCoefficient;

    Button enterMyPlan, enterWeighIn, enterUpdate;
    TextView showCalToConsume, showMealCal;

    int A, H;
    float W;

    int breakfastCal, lunchCal, dinnerCal, snackCal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard);

        SharedPreferences openQuestionnairePref = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        String SecondTime = openQuestionnairePref.getString("FirstTimeInstall", "");

        if(!(SecondTime.equals("Yes"))){
            SharedPreferences.Editor editor = openQuestionnairePref.edit();
            editor.putString("FirstTimeInstall", "Yes");
            editor.apply();

            Intent intent = new Intent(MainDashboard.this, Questionnaire.class);
            startActivity(intent);
        }

        questionareDatabase = new QuestionareDatabase(MainDashboard.this);
        gender = new ArrayList<>();
        age = new ArrayList<>();
        weight = new ArrayList<>();
        height = new ArrayList<>();
        goalWeight = new ArrayList<>();
        activity = new ArrayList<>();

        storeDataQuestionnaire();

        goalDatabase = new GoalsDatabase(MainDashboard.this);
        goal = new ArrayList<>();

        storeDataGoal();

        W = Float.parseFloat(weight.get(weight.size() - 1));
        H = Integer.parseInt(height.get(height.size() - 1));
        A = Integer.parseInt(age.get(age.size() - 1));

        formulaCoefficient = setFormulaCoefficient(activity);
        calBasedOnGoals = setCalBasedOnGoals(goal);

        if("Male".equals(gender.get(gender.size() - 1))) {
            dailyCalToConsume = calculator.calculateDailyCaloriesForMen(W, H, A, formulaCoefficient, calBasedOnGoals);
        } else {
            dailyCalToConsume = calculator.calculateDailyCaloriesForWomen(W, H, A, formulaCoefficient, calBasedOnGoals);
        }

        progressBar = findViewById(R.id.progress_bar);
        progressText = findViewById(R.id.progress_text);

        Intent intent = getIntent();

        breakfastCal = intent.getIntExtra("message_key_breakfast", 0);
        lunchCal = intent.getIntExtra("message_key_lunch", 0);
        dinnerCal = intent.getIntExtra("message_key_dinner", 0);
        snackCal = intent.getIntExtra("message_key_snack", 0);

        consumedCalories = breakfastCal + lunchCal + dinnerCal + snackCal;

        consumedCalInPercentage = (float)consumedCalories / (float)dailyCalToConsume * 100;

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (i <= consumedCalInPercentage) {
                    progressText.setText("" + consumedCalories);
                    progressBar.setProgress(i);
                    i++;
                    handler.postDelayed(this, 0);
                } else {
                    handler.removeCallbacks(this);
                }
            }
        }, 0);

        showCalToConsume = findViewById(R.id.textView10);
        showCalToConsume.setText(""+ dailyCalToConsume);

        showMealCal = findViewById(R.id.textView16);
        showMealCal.setText("" + intent.getIntExtra("message_key_breakfast", 0));

        showMealCal = findViewById(R.id.textView17);
        showMealCal.setText("" + intent.getIntExtra("message_key_lunch", 0));

        showMealCal = findViewById(R.id.textView18);
        showMealCal.setText("" + intent.getIntExtra("message_key_dinner", 0));

        showMealCal = findViewById(R.id.textView13);
        showMealCal.setText("" + intent.getIntExtra("message_key_snack", 0));

        enterMyPlan = (Button) findViewById(R.id.buttonMyPlan);
        enterMyPlan.setOnClickListener(v -> {
            Intent intent1 = new Intent(MainDashboard.this, MealPlan.class);
            intent1.putExtra("dailyCalToConsume", dailyCalToConsume);
            startActivity(intent1);
        });

        enterUpdate = (Button) findViewById(R.id.buttonUpdate);
        enterUpdate.setOnClickListener(v -> {
            Intent intent1 = new Intent(MainDashboard.this, Questionnaire.class);
            startActivity(intent1);
        });
    }

    public void storeDataQuestionnaire(){
        Cursor cursor = questionareDatabase.readAllData();
        while(cursor.moveToNext()){
            gender.add(cursor.getString(1));
            age.add(cursor.getString(2));
            weight.add(cursor.getString(3));
            height.add(cursor.getString(4));
            goalWeight.add(cursor.getString(5));
            activity.add(cursor.getInt(6));
        }
    }

    public void storeDataGoal(){
        Cursor cursor = goalDatabase.readAllData();
        while(cursor.moveToNext()){
            goal.add(cursor.getString(1));
        }
    }

    public float setFormulaCoefficient(ArrayList<Integer> activity) {
        switch (activity.get(activity.size() - 1)) {
            case 0:
                formulaCoefficient = 1.2f;
                break;
            case 1:
                formulaCoefficient = 1.375f;
                break;
            case 2:
                formulaCoefficient = 1.55f;
                break;
            case 3:
                formulaCoefficient = 1.725f;
                break;
            case 4:
                formulaCoefficient = 1.9f;
                break;
        }
        return formulaCoefficient;
    }

    public int setCalBasedOnGoals(ArrayList<String> goal){
        switch (goal.get(goal.size() - 1)) {
            case "Gain Muscles":
                calBasedOnGoals = 500;
                break;
            case "Lose Weight":
                calBasedOnGoals = -500;
                break;
            case "Sustain Health":
                calBasedOnGoals = 0;
                break;
        }
        return calBasedOnGoals;
    }

}