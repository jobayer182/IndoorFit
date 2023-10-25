package com.example.indoorfit.Nutrition;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.indoorfit.Adapter.VeggiesAdapter;
import com.example.indoorfit.NutritionActivity;
import com.example.indoorfit.R;


public class Veggies extends AppCompatActivity {

    private GridView gridView;
    private String[] nutritionNames;
    private int[] nutritionImages = {R.drawable.asparagus, R.drawable.broccoli, R.drawable.carrot,
            R.drawable.cucumber, R.drawable.potato, R.drawable.radish, R.drawable.tomato,
            R.drawable.spinach};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veggies);

        nutritionNames = getResources().getStringArray(R.array.veggies_name);
        gridView = (GridView) findViewById(R.id.gridViewId);


        ImageView leftButton = findViewById(R.id.left_button);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(Veggies.this, NutritionActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });

        VeggiesAdapter adapter = new VeggiesAdapter (this,nutritionNames,nutritionImages);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Veggies.this, VeggiesED.class);
                intent.putExtra("nutritionName", position + 1); // Add 1 because positions are zero-based
                startActivity(intent);
            }
        });
    }
}