package com.example.androidproject;

public class CalorieCalculator {

    public int calculateDailyCaloriesForMen(float weight, int height, int age, float activityMultiplier, int goalCalories) {
        float menDailySustainCal = (65.4f + (13.7f * weight) + (5.0f * height) - (6.8f * age)) * activityMultiplier;
        return (int) menDailySustainCal + goalCalories;
    }

    public int calculateDailyCaloriesForWomen(float weight, int height, int age, float activityMultiplier, int goalCalories) {
        float womenDailySustainCal = (655f + (9.6f * weight) + (1.8f * height) - (4.7f * age)) * activityMultiplier;
        return (int) womenDailySustainCal + goalCalories;
    }
}
