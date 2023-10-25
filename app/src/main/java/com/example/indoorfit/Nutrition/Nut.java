package com.example.indoorfit.Nutrition;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.indoorfit.Adapter.NutAdapter;
import com.example.indoorfit.NutritionActivity;
import com.example.indoorfit.R;


public class Nut extends AppCompatActivity {

    private GridView gridView;
    private String[] nutritionNames;
    private int[] nutritionImages = {R.drawable.almond, R.drawable.cashew, R.drawable.chestnut,
            R.drawable.hazelnut, R.drawable.macadamia, R.drawable.peanut, R.drawable.sunflower_seed,
            R.drawable.walnut};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nut);

        nutritionNames = getResources().getStringArray(R.array.nut_name);
        gridView = (GridView) findViewById(R.id.gridViewId);

        ImageView leftButton = findViewById(R.id.left_button);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(Nut.this, NutritionActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });

        NutAdapter adapter = new NutAdapter (this,nutritionNames,nutritionImages);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Nut.this, NutED.class);
                intent.putExtra("nutritionName", position + 1); // Add 1 because positions are zero-based
                startActivity(intent);
            }
        });
    }
}