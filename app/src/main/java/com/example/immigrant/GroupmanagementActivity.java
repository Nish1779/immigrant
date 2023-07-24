package com.example.immigrant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class GroupmanagementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupmanagement);

// Create a group
        //  To create a new group in Firestore, use the following code:

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        Map<String, Object> groupData = new HashMap<>();
        groupData.put("name", "Group 1");
        groupData.put("creatorId", "userId1");

        CollectionReference groupsCollection = firestore.collection("groups");
        DocumentReference newGroupRef = groupsCollection.document("groupId1");
        newGroupRef.set(groupData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Group creation successful
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Group creation failed
                    }
                });

// Add Members to the Group:
//To add members to a group, use the following code:

        Map<String, Boolean> membersData = new HashMap<>();
        membersData.put("userId1", true);
        membersData.put("userId2", true);

        DocumentReference groupRef = firestore.collection("groups").document("groupId1");
        groupRef.update("members", membersData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Members added successfully
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Failed to add members
                    }
                });

//Add Tasks to the Group:
//To add tasks to a group, use the following code:

        Map<String, Object> taskData = new HashMap<>();
        taskData.put("title", "Task 1");
        taskData.put("description", "Description of Task 1");
        taskData.put("dueDate", "2023-07-10");
        taskData.put("status", "pending");

        Map<String, Boolean> assigneesData = new HashMap<>();
        assigneesData.put("userId1", true);
        assigneesData.put("userId2", true);
        taskData.put("assignees", assigneesData);

        CollectionReference tasksCollection = firestore.collection("groups").document("groupId1").collection("tasks");
        DocumentReference newTaskRef = tasksCollection.document("taskId1");
        newTaskRef.set(taskData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Task creation successful
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Task creation failed
                    }
                });



        // Add Comments to a Task:
        //
        //To add comments to a task within a group, use the following code:

        Map<String, Object> commentData = new HashMap<>();
        commentData.put("userId", "userId1");
        commentData.put("text", "Comment 1");
        commentData.put("timestamp", 1625315234);

        CollectionReference commentsCollection = firestore.collection("groups").document("groupId1")
                .collection("tasks").document("taskId1").collection("comments");
        DocumentReference newCommentRef = commentsCollection.document();
        newCommentRef.set(commentData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Comment creation successful
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Comment creation failed
                    }
                });








    }
}