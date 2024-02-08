package com.example.androidproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class MealPlan extends AppCompatActivity {

    ImageView imageView;
    TextView showMealName, showMealIngredients, showMealCalories;
    CheckBox checkBox1, checkBox2, checkBox3, checkBox4;
    private static final String CHECKBOX_STATE = "checkbox_state";

    ArrayList<Meal> mealsList, breakfastList, lunchList, dinnerList, snackList;

    int breakfastId, lunchId, dinnerId, snackId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_plan);
        MyDatabaseHelper mydb = new MyDatabaseHelper(MealPlan.this);
        mydb.insertMeals("Steak and Potatoes", "Lunch", 980, "Grilled Steak (300g), Roasted Potatoes (300g), Sautéed Asparagus (100g), Garlic Butter (50g)");
        mydb.insertMeals("Pasta Alfredo with Chicken", "Dinner", 920, "Fettuccine Pasta (300g), Grilled Chicken Breast (200g), Alfredo Sauce (200g), Parmesan Cheese (50g), Broccoli (100g)");
        mydb.insertMeals("High-Protein Power Bowl", "Lunch", 950, "Quinoa (250g), Grilled Salmon Fillet (300g), Steamed Kale (150g), Chickpeas (150g), Lemon Tahini Dressing (100g)");
        mydb.insertMeals("Triple Decker Club Sandwich", "Lunch", 980, "Turkey Breast (200g), Ham (150g), Bacon (150g), Swiss Cheese (100g), Lettuce (50g), Tomato (50g), Mayonnaise (50g), Whole Wheat Bread (230g)");
        mydb.insertMeals("Sushi Platter", "Dinner", 990, "Assorted Sushi Rolls (400g), Edamame (200g), Soy Sauce (50g), Pickled Ginger (30g), Wasabi (10g)");
        mydb.insertMeals("Avocado Toast", "Breakfast", 500, "Whole Grain Bread (120g), Avocado (150g), Cherry Tomatoes (60g), Feta Cheese (50g)");
        mydb.insertMeals("Grilled Salmon Salad", "Lunch", 600, "Grilled Salmon Fillet (200g), Mixed Greens (150g), Cucumber (80g), Red Onion (50g), Balsamic Vinaigrette (30g)");
        mydb.insertMeals("Vegetable Stir-Fry", "Dinner", 550, "Broccoli (150g), Carrots (120g), Bell Peppers (100g), Tofu (150g), Soy Sauce (30g)");
        mydb.insertMeals("Peanut Butter Banana Smoothie", "Breakfast", 400, "Banana (200g), Peanut Butter (50g), Greek Yogurt (150g), Almond Milk (200g)");
        mydb.insertMeals("Caprese Salad", "Lunch", 450, "Tomatoes (150g), Fresh Mozzarella (80g), Basil Leaves (15g), Balsamic Glaze (30g)");
        mydb.insertMeals("Chicken Quinoa Bowl", "Dinner", 580, "Grilled Chicken Breast (180g), Quinoa (150g), Steamed Broccoli (120g), Avocado (80g), Sriracha Sauce (15g)");
        mydb.insertMeals("Egg White Omelette", "Breakfast", 350, "Egg Whites (250g), Spinach (80g), Mushrooms (60g), Feta Cheese (50g)");
        mydb.insertMeals("Tuna Salad Sandwich", "Lunch", 500, "Canned Tuna (120g), Whole Wheat Bread (100g), Lettuce (60g), Tomato (50g), Mayonnaise (30g)");
        mydb.insertMeals("Vegetarian Chili", "Dinner", 450, "Kidney Beans (150g), Black Beans (120g), Diced Tomatoes (150g), Bell Peppers (90g), Onion (60g), Chili Powder (15g)");
        mydb.insertMeals("Blueberry Pancakes", "Breakfast", 600, "Pancake Mix (200g), Blueberries (150g), Maple Syrup (50g), Butter (30g)");
        mydb.insertMeals("Turkey and Veggie Wrap", "Lunch", 500, "Sliced Turkey Breast (150g), Hummus (60g), Red Bell Pepper (60g), Spinach Leaves (50g), Whole Wheat Wrap (100g)");
        mydb.insertMeals("Mushroom Risotto", "Dinner", 650, "Rice (150g), Mushrooms (120g), Vegetable Broth (250g), Parmesan Cheese (40g), White Wine (30g)");
        mydb.insertMeals("Fruit Salad", "Breakfast", 350, "Strawberries (100g), Pineapple (90g), Kiwi (70g), Grapes (70g), Honey (30g)");
        mydb.insertMeals("Shrimp Tacos", "Lunch", 500, "Shrimp (150g), Corn Tortillas (120g), Cabbage Slaw (100g), Avocado (80g), Lime (30g)");
        mydb.insertMeals("Eggplant Parmesan", "Dinner", 600, "Eggplant (200g), Marinara Sauce (120g), Mozzarella Cheese (60g), Bread Crumbs (40g), Olive Oil (30g)");
        mydb.insertMeals("Breakfast Burrito", "Breakfast", 550, "Scrambled Eggs (200g), Black Beans (120g), Salsa (70g), Cheddar Cheese (60g), Whole Wheat Tortilla (120g)");
        mydb.insertMeals("Asian Noodle Salad", "Lunch", 480, "Rice Noodles (150g), Shredded Carrots (80g), Cucumber (70g), Edamame (60g), Soy Ginger Dressing (30g)");
        mydb.insertMeals("Beef and Broccoli Stir-Fry", "Dinner", 700, "Beef Sirloin (200g), Broccoli Florets (150g), Soy Sauce (40g), Garlic (30g), Brown Rice (200g)");
        mydb.insertMeals("Cottage Cheese and Fruit Bowl", "Breakfast", 450, "Cottage Cheese (200g), Sliced Peaches (150g), Almonds (50g), Honey (30g)");
        mydb.insertMeals("Mediterranean Wrap", "Lunch", 550, "Grilled Chicken (180g), Tzatziki Sauce (60g), Kalamata Olives (45g), Red Onion (45g), Whole Wheat Wrap (120g)");
        mydb.insertMeals("Nutty Energy Bars", "Snack", 400, "Rolled Oats (100g), Almonds (100g), Peanut Butter (80g), Honey (50g), Dark Chocolate Chips (50g)");
        mydb.insertMeals("Greek Yogurt Bowl", "Snack", 350, "Greek Yogurt (250g), Mixed Berries (100g), Granola (50g), Chia Seeds (20g), Honey (30g)");
        mydb.insertMeals("Trail Mix Deluxe", "Snack", 450, "Almonds (150g), Cashews (150g), Dried Cranberries (100g), Dark Chocolate (50g), Pumpkin Seeds (50g)");
        mydb.insertMeals("Cheese and Crackers Platter", "Snack", 500, "Assorted Cheeses (200g), Multigrain Crackers (150g), Grapes (100g), Apple Slices (50g), Honey (30g)");
        mydb.insertMeals("Hummus and Veggie Sticks", "Snack", 380, "Hummus (150g), Carrot Sticks (100g), Cucumber Slices (100g), Bell Pepper Strips (50g)");
        mydb.insertMeals("Protein-Packed Smoothie Bowl", "Snack", 420, "Protein Powder (40g), Frozen Mixed Berries (150g), Banana (100g), Almond Milk (200g), Granola (50g)");
        mydb.insertMeals("Avocado and Whole Grain Toast", "Snack", 370, "Avocado (150g), Whole Grain Toast (100g), Cherry Tomatoes (50g), Feta Cheese (30g)");
        mydb.insertMeals("Cottage Cheese and Fruit Plate", "Snack", 420, "Cottage Cheese (200g), Sliced Peaches (100g), Almonds (50g), Honey (20g), Kiwi Slices (50g)");
        mydb.insertMeals("Nut Butter and Banana Rice Cakes", "Snack", 380, "Rice Cakes (100g), Almond Butter (50g), Peanut Butter (50g), Sliced Banana (80g), Honey (20g)");
        mydb.insertMeals("Tuna Salad with Crackers", "Snack", 450, "Canned Tuna (100g), Mayonnaise (30g), Diced Celery (50g), Whole Grain Crackers (150g), Lettuce Leaves (50g)");












        mealsList = new ArrayList<>();

        // Adding Meal objects to the ArrayList
        mealsList.add(new Meal("Steak and Potatoes", "Lunch", 980, "Grilled Steak (300g), Roasted Potatoes (300g), Sautéed Asparagus (100g), Garlic Butter (50g)"));
        mealsList.add(new Meal("Pasta Alfredo with Chicken", "Dinner", 920, "Fettuccine Pasta (300g), Grilled Chicken Breast (200g), Alfredo Sauce (200g), Parmesan Cheese (50g), Broccoli (100g)"));
        mealsList.add(new Meal("High-Protein Power Bowl", "Lunch", 950, "Quinoa (250g), Grilled Salmon Fillet (300g), Steamed Kale (150g), Chickpeas (150g), Lemon Tahini Dressing (100g)"));
        mealsList.add(new Meal("Triple Decker Club Sandwich", "Lunch", 980, "Turkey Breast (200g), Ham (150g), Bacon (150g), Swiss Cheese (100g), Lettuce (50g), Tomato (50g), Mayonnaise (50g), Whole Wheat Bread (230g)"));
        mealsList.add(new Meal("Sushi Platter", "Dinner", 990, "Assorted Sushi Rolls (400g), Edamame (200g), Soy Sauce (50g), Pickled Ginger (30g), Wasabi (10g)"));
        mealsList.add(new Meal("Avocado Toast", "Breakfast", 500, "Whole Grain Bread (120g), Avocado (150g), Cherry Tomatoes (60g), Feta Cheese (50g)"));
        mealsList.add(new Meal("Grilled Salmon Salad", "Lunch", 600, "Grilled Salmon Fillet (200g), Mixed Greens (150g), Cucumber (80g), Red Onion (50g), Balsamic Vinaigrette (30g)"));
        mealsList.add(new Meal("Vegetable Stir-Fry", "Dinner", 550, "Broccoli (150g), Carrots (120g), Bell Peppers (100g), Tofu (150g), Soy Sauce (30g)"));
        mealsList.add(new Meal("Peanut Butter Banana Smoothie", "Breakfast", 400, "Banana (200g), Peanut Butter (50g), Greek Yogurt (150g), Almond Milk (200g)"));
        mealsList.add(new Meal("Caprese Salad", "Lunch", 450, "Tomatoes (150g), Fresh Mozzarella (80g), Basil Leaves (15g), Balsamic Glaze (30g)"));
        mealsList.add(new Meal("Chicken Quinoa Bowl", "Dinner", 580, "Grilled Chicken Breast (180g), Quinoa (150g), Steamed Broccoli (120g), Avocado (80g), Sriracha Sauce (15g)"));
        mealsList.add(new Meal("Egg White Omelette", "Breakfast", 350, "Egg Whites (250g), Spinach (80g), Mushrooms (60g), Feta Cheese (50g)"));
        mealsList.add(new Meal("Tuna Salad Sandwich", "Lunch", 500, "Canned Tuna (120g), Whole Wheat Bread (100g), Lettuce (60g), Tomato (50g), Mayonnaise (30g)"));
        mealsList.add(new Meal("Vegetarian Chili", "Dinner", 450, "Kidney Beans (150g), Black Beans (120g), Diced Tomatoes (150g), Bell Peppers (90g), Onion (60g), Chili Powder (15g)"));
        mealsList.add(new Meal("Blueberry Pancakes", "Breakfast", 600, "Pancake Mix (200g), Blueberries (150g), Maple Syrup (50g), Butter (30g)"));
        mealsList.add(new Meal("Turkey and Veggie Wrap", "Lunch", 500, "Sliced Turkey Breast (150g), Hummus (60g), Red Bell Pepper (60g), Spinach Leaves (50g), Whole Wheat Wrap (100g)"));
        mealsList.add(new Meal("Mushroom Risotto", "Dinner", 650, "Rice (150g), Mushrooms (120g), Vegetable Broth (250g), Parmesan Cheese (40g), White Wine (30g)"));
        mealsList.add(new Meal("Fruit Salad", "Breakfast", 350, "Strawberries (100g), Pineapple (90g), Kiwi (70g), Grapes (70g), Honey (30g)"));
        mealsList.add(new Meal("Shrimp Tacos", "Lunch", 500, "Shrimp (150g), Corn Tortillas (120g), Cabbage Slaw (100g), Avocado (80g), Lime (30g)"));
        mealsList.add(new Meal("Eggplant Parmesan", "Dinner", 600, "Eggplant (200g), Marinara Sauce (120g), Mozzarella Cheese (60g), Bread Crumbs (40g), Olive Oil (30g)"));
        mealsList.add(new Meal("Breakfast Burrito", "Breakfast", 550, "Scrambled Eggs (200g), Black Beans (120g), Salsa (70g), Cheddar Cheese (60g), Whole Wheat Tortilla (120g)"));
        mealsList.add(new Meal("Asian Noodle Salad", "Lunch", 480, "Rice Noodles (150g), Shredded Carrots (80g), Cucumber (70g), Edamame (60g), Soy Ginger Dressing (30g)"));
        mealsList.add(new Meal("Beef and Broccoli Stir-Fry", "Dinner", 700, "Beef Sirloin (200g), Broccoli Florets (150g), Soy Sauce (40g), Garlic (30g), Brown Rice (200g)"));
        mealsList.add(new Meal("Cottage Cheese and Fruit Bowl", "Breakfast", 450, "Cottage Cheese (200g), Sliced Peaches (150g), Almonds (50g), Honey (30g)"));
        mealsList.add(new Meal("Mediterranean Wrap", "Lunch", 550, "Grilled Chicken (180g), Tzatziki Sauce (60g), Kalamata Olives (45g), Red Onion (45g), Whole Wheat Wrap (120g)"));
        mealsList.add(new Meal("Nutty Energy Bars", "Snack", 400, "Rolled Oats (100g), Almonds (100g), Peanut Butter (80g), Honey (50g), Dark Chocolate Chips (50g)"));
        mealsList.add(new Meal("Greek Yogurt Bowl", "Snack", 350, "Greek Yogurt (250g), Mixed Berries (100g), Granola (50g), Chia Seeds (20g), Honey (30g)"));
        mealsList.add(new Meal("Trail Mix Deluxe", "Snack", 450, "Almonds (150g), Cashews (150g), Dried Cranberries (100g), Dark Chocolate (50g), Pumpkin Seeds (50g)"));
        mealsList.add(new Meal("Cheese and Crackers Platter", "Snack", 500, "Assorted Cheeses (200g), Multigrain Crackers (150g), Grapes (100g), Apple Slices (50g), Honey (30g)"));
        mealsList.add(new Meal("Hummus and Veggie Sticks", "Snack", 380, "Hummus (150g), Carrot Sticks (100g), Cucumber Slices (100g), Bell Pepper Strips (50g)"));
        mealsList.add(new Meal("Protein-Packed Smoothie Bowl", "Snack", 420, "Protein Powder (40g), Frozen Mixed Berries (150g), Banana (100g), Almond Milk (200g), Granola (50g)"));
        mealsList.add(new Meal("Avocado and Whole Grain Toast", "Snack", 370, "Avocado (150g), Whole Grain Toast (100g), Cherry Tomatoes (50g), Feta Cheese (30g)"));
        mealsList.add(new Meal("Cottage Cheese and Fruit Plate", "Snack", 420, "Cottage Cheese (200g), Sliced Peaches (100g), Almonds (50g), Honey (20g), Kiwi Slices (50g)"));
        mealsList.add(new Meal("Nut Butter and Banana Rice Cakes", "Snack", 380, "Rice Cakes (100g), Almond Butter (50g), Peanut Butter (50g), Sliced Banana (80g), Honey (20g)"));
        mealsList.add(new Meal("Tuna Salad with Crackers", "Snack", 450, "Canned Tuna (100g), Mayonnaise (30g), Diced Celery (50g), Whole Grain Crackers (150g), Lettuce Leaves (50g)"));

        breakfastList = new ArrayList<>();
        lunchList = new ArrayList<>();
        dinnerList = new ArrayList<>();
        snackList = new ArrayList<>();

        for (int i = 0; i < mealsList.size(); i++) {
            String time = mealsList.get(i).getTime();
            if (time.equals("Breakfast")) {
                breakfastList.add(mealsList.get(i));
            }
        }

        for (int i = 0; i < mealsList.size(); i++) {
            String time = mealsList.get(i).getTime();
            if (time.equals("Lunch")) {
                lunchList.add(mealsList.get(i));
            }
        }

        for (int i = 0; i < mealsList.size(); i++) {
            String time = mealsList.get(i).getTime();
            if (time.equals("Dinner")) {
                dinnerList.add(mealsList.get(i));
            }
        }

        for (int i = 0; i < mealsList.size(); i++) {
            String time = mealsList.get(i).getTime();
            if (time.equals("Snack")) {
                snackList.add(mealsList.get(i));
            }
        }

        Random random = new Random();
        breakfastId = random.nextInt(breakfastList.size());
        int predeterminedA = breakfastList.get(breakfastId).getCalories(); // Change this to your desired value

        int minDiff = Integer.MAX_VALUE;

        Intent intent = getIntent();
        int targetSum = intent.getIntExtra("dailyCalToConsume", 0);

        for (int i = 0; i < lunchList.size(); i++) {
            for (int j = 0; j < dinnerList.size(); j++) {
                for (int k = 0; k < snackList.size(); k++) {
                    int b = lunchList.get(i).getCalories();
                    int c = dinnerList.get(j).getCalories();
                    int d = snackList.get(k).getCalories();
                    int sum = predeterminedA + b + c + d;
                    int diff = Math.abs(targetSum - sum);

                    if (diff < minDiff) {
                        minDiff = diff;
                        lunchId = i;
                        dinnerId = j;
                        snackId = k;
                    }
                }
            }
        }

        showMealName = findViewById(R.id.textView2);
        showMealName.setText(""+ breakfastList.get(breakfastId).getMeal());

        showMealIngredients = findViewById(R.id.textView5);
        showMealIngredients.setText(""+ breakfastList.get(breakfastId).getIngredients());

        showMealCalories = findViewById(R.id.textView4);
        showMealCalories.setText(""+ breakfastList.get(breakfastId).getCalories() + "cal");
        //------------------------
        showMealName = findViewById(R.id.textView7);
        showMealName.setText(""+ lunchList.get(lunchId).getMeal());

        showMealIngredients = findViewById(R.id.textView10);
        showMealIngredients.setText(""+ lunchList.get(lunchId).getIngredients());

        showMealCalories = findViewById(R.id.textView9);
        showMealCalories.setText(""+ lunchList.get(lunchId).getCalories() + "cal");
        //------------------------
        showMealName = findViewById(R.id.textView12);
        showMealName.setText(""+ dinnerList.get(dinnerId).getMeal());

        showMealIngredients = findViewById(R.id.textView15);
        showMealIngredients.setText(""+ dinnerList.get(dinnerId).getIngredients());

        showMealCalories = findViewById(R.id.textView14);
        showMealCalories.setText(""+ dinnerList.get(dinnerId).getCalories() + "cal");
        //------------------------
        showMealName = findViewById(R.id.textView17);
        showMealName.setText(""+ snackList.get(snackId).getMeal());

        showMealIngredients = findViewById(R.id.textView30);
        showMealIngredients.setText(""+ snackList.get(snackId).getIngredients());

        showMealCalories = findViewById(R.id.textView18);
        showMealCalories.setText(""+ snackList.get(snackId).getCalories() + "cal");


        checkBox1 = findViewById(R.id.checkBox);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        checkBox4 = findViewById(R.id.checkBox4);

        restoreCheckBoxState(checkBox1, "checkBox1State");
        restoreCheckBoxState(checkBox2, "checkBox2State");
        restoreCheckBoxState(checkBox3, "checkBox3State");
        restoreCheckBoxState(checkBox4, "checkBox4State");

        checkBox1.setOnCheckedChangeListener((buttonView, isChecked) -> saveCheckBoxState("checkBox1State", isChecked));
        checkBox2.setOnCheckedChangeListener((buttonView, isChecked) -> saveCheckBoxState("checkBox2State", isChecked));
        checkBox3.setOnCheckedChangeListener((buttonView, isChecked) -> saveCheckBoxState("checkBox3State", isChecked));
        checkBox4.setOnCheckedChangeListener((buttonView, isChecked) -> saveCheckBoxState("checkBox4State", isChecked));

        imageView = findViewById(R.id.closeIcon);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MealPlan.this, MainDashboard.class);

                if (checkBox1.isChecked()) { intent1.putExtra("message_key_breakfast", breakfastList.get(breakfastId).getCalories()); }
                if (checkBox2.isChecked()) { intent1.putExtra("message_key_lunch", lunchList.get(lunchId).getCalories()); }
                if (checkBox3.isChecked()) { intent1.putExtra("message_key_dinner", dinnerList.get(dinnerId).getCalories()); }
                if (checkBox4.isChecked()) { intent1.putExtra("message_key_snack", snackList.get(snackId).getCalories()); }

                startActivity(intent1);
            }
        });
    }
    private void restoreCheckBoxState(CheckBox checkBox, String checkBoxKey) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        boolean isChecked = sharedPreferences.getBoolean(checkBoxKey, false);
        checkBox.setChecked(isChecked);
    }

    private void saveCheckBoxState(String checkBoxKey, boolean isChecked) {
        SharedPreferences.Editor editor = getSharedPreferences("MyPrefs", MODE_PRIVATE).edit();
        editor.putBoolean(checkBoxKey, isChecked);
        editor.apply();
    }
}