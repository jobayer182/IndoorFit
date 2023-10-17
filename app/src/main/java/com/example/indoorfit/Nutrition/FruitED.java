package com.example.indoorfit.Nutrition;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.indoorfit.R;

public class FruitED extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_ed);

        Intent intent = getIntent();
        int nutritionPosition = intent.getIntExtra("nutritionName", 1);
        Log.d("FruitED", "Received nutritionPosition: " + nutritionPosition);

        switch (nutritionPosition) {


            case 1:
                Log.d("FruitED", "Case 1 triggered");
                setContentView(R.layout.fr_apple);
                break;
            case 2:
                Log.d("FruitED", "Case 2 triggered");
                setContentView(R.layout.fr_banana);
                break;
            case 3:
                Log.d("FruitED", "Case 3 triggered");
                setContentView(R.layout.fr_berrie);
                break;
            case 4:
                Log.d("FruitED", "Case 4 triggered");
                setContentView(R.layout.fr_grape);
                break;
            case 5:
                Log.d("FruitED", "Case 5 triggered");
                setContentView(R.layout.fr_mango);
                break;
            case 6:
                Log.d("FruitED", "Case 6 triggered");
                setContentView(R.layout.fr_orange);
                break;
            case 7:
                Log.d("FruitED", "Case 7 triggered");
                setContentView(R.layout.fr_pineapple);
                break;
            case 8:
                Log.d("FruitED", "Case 8 triggered");
                setContentView(R.layout.fr_watermelon);
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