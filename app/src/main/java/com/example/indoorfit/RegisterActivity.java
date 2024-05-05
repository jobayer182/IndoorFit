package com.example.indoorfit;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterActivity extends AppCompatActivity {

    private EditText fullNameEditText, emailEditText, passwordEditText, rePasswordEditText, weightEditText, heightEditText, ageEditText;

    private Button registerButton, signInButton;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        // Initialize views
        fullNameEditText = findViewById(R.id.ipFullName);
        emailEditText = findViewById(R.id.ipEmail);
        passwordEditText = findViewById(R.id.ipPassword);
        rePasswordEditText = findViewById(R.id.ipRePassword);
        weightEditText = findViewById(R.id.ipWeight);
        heightEditText = findViewById(R.id.ipHeight);
        ageEditText = findViewById(R.id.ipAge);


        registerButton = findViewById(R.id.registerButton);
        signInButton = findViewById(R.id.registerActivity);
        progressBar = findViewById(R.id.progressBar);

        // Set click listeners
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                finish();
            }
        });
    }

    private void registerUser() {
        // Retrieve user input from EditTexts
        String fullName = fullNameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String rePassword = rePasswordEditText.getText().toString().trim();
        int weight = Integer.parseInt(weightEditText.getText().toString().trim());
        float height = Float.parseFloat(heightEditText.getText().toString().trim());
        int age = Integer.parseInt(ageEditText.getText().toString().trim());

        // Perform input validation
        if (fullName.isEmpty() || email.isEmpty() || password.isEmpty() || rePassword.isEmpty() || weightEditText.getText().toString().trim().isEmpty()
                || heightEditText.getText().toString().trim().isEmpty()
                || ageEditText.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if passwords match
        if (!password.equals(rePassword)) {
            Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a user object with the user's information
        User user = new User(fullName, email, weight, height, age);

        // Show the progress bar while registering the user
        progressBar.setVisibility(View.VISIBLE);

        // Create a new user with Firebase Authentication
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Registration success
                            String userId = firebaseAuth.getCurrentUser().getUid();

                            // Create a user object to store in the Realtime Database
                            User user = new User(fullName, email, weight, height, age);

                            // Store user data in the Realtime Database
                            databaseReference.child(userId).setValue(user);

                            Toast.makeText(RegisterActivity.this, "Registration successful!", Toast.LENGTH_SHORT).show();

                            // Redirect to another activity, e.g., the login activity
                            Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(loginIntent);
                            finish();
                        } else {
                            // Registration failed
                            Toast.makeText(RegisterActivity.this, "Registration failed. " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Registration failed
                        Toast.makeText(RegisterActivity.this, "Registration failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}