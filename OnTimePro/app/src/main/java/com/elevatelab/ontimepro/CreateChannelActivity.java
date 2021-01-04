package com.elevatelab.ontimepro;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.elevatelab.ontimepro.BackgroundServices.Services;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CreateChannelActivity extends AppCompatActivity {

    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_channel);
        db = FirebaseFirestore.getInstance();
        retrieveCode();

    }

    private void retrieveCode()
    {
        db.collection("developers")
                .document("codesGenerated")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        ArrayList<String> inUseCodes = (ArrayList<String>) documentSnapshot.get("codes");

                        String newChannelCode = Services.codeGenerator(inUseCodes);

                        Toast.makeText(CreateChannelActivity.this, newChannelCode, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}