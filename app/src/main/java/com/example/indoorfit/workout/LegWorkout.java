package com.example.indoorfit.workout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.indoorfit.Adapter.LegAdapter;
import com.example.indoorfit.R;
import com.example.indoorfit.WorkoutActivity;

public class LegWorkout extends AppCompatActivity {

    private ListView listView;
    private String[] workoutNames;
    private int[] workoutImages ={R.drawable.l_squat,R.drawable.l_lunges,R.drawable.l_jump_squats,R.drawable.l_calf_raises,
            R.drawable.l_jumping_jacks,R.drawable.l_high_knees,R.drawable.l_sumo_squats};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leg_workout);

        workoutNames = getResources().getStringArray(R.array.leg_workout_names);
        listView = findViewById(R.id.listViewId5);
        LegAdapter adapter = new LegAdapter(this, workoutNames, workoutImages);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Start LegED activity with the clicked workout position
                Intent intent = new Intent(LegWorkout.this, LegED.class);
                intent.putExtra("workoutName", position + 1); // Add 1 because positions are zero-based
                startActivity(intent);
            }
        });


        ImageView leftButton = findViewById(R.id.left_button);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(LegWorkout.this, WorkoutActivity.class);
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