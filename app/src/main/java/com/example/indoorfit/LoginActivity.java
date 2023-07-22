package com.example.indoorfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText ipEmail, ipPassword;
    private Button loginButton;
    private TextView forgotPassword, registerActivity;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ipEmail = findViewById(R.id.ipEmail);
        ipPassword = findViewById(R.id.ipPassword);
        loginButton = findViewById(R.id.loginButtonId);
        forgotPassword = findViewById(R.id.forgotPassId);
        registerActivity = findViewById(R.id.registerActivity);
        progressBar = findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();

        // Login Button Click Event
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                loginUser();
            }
        });

        // Register Link Click Event
        registerActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loginUser() {
        // get user input email and password
        String email = Objects.requireNonNull(ipEmail.getText()).toString().trim();
        String password = Objects.requireNonNull(ipPassword.getText()).toString().trim();

        // validate user input
        if (TextUtils.isEmpty(email)) {
            ipEmail.setError("Email is required.");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            ipPassword.setError("Password is required.");
            return;
        }

        // show progress bar
        progressBar.setVisibility(View.VISIBLE);

        // authenticate user
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(LoginActivity.this, "Authentication succeeded.",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // hide progress bar
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
    }

}
