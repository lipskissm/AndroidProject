package com.example.androidproject;

public class Meal {
    private String meal;
    private String time;
    private int calories;
    private String ingredients;

    // Constructor
    public Meal(String meal, String time, int calories, String ingredients) {
        this.meal = meal;
        this.time = time;
        this.calories = calories;
        this.ingredients = ingredients;
    }

    // Getters and Setters
    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
}
