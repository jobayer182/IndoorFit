package com.example.indoorfit.Nutrition;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.indoorfit.R;

public class FishED extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish_ed);

        Intent intent = getIntent();
        int nutritionPosition = intent.getIntExtra("nutritionName", 1);
        Log.d("FishED", "Received nutritionPosition: " + nutritionPosition);

        switch (nutritionPosition) {


            case 1:
                Log.d("FishED", "Case 1 triggered");
                setContentView(R.layout.fi_catla);
                break;
            case 2:
                Log.d("FishED", "Case 2 triggered");
                setContentView(R.layout.fi_hilsa);
                break;
            case 3:
                Log.d("FishED", "Case 3 triggered");
                setContentView(R.layout.fi_mirror_carp);
                break;
            case 4:
                Log.d("FishED", "Case 4 triggered");
                setContentView(R.layout.fi_red_drum);
                break;
            case 5:
                Log.d("FishED", "Case 5 triggered");
                setContentView(R.layout.fi_rohu_carp);
                break;
            case 6:
                Log.d("FishED", "Case 6 triggered");
                setContentView(R.layout.fi_salmon);
                break;
            case 7:
                Log.d("FishED", "Case 7 triggered");
                setContentView(R.layout.fi_tuna);
                break;
            case 8:
                Log.d("FishED", "Case 8 triggered");
                setContentView(R.layout.fi_tilapia);
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