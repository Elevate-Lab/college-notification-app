package com.elevatelab.ontimepro;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class SplashActivity extends AppCompatActivity {

    private final int splashTime=3000;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseFirestore db;
    private String instiIdOfCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                if (mFirebaseAuth.getCurrentUser() != null) {
                    Log.e("User",mFirebaseAuth.getCurrentUser().getEmail());
                    String userID = mFirebaseAuth.getUid();
                    startIntent(userID);
                }
                else
                {
                    Intent intentWelcome = new Intent(SplashActivity.this,WelcomeActivity.class);
                    startActivity(intentWelcome);
                    finish();
                }
            }
        },splashTime);
    }

    private void startIntent(String userID)
    {
        db.collection("Users")
                .document(userID)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        instiIdOfCurrent = (String) documentSnapshot.get("instiID");
                        Log.e("INs",instiIdOfCurrent);
                        Intent mainActivityIntent = new Intent(SplashActivity.this, MainActivity.class);
                        mainActivityIntent.putExtra("instiCode",instiIdOfCurrent);
                        startActivity(mainActivityIntent);
                        finish();
                    }
                });

    }
}