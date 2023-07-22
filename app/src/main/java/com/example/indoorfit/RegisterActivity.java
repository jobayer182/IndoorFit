package com.example.indoorfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText FullName, Email, Password;
    private TextInputLayout FullNameLayout, EmailLayout, PasswordLayout;
    private Button RegisterButton;
    private ProgressBar ProgressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        FullName = findViewById(R.id.ipFullName);
        Email = findViewById(R.id.ipEmail);
        Password = findViewById(R.id.ipPassword);

        FullNameLayout = findViewById(R.id.fullName);
        EmailLayout = findViewById(R.id.email);
        PasswordLayout = findViewById(R.id.password);

        RegisterButton = findViewById(R.id.registerButton);
        ProgressBar = findViewById(R.id.progressBar);

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = Objects.requireNonNull(FullName.getText()).toString().trim();
                String email = Objects.requireNonNull(Email.getText()).toString().trim();
                String password = Objects.requireNonNull(Password.getText()).toString().trim();

                if (fullName.isEmpty()) {
                    FullNameLayout.setError("Full name is required!");
                    FullNameLayout.requestFocus();
                    return;
                }

                if (email.isEmpty()) {
                    EmailLayout.setError("Email is required!");
                    EmailLayout.requestFocus();
                    return;
                }

                if (password.isEmpty()) {
                    PasswordLayout.setError("Password is required!");
                    PasswordLayout.requestFocus();
                    return;
                }

                ProgressBar.setVisibility(View.VISIBLE);

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(RegisterActivity.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                                    ProgressBar.setVisibility(View.GONE);
                                } else {
                                    Toast.makeText(RegisterActivity.this, "Registration failed! Please try again later.", Toast.LENGTH_SHORT).show();
                                    ProgressBar.setVisibility(View.GONE);
                                }
                            }
                        });
            }
        });
    }
}