package com.example.indoorfit.workout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.indoorfit.Adapter.ArmChestAdapter;
import com.example.indoorfit.R;
import com.example.indoorfit.WorkoutActivity;

public class ArmChestWorkout extends AppCompatActivity {

    private ListView listView;
    private String[] workoutNames;
    private int[] workoutImages ={R.drawable.ac_push_ups,R.drawable.ac_tricep_dips,R.drawable.ac_diamond_push_ups,R.drawable.ac_decline_push_ups,
            R.drawable.ac_wide_push_ups,R.drawable.ac_clapping_push_ups,R.drawable.ac_pike_push_up};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arm_chest_workout);

        workoutNames = getResources().getStringArray(R.array.arm_chest_workout_names);
        listView = findViewById(R.id.listViewId3);
        ArmChestAdapter adapter = new ArmChestAdapter(this, workoutNames, workoutImages);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Start ArmChestED activity with the clicked workout position
                Intent intent = new Intent(ArmChestWorkout.this, ArmChestED.class);
                intent.putExtra("workoutName", position + 1); // Add 1 because positions are zero-based
                startActivity(intent);
            }
        });


        ImageView leftButton = findViewById(R.id.left_button);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(ArmChestWorkout.this, WorkoutActivity.class);
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
