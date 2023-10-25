package com.example.indoorfit;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;

import com.example.indoorfit.Nutrition.Fish;
import com.example.indoorfit.Nutrition.Fruit;
import com.example.indoorfit.Nutrition.Meat;
import com.example.indoorfit.Nutrition.Nut;
import com.example.indoorfit.Nutrition.Veggies;
import com.example.indoorfit.workout.AbsWorkout;
import com.example.indoorfit.workout.ArmChestWorkout;
import com.example.indoorfit.workout.FullBodyWorkout;
import com.example.indoorfit.workout.LegWorkout;
import com.example.indoorfit.workout.ShoulderBackWorkout;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    private CardView fruitCardView;
    private CardView veggiesCardView;
    private CardView meatCardView;
    private CardView nutCardView;
    private CardView fishCardView;

    private CardView fullBodyCardView;
    private CardView absCardView;
    private CardView armChestCardView;
    private CardView shoulderBackCardView;
    private CardView legCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        float stepData = StepDataSingleton.getInstance().getStepData();

        // Now, you have the step data in the 'stepData' variable.
        TextView stepDataTextView = findViewById(R.id.txtStepsValue); // Assuming you have a TextView in your layout
        stepDataTextView.setText("" + stepData);

// Initialize all CardViews
        fruitCardView = findViewById(R.id.fruitId);
        veggiesCardView = findViewById(R.id.veggiesId);
        meatCardView = findViewById(R.id.meatId);
        nutCardView = findViewById(R.id.nutId);
        fishCardView = findViewById(R.id.fishId);

        fullBodyCardView = findViewById(R.id.fullBodyWorkout);
        absCardView = findViewById(R.id.absWorkout);
        armChestCardView = findViewById(R.id.armChestWorkout);
        shoulderBackCardView = findViewById(R.id.shoulderBackWorkout);
        legCardView = findViewById(R.id.legWorkout);

        fruitCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Fruit activity
                Intent fruitIntent = new Intent(MainActivity.this, Fruit.class);
                startActivity(fruitIntent);
            }
        });

        veggiesCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Veggies activity
                Intent veggiesIntent = new Intent(MainActivity.this, Veggies.class);
                startActivity(veggiesIntent);
            }
        });

        meatCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Meat activity
                Intent meatIntent = new Intent(MainActivity.this, Meat.class);
                startActivity(meatIntent);
            }
        });

        nutCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Nut activity
                Intent nutIntent = new Intent(MainActivity.this, Nut.class);
                startActivity(nutIntent);
            }
        });

        fishCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Fish activity
                Intent fishIntent = new Intent(MainActivity.this, Fish.class);
                startActivity(fishIntent);
            }
        });


        fullBodyCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start FullBodyWorkout activity
                Intent fullBodyIntent = new Intent(MainActivity.this, FullBodyWorkout.class);
                startActivity(fullBodyIntent);
            }
        });

        absCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start AbsWorkout activity
                Intent absIntent = new Intent(MainActivity.this, AbsWorkout.class);
                startActivity(absIntent);
            }
        });

        armChestCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start ArmChestWorkout activity
                Intent armChestIntent = new Intent(MainActivity.this, ArmChestWorkout.class);
                startActivity(armChestIntent);
            }
        });

        shoulderBackCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start ShoulderBackWorkout activity
                Intent shoulderBackIntent = new Intent(MainActivity.this, ShoulderBackWorkout.class);
                startActivity(shoulderBackIntent);
            }
        });

        legCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start LegWorkout activity
                Intent legIntent = new Intent(MainActivity.this, LegWorkout.class);
                startActivity(legIntent);
            }
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.menuHome);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menuHome:
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


// Define a notification channel (You can do this in your MainActivity or Application class)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "StepCounterChannel"; // Channel name
            String description = "Channel for Step Counter Notifications"; // Channel description
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("StepCounterChannel", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }


    }



    @Override
    public void onBackPressed(){
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }

}
