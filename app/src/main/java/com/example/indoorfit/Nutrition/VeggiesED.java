package com.example.indoorfit.Nutrition;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.indoorfit.R;

public class VeggiesED extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veggies_ed);

        Intent intent = getIntent();
        int nutritionPosition = intent.getIntExtra("nutritionName", 1);
        Log.d("VeggiesED", "Received nutritionPosition: " + nutritionPosition);

        switch (nutritionPosition) {


            case 1:
                Log.d("VeggiesED", "Case 1 triggered");
                setContentView(R.layout.vg_aspargus);
                break;
            case 2:
                Log.d("VeggiesED", "Case 2 triggered");
                setContentView(R.layout.vg_broccoli);
                break;
            case 3:
                Log.d("VeggiesED", "Case 3 triggered");
                setContentView(R.layout.vg_carrot);
                break;
            case 4:
                Log.d("VeggiesED", "Case 4 triggered");
                setContentView(R.layout.vg_cucumber);
                break;
            case 5:
                Log.d("VeggiesED", "Case 5 triggered");
                setContentView(R.layout.vg_potato);
                break;
            case 6:
                Log.d("VeggiesED", "Case 6 triggered");
                setContentView(R.layout.vg_radish);
                break;
            case 7:
                Log.d("VeggiesED", "Case 7 triggered");
                setContentView(R.layout.vg_tomato);
                break;
            case 8:
                Log.d("VeggiesED", "Case 8 triggered");
                setContentView(R.layout.vg_spinach);
                break;


        }
        ImageView leftButton = findViewById(R.id.left_button);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Finish the current activity, which simulates a back button press
            }
        });
    }
}