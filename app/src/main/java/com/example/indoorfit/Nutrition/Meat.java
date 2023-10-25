package com.example.indoorfit.Nutrition;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.indoorfit.Adapter.MeatAdapter;
import com.example.indoorfit.NutritionActivity;
import com.example.indoorfit.R;


public class Meat extends AppCompatActivity {

    private GridView gridView;
    private String[] nutritionNames;
    private int[] nutritionImages = {R.drawable.beef, R.drawable.chicken, R.drawable.duck,
            R.drawable.goat, R.drawable.labm, R.drawable.pork, R.drawable.turkey,
            R.drawable.veal};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meat);

        nutritionNames = getResources().getStringArray(R.array.meat_name);
        gridView = (GridView) findViewById(R.id.gridViewId);


        ImageView leftButton = findViewById(R.id.left_button);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(Meat.this, NutritionActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });

        MeatAdapter adapter = new MeatAdapter (this,nutritionNames,nutritionImages);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Meat.this, MeatED.class);
                intent.putExtra("nutritionName", position + 1); // Add 1 because positions are zero-based
                startActivity(intent);
            }
        });
    }
}