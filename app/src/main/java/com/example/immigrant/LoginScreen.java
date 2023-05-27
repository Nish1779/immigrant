package com.example.immigrant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.ApiException;
import com.google.firebase.auth.FirebaseAuth;



public class LoginScreen extends AppCompatActivity {

// variables for the google signin option
    private static final int RC_SIGN_IN = 9001;
    private static final String TAG = "Login failed from the ggogle side";
    private FirebaseAuth firebaseAuth;
    private GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        FirebaseApp.initializeApp(this);
        // Initialize Firebase Authentication

                // Initialize Firebase Authentication
                firebaseAuth = FirebaseAuth.getInstance();

                // Configure Google Sign-In options
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .build();

                // Build a GoogleSignInClient with the options
                googleSignInClient = GoogleSignIn.getClient(this, gso);

                // Set click listener for the Google Sign-In button
                SignInButton googleSignInButton = findViewById(R.id.googleSignInButton);
                googleSignInButton.setOnClickListener(view -> signInWithGoogle());
            }

            // Sign in with Google button click handler
            private void signInWithGoogle() {
                Intent signInIntent = googleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }

            // Handle the Google Sign-In response
            @Override
            public void onActivityResult(int requestCode, int resultCode, Intent data) {
                super.onActivityResult(requestCode, resultCode, data);

                if (requestCode == RC_SIGN_IN) {
                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                    try {
                        // Google Sign-In was successful, authenticate with Firebase
                        GoogleSignInAccount account = task.getResult(ApiException.class);
                        // TODO: Authenticate with Firebase using the Google account
                    } catch (ApiException e) {
                        // Google Sign-In failed, handle the error
                        Log.w(TAG, "Google sign-in failed", e);
                    }
                }
            }


}