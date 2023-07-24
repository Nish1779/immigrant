package com.example.immigrant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SigninScreen extends AppCompatActivity {


    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firestore;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText usernameEditText;
    private EditText mobileNumberEditText;
    private Button signInButton,gologinInButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_screen);

// connecting with the firestore
        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        // Getting all the details pof the variables
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        usernameEditText = findViewById(R.id.usernameEditText);
        mobileNumberEditText = findViewById(R.id.mobileNumberEditText);
        signInButton = findViewById(R.id.signInButton);
        gologinInButton = findViewById(R.id.gologinInButton);

        signInButton.setOnClickListener(view -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String username = usernameEditText.getText().toString();
            String mobileNumber = mobileNumberEditText.getText().toString();

            // Perform input validation if needed

            createUserWithEmailAndPassword(email, password, username, mobileNumber);
        });

        gologinInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SigninScreen.this, LoginScreen.class));
            }
        });

    }

    // To create the new user in the firestore

    private void createUserWithEmailAndPassword(String email, String password, String username, String mobileNumber) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // User creation successful
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        String userId = user.getUid();

                        // Store user data in Firestore
                        storeUserData(userId, email, username, mobileNumber);

                        // Proceed with your app logic for a signed-in user
                    } else {
                        // User creation failed
                        Toast.makeText(SigninScreen.this, "Failed to create user: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

// to store the user dta into the firestore
    private void storeUserData(String userId, String email, String username, String mobileNumber) {
        DocumentReference userRef = firestore.collection("users").document(userId);

        Map<String, Object> userData = new HashMap<>();
        userData.put("email", email);
        userData.put("username", username);
        userData.put("mobileNumber", mobileNumber);

        userRef.set(userData)
                .addOnSuccessListener(aVoid -> {
                    // User data stored successfully
                })
                .addOnFailureListener(e -> {
                    // Error occurred while storing user data
                    Toast.makeText(SigninScreen.this, "Failed to store user data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

}