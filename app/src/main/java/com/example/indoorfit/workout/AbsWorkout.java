package com.example.indoorfit.workout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.indoorfit.Adapter.AbsAdapter;
import com.example.indoorfit.R;
import com.example.indoorfit.WorkoutActivity;

public class AbsWorkout extends AppCompatActivity {

    private ListView listView;
    private String[] workoutNames;
    private int[] workoutImages ={R.drawable.a_crunches,R.drawable.a_reverse_crunches,R.drawable.a_plank,R.drawable.a_bicycle_crunches,
            R.drawable.a_russian_twist,R.drawable.a_leg_raises,R.drawable.a_scissor_kicks};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abs_workout);

        workoutNames = getResources().getStringArray(R.array.abs_workout_names);
        listView = findViewById(R.id.listViewId2);
        AbsAdapter adapter = new AbsAdapter(this, workoutNames, workoutImages);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Start AbsED activity with the clicked workout position
                Intent intent = new Intent(AbsWorkout.this, AbsED.class);
                intent.putExtra("workoutName", position + 1); // Add 1 because positions are zero-based
                startActivity(intent);
            }
        });


        ImageView leftButton = findViewById(R.id.left_button);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(AbsWorkout.this, WorkoutActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });


    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }

}
