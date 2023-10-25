package com.example.indoorfit.Nutrition;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.indoorfit.Adapter.FruitAdapter;
import com.example.indoorfit.NutritionActivity;
import com.example.indoorfit.R;

public class Fruit extends AppCompatActivity {

    private GridView gridView;
    private String[] nutritionNames;
    private int[] nutritionImages = {R.drawable.apple, R.drawable.banana, R.drawable.berries,
            R.drawable.grape, R.drawable.mango, R.drawable.orange, R.drawable.pineapple,
            R.drawable.watermelon};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);

        nutritionNames = getResources().getStringArray(R.array.fruits_name);
        gridView = (GridView) findViewById(R.id.gridViewId);


        ImageView leftButton = findViewById(R.id.left_button);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(Fruit.this, NutritionActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });

        FruitAdapter adapter = new FruitAdapter(this,nutritionNames,nutritionImages);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Fruit.this, FruitED.class);
                intent.putExtra("nutritionName", position + 1); // Add 1 because positions are zero-based
                startActivity(intent);
            }
        });
    }
}