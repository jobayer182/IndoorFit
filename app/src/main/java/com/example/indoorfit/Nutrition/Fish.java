package com.example.indoorfit.Nutrition;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.indoorfit.Adapter.FishAdapter;
import com.example.indoorfit.NutritionActivity;
import com.example.indoorfit.R;

public class Fish extends AppCompatActivity {

    private GridView gridView;
    private String[] nutritionNames;
    private int[] nutritionImages = {R.drawable.catla, R.drawable.hilsa, R.drawable.mirror_carp,
            R.drawable.red_drum, R.drawable.rohu_carp, R.drawable.salmon, R.drawable.tuna,
            R.drawable.tilapia};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish);

        nutritionNames = getResources().getStringArray(R.array.fish_name);
        gridView = (GridView) findViewById(R.id.gridViewId);

        ImageView leftButton = findViewById(R.id.left_button);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(Fish.this, NutritionActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });

        FishAdapter adapter = new FishAdapter(this,nutritionNames,nutritionImages);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Fish.this, FishED.class);
                intent.putExtra("nutritionName", position + 1); // Add 1 because positions are zero-based
                startActivity(intent);
            }
        });

    }
}