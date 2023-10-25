package com.example.indoorfit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.example.indoorfit.Nutrition.Fish;
import com.example.indoorfit.Nutrition.Fruit;
import com.example.indoorfit.Nutrition.Meat;
import com.example.indoorfit.Nutrition.Nut;
import com.example.indoorfit.Nutrition.Veggies;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NutritionActivity extends AppCompatActivity {

    private CardView fruitCardView;
    private CardView veggiesCardView;
    private CardView meatCardView;
    private CardView nutCardView;
    private CardView fishCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);


        // Initialize the CardViews
        fruitCardView = findViewById(R.id.fruitId);
        veggiesCardView = findViewById(R.id.veggiesId);
        meatCardView = findViewById(R.id.meatId);
        nutCardView = findViewById(R.id.nutId);
        fishCardView = findViewById(R.id.fishId);



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.menuNutrition);

        ImageView leftButton = findViewById(R.id.left_button);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(NutritionActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });



        // Handle clicks on CardViews
        fruitCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Fruit activity
                Intent fruitIntent = new Intent(NutritionActivity.this, Fruit.class);
                startActivity(fruitIntent);
            }
        });

        veggiesCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Veggies activity
                Intent veggiesIntent = new Intent(NutritionActivity.this, Veggies.class);
                startActivity(veggiesIntent);
            }
        });

        meatCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Meat activity
                Intent meatIntent = new Intent(NutritionActivity.this, Meat.class);
                startActivity(meatIntent);
            }
        });

        nutCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Nut activity
                Intent nutIntent = new Intent(NutritionActivity.this, Nut.class);
                startActivity(nutIntent);
            }
        });

        fishCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Fish activity
                Intent fishIntent = new Intent(NutritionActivity.this, Fish.class);
                startActivity(fishIntent);
            }
        });


        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menuHome:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                    return true;
                case R.id.menuStep:
                    startActivity(new Intent(getApplicationContext(), StepCounterActivity.class));
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                    return true;
                case R.id.menuWorkout:
                    startActivity(new Intent(getApplicationContext(), WorkoutActivity.class));
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                    return true;
                case R.id.menuNutrition:
                    return true;
                case R.id.menuProfile:
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                    return true;
            }
            return false;
        });
    }
}

