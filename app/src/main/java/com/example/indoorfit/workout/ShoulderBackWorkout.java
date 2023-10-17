package com.example.indoorfit.workout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.indoorfit.Adapter.ShoulderBackAdapter;
import com.example.indoorfit.R;
import com.example.indoorfit.WorkoutActivity;

public class ShoulderBackWorkout extends AppCompatActivity {

    private ListView listView;
    private String[] workoutNames;
    private int[] workoutImages ={R.drawable.sb_push_ups,R.drawable.sb_bird_dog_plank,R.drawable.sb_supermans,R.drawable.sb_shoulder_taps,
            R.drawable.sb_back_extensions,R.drawable.sb_bear_crawls,R.drawable.sb_inchworms};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoulder_back_workout);

        workoutNames = getResources().getStringArray(R.array.shoulder_back_workout_names);
        listView = findViewById(R.id.listViewId4);
        ShoulderBackAdapter adapter = new ShoulderBackAdapter(this, workoutNames, workoutImages);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Start ShoulderBackED activity with the clicked workout position
                Intent intent = new Intent(ShoulderBackWorkout.this, ShoulderBackED.class);
                intent.putExtra("workoutName", position + 1); // Add 1 because positions are zero-based
                startActivity(intent);
            }
        });


        ImageView leftButton = findViewById(R.id.left_button);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(ShoulderBackWorkout.this, WorkoutActivity.class);
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
