package com.example.indoorfit;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class sp extends AppCompatActivity {

    private ProgressBar progressBar;
    private int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_sp);

        progressBar = findViewById(R.id.progressBarId);

        // Always proceed with splash screen progress
        startSplashScreen();
    }

    public void doWork() {
        for (progress = 20; progress <= 100; progress += 20) {
            try {
                Thread.sleep(1000); // Adjust this timing as needed
                runOnUiThread(() -> progressBar.setProgress(progress));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void startApp() {
        // Check login status after splash screen progress
        checkLoginStatus();
    }

    private void startSplashScreen() {
        Thread thread = new Thread(() -> {
            doWork();
            startApp();
        });
        thread.start();
    }

    private void checkLoginStatus() {
        // Check if the user is already logged in using SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("LoginScreenPrefs", MODE_PRIVATE); // Update with your actual key
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        if (isLoggedIn) {
            // If logged in, go directly to MainActivity
            navigateToMainActivity();
        } else {
            // If not logged in, proceed to sp1
            navigateToSp1();
        }
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(sp.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void navigateToSp1() {
        Intent intent = new Intent(sp.this, sp1.class);
        startActivity(intent);
        finish();
    }
}
