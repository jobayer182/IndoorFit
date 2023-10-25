package com.example.indoorfit.Nutrition;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.indoorfit.R;

public class NutED extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nut_ed);

        Intent intent = getIntent();
        int nutritionPosition = intent.getIntExtra("nutritionName", 1);
        Log.d("NutED", "Received nutritionPosition: " + nutritionPosition);

        switch (nutritionPosition) {


            case 1:
                Log.d("NutED", "Case 1 triggered");
                setContentView(R.layout.n_almond);
                break;
            case 2:
                Log.d("NutED", "Case 2 triggered");
                setContentView(R.layout.n_cashew);
                break;
            case 3:
                Log.d("NutED", "Case 3 triggered");
                setContentView(R.layout.n_chestnut);
                break;
            case 4:
                Log.d("NutED", "Case 4 triggered");
                setContentView(R.layout.n_hazelnut);
                break;
            case 5:
                Log.d("NutED", "Case 5 triggered");
                setContentView(R.layout.n_macadamia);
                break;
            case 6:
                Log.d("NutED", "Case 6 triggered");
                setContentView(R.layout.n_peanut);
                break;
            case 7:
                Log.d("NutED", "Case 7 triggered");
                setContentView(R.layout.n_sunflower_seed);
                break;
            case 8:
                Log.d("NutED", "Case 8 triggered");
                setContentView(R.layout.n_walnut);
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