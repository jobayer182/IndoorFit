package com.example.indoorfit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.indoorfit.workout.AbsWorkout;
import com.example.indoorfit.workout.ArmChestWorkout;
import com.example.indoorfit.workout.FullBodyWorkout;
import com.example.indoorfit.workout.LegWorkout;
import com.example.indoorfit.workout.ShoulderBackWorkout;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class WorkoutActivity extends AppCompatActivity {

    private Button fullBodyButton;
    private Button absButton;
    private Button armChestButton;
    private Button shoulderBackButton;
    private Button legButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        fullBodyButton= (Button) findViewById(R.id.fullBodyWorkout);
        absButton = findViewById(R.id.absWorkout);
        armChestButton = findViewById(R.id.armChestWorkout);
        shoulderBackButton = findViewById(R.id.shoulderBackWorkout);
        legButton = findViewById(R.id.legWorkout);
        ImageView leftButton= findViewById(R.id.left_button);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(WorkoutActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.menuWorkout);

        fullBodyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fullbodybuttonIntent = new Intent(WorkoutActivity.this, FullBodyWorkout.class);
                startActivity(fullbodybuttonIntent);
                finish();
            }
        });

        absButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent absbuttonIntent = new Intent(WorkoutActivity.this, AbsWorkout.class);
                startActivity(absbuttonIntent);
                finish();

            }
        });

        armChestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent armchestbuttonIntent = new Intent(WorkoutActivity.this, ArmChestWorkout.class);
                startActivity(armchestbuttonIntent);
                finish();
            }
        });

        shoulderBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shoulderbackbuttonIntent = new Intent(WorkoutActivity.this, ShoulderBackWorkout.class);
                startActivity(shoulderbackbuttonIntent);
                finish();
            }
        });

        legButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent legbackbuttonIntent = new Intent(WorkoutActivity.this, LegWorkout.class);
                startActivity(legbackbuttonIntent);
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
                    return true;
                case R.id.menuNutrition:
                    startActivity(new Intent(getApplicationContext(), NutritionActivity.class));
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
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