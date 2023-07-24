package com.example.immigrant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.firebase.auth.FirebaseAuth;

import android.widget.ImageButton;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class JoinOption extends AppCompatActivity {

    private static final String GROUPS_COLLECTION = "groups";

    private ImageButton btn_create;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_option);

        // Initialize Firestore
        firestore = FirebaseFirestore.getInstance();

        // Find the create button by its ID
        btn_create = findViewById(R.id.btn_create);

        // Set click listener for the create button
        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Call the method to create the group and make an entry in Firestore
                createGroupAndFirestoreEntry();
            }
        });
    }

    private void createGroupAndFirestoreEntry() {
        // Perform any logic here related to creating the group

        // For example, you can create a new group with a unique group ID
        String groupId = "group_" + System.currentTimeMillis(); // Use your own logic to generate group IDs

        // You can also get additional information about the group from other UI elements or data sources

        // Create a Map to store the group data
        Map<String, Object> groupData = new HashMap<>();
        groupData.put("name", "My Group"); // Replace "My Group" with the actual group name
        groupData.put("description", "A sample group"); // Replace "A sample group" with the actual group description
       // groupData.put("", "A sample group"); // Replace "A sample group" with the actual group description


        // Add the group data to Firestore
        firestore.collection(GROUPS_COLLECTION)
                .document(groupId)
                .set(groupData)
                .addOnSuccessListener(aVoid -> {
                    // The group entry is successfully created in Firestore
                    // You can perform any additional actions here if needed
                })
                .addOnFailureListener(e -> {
                    // An error occurred while creating the group entry in Firestore
                    // Handle the error appropriately
                });
    }
}