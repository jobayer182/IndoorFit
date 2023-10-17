package com.example.indoorfit.workout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.indoorfit.R;

public class LegED extends AppCompatActivity {
    String buttonvalue;
    Button startBtn;
    Button youtubeBtn;
    private CountDownTimer countDownTimer;
    TextView mtextview;
    private boolean MTimeRunning;
    private long MTimeLeftinmills;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leg_ed);


        Intent intent = getIntent();
        int workoutPosition = intent.getIntExtra("workoutName", 1);
        Log.d("LegED", "Received workoutPosition: " + workoutPosition);
        switch (workoutPosition) {


            case 1:
                Log.d("LegED", "Case 1 triggered");
                setContentView(R.layout.l_squats);
                setupYouTubeLinkButton("https://www.youtube.com/watch?v=hIkeJVV-Djk");
                break;
            case 2:
                Log.d("LegED", "Case 2 triggered");
                setContentView(R.layout.l_lunges);
                setupYouTubeLinkButton("https://www.youtube.com/watch?v=xqvCmoLULNY");
                break;
            case 3:
                Log.d("LegED", "Case 3 triggered");
                setContentView(R.layout.l_jump_squats);
                setupYouTubeLinkButton("https://www.youtube.com/watch?v=xqvCmoLULNY");
                break;
            case 4:
                Log.d("LegED", "Case 4 triggered");
                setContentView(R.layout.l_calf_raises);
                setupYouTubeLinkButton("https://www.youtube.com/watch?v=xqvCmoLULNY");
                break;
            case 5:
                Log.d("LegED", "Case 5 triggered");
                setContentView(R.layout.l_jumping_jacks);
                setupYouTubeLinkButton("https://www.youtube.com/watch?v=xqvCmoLULNY");
                break;
            case 6:
                Log.d("LegED", "Case 6 triggered");
                setContentView(R.layout.l_high_knees);
                setupYouTubeLinkButton("https://www.youtube.com/watch?v=xqvCmoLULNY");
                break;
            case 7:
                Log.d("LegED", "Case 7 triggered");
                setContentView(R.layout.l_sumo_squats);
                setupYouTubeLinkButton("https://www.youtube.com/watch?v=xqvCmoLULNY");
                break;

        }


        startBtn = findViewById(R.id.startButton);
        mtextview = findViewById(R.id.time);
        ImageView leftButton = findViewById(R.id.left_button);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        startBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (MTimeRunning) {
                    stopTimer();

                } else {
                    startTimer();

                }

            }


            private void stopTimer() {

                countDownTimer.cancel();
                MTimeRunning = false;
                startBtn.setText("START");

            }


            private void startTimer() {

                final CharSequence value1 = mtextview.getText();
                String num1 = value1.toString();
                String num2 = num1.substring(0, 2);
                String num3 = num1.substring(3, 5);


                final int number = Integer.parseInt(num2) * 60 + Integer.parseInt(num3);
                MTimeLeftinmills = number * 1000L;

                countDownTimer = new CountDownTimer(MTimeLeftinmills, 1000) {
                    @Override
                    public void onTick(long millisUnitFinished) {

                        MTimeLeftinmills = millisUnitFinished;
                        updateTimer();

                    }

                    @Override
                    public void onFinish() {

                        int newvalue = Integer.parseInt(buttonvalue) + 1;
                        if (newvalue <= 7) {
                            Intent intent = new Intent(LegED.this, LegED.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.putExtra("workoutName", String.valueOf(newvalue));
                            startActivity(intent);
                        } else {
                            newvalue = 1;
                            Intent intent = new Intent(LegED.this, LegED.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.putExtra("workoutName", String.valueOf(newvalue));
                            startActivity(intent);
                        }


                    }
                }.start();
                startBtn.setText("Pause");
                MTimeRunning = true;

            }


            private void updateTimer() {


                int minutes = (int) MTimeLeftinmills / 60000;
                int seconds = (int) MTimeLeftinmills % 60000 / 1000;


                String timeLeftText = "";
                if (minutes < 10)
                    timeLeftText = "0";
                timeLeftText = timeLeftText + minutes + ":";
                if (seconds < 10)
                    timeLeftText += "0";
                timeLeftText += seconds;
                mtextview.setText(timeLeftText);

            }

        });
    }

    private void setupYouTubeLinkButton(final String youtubeLink) {
        youtubeBtn = findViewById(R.id.watch_on_youtube_button);
        youtubeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openYouTubeVideo(youtubeLink);
            }
        });
    }
    private void openYouTubeVideo(String link) {
        Uri uri = Uri.parse(link);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }
}
