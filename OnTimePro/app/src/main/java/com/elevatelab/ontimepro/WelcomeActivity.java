package com.elevatelab.ontimepro;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class WelcomeActivity extends AppCompatActivity {


    private Button registerBtn, loginBtn;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseFirestore db;
    private String instiIdOfCurrent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initViews();
        mFirebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        if (mFirebaseAuth.getCurrentUser() != null) {

            String userID = mFirebaseAuth.getUid();
            startIntent(userID);
        }

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUpIntent = new Intent(WelcomeActivity.this, SignUpActivity.class);
                startActivity(signUpIntent);
                finish();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                finish();
            }
        });
    }
    private void initViews(){
        registerBtn = findViewById(R.id.login_register_btn);
        loginBtn = findViewById(R.id.login_btn);
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
                        Intent mainActivityIntent = new Intent(WelcomeActivity.this, MainActivity.class);
                        mainActivityIntent.putExtra("instiCode",instiIdOfCurrent);
                        startActivity(mainActivityIntent);
                        finish();
                    }
                });

    }

}