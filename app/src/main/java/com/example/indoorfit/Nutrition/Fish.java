package com.example.indoorfit.Nutrition;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.indoorfit.Adapter.FishAdapter;
import com.example.indoorfit.MainActivity;
import com.example.indoorfit.ProfileActivity;
import com.example.indoorfit.R;
import com.example.indoorfit.StepCounter.StepCounterActivity;
import com.example.indoorfit.WorkoutActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Fish extends AppCompatActivity {

    private GridView gridView;
    private String[] nutritionNames;
    private int[] nutritionImages = {R.drawable.apple, R.drawable.banana, R.drawable.berries,
            R.drawable.grape, R.drawable.mango, R.drawable.orange, R.drawable.pineapple,
            R.drawable.watermelon};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish);

        TextView fruitsTextView = findViewById(R.id.fruits);
        TextView veggiesTextView = findViewById(R.id.veggies);
        TextView meatTextView = findViewById(R.id.meat);
        TextView nutsTextView = findViewById(R.id.nuts);
        TextView fishTextView = findViewById(R.id.fish);


        nutritionNames = getResources().getStringArray(R.array.fruits_name);
        gridView = (GridView) findViewById(R.id.gridViewId);

        FishAdapter adapter = new FishAdapter(this,nutritionNames,nutritionImages);
        gridView.setAdapter(adapter);





        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.menuNutrition);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Fish.this, FishED.class);
                intent.putExtra("nutritionName", position + 1); // Add 1 because positions are zero-based
                startActivity(intent);
            }
        });


        fruitsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fruitsTextViewIntent = new Intent(Fish.this, Fruit.class);
                startActivity(fruitsTextViewIntent);
                finish();
            }
        });

        veggiesTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent veggiesTextViewIntent = new Intent(Fish.this, Veggies.class);
                startActivity(veggiesTextViewIntent);
                finish();

            }
        });

        meatTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent meatTextViewIntent = new Intent(Fish.this, Meat.class);
                startActivity(meatTextViewIntent);
                finish();
            }
        });

        nutsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nutsTextViewIntent = new Intent(Fish.this, Nut.class);
                startActivity(nutsTextViewIntent);
                finish();
            }
        });

        fishTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fishTextViewIntent = new Intent(Fish.this, Fish.class);
                startActivity(fishTextViewIntent);
                finish();
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

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }
}