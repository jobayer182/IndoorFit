package com.example.indoorfit.Nutrition;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.indoorfit.R;

public class MeatED extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meat_ed);

        Intent intent = getIntent();
        int nutritionPosition = intent.getIntExtra("nutritionName", 1);
        Log.d("MeatED", "Received nutritionPosition: " + nutritionPosition);

        switch (nutritionPosition) {


            case 1:
                Log.d("MeatED", "Case 1 triggered");
                setContentView(R.layout.m_beef);
                break;
            case 2:
                Log.d("MeatED", "Case 2 triggered");
                setContentView(R.layout.m_chicken);
                break;
            case 3:
                Log.d("MeatED", "Case 3 triggered");
                setContentView(R.layout.m_duck);
                break;
            case 4:
                Log.d("MeatED", "Case 4 triggered");
                setContentView(R.layout.m_goat);
                break;
            case 5:
                Log.d("MeatED", "Case 5 triggered");
                setContentView(R.layout.m_lamb);
                break;
            case 6:
                Log.d("MeatED", "Case 6 triggered");
                setContentView(R.layout.m_pork);
                break;
            case 7:
                Log.d("MeatED", "Case 7 triggered");
                setContentView(R.layout.m_turkey);
                break;
            case 8:
                Log.d("MeatED", "Case 8 triggered");
                setContentView(R.layout.m_veal);
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