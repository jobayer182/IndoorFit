package com.example.indoorfit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class ProfileActivity extends AppCompatActivity {

    ImageView imageView;
    FloatingActionButton button;
    private TextView fullNameEditText, weightEditText, heightEditText, emailEditText, ageEditText;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        // Initialize Firebase
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        // Initialize views
        imageView = findViewById(R.id.shapeableImageView);
        button = findViewById(R.id.floatingActionButton);
        fullNameEditText = findViewById(R.id.ipFullName);
        weightEditText = findViewById(R.id.ipWeight);
        heightEditText = findViewById(R.id.ipHeight);
        emailEditText = findViewById(R.id.ipEmail);
        ageEditText = findViewById(R.id.ipAge);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.menuProfile);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menuHome:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                    return true;
                case R.id.menuStep:
                    startActivity(new Intent(getApplicationContext(), StepCounterActivity.class));
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                    return true;
                case R.id.menuWorkout:
                    startActivity(new Intent(getApplicationContext(), WorkoutActivity.class));
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                    return true;
                case R.id.menuNutrition:
                    startActivity(new Intent(getApplicationContext(), NutritionActivity.class));
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                    return true;
                case R.id.menuProfile:
                    return true;
            }
            return false;
        });


        // Handle image picker
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(ProfileActivity.this)
                        .crop()                    // Crop image (Optional)
                        .compress(1024)            // Final image size will be less than 1 MB (Optional)
                        .maxResultSize(1080, 1080)  // Final image resolution will be less than 1080 x 1080 (Optional)
                        .start();
            }
        });

        // Load and set the profile image from SharedPreferences
        String imageUriString = sharedPreferences.getString("profileImageUri", "");
        if (!imageUriString.isEmpty()) {
            Uri imageUri = Uri.parse(imageUriString);
            imageView.setImageURI(imageUri);
        }

        // Retrieve the current user's UID
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            String currentUserId = currentUser.getUid();

            // Retrieve the user data from the Realtime Database
            databaseReference.child(currentUserId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        User user = dataSnapshot.getValue(User.class);

                        // Update the UI with the user's data
                        fullNameEditText.setText(user.getFullName());
                        weightEditText.setText(String.valueOf(user.getWeight()));
                        heightEditText.setText(String.valueOf(user.getHeight()));
                        emailEditText.setText(user.getEmail());
                        ageEditText.setText(String.valueOf(user.getAge()));

                        loadUserImageFromStorage(currentUserId);
                    } else {
                        // Handle the case where the user's data is not found
                        Toast.makeText(ProfileActivity.this, "User data not found.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle any errors that may occur
                    Toast.makeText(ProfileActivity.this, "Database Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            // Handle the case where the user is not authenticated or logged in.
            // You can add appropriate logic for handling this situation.
        }
    }

    private void loadUserImageFromStorage(String currentUserId) {
        // Create a reference to the user's profile image in Firebase Storage
        StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("userImages/" + currentUserId + ".jpg");

        // Download the image and display it in your ImageView
        storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
            String imageUrl = uri.toString();

            // Use an image loading library (e.g., Picasso, Glide) to load and display the image
            // Here, we'll use Glide as an example:
            Glide.with(ProfileActivity.this)
                    .load(imageUrl)
                    .into(imageView);
        }).addOnFailureListener(e -> {
            // Handle the failure case when image download fails

            // Provide a more specific error message based on the exception
            String errorMessage = "Failed to load user image: " + e.getMessage();
            Toast.makeText(ProfileActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        imageView.setImageURI(uri);

        // Save the image URI to SharedPreferences
        editor.putString("profileImageUri", uri.toString());
        editor.apply();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }
}
